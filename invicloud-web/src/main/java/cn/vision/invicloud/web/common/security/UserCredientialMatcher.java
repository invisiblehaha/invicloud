package cn.vision.invicloud.web.common.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.AbstractHash;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.StringUtils;

public class UserCredientialMatcher extends SimpleCredentialsMatcher {
    private String hashAlgorithm;
    private int hashIterations;
    private boolean hashSalted;
    private boolean storedCredentialsHexEncoded;

    public UserCredientialMatcher() {
        this.hashAlgorithm = null;
        this.hashSalted = false;
        this.hashIterations = 1;
        this.storedCredentialsHexEncoded = true;
    }

    public UserCredientialMatcher(String hashAlgorithmName) {
        this();
        if (!StringUtils.hasText(hashAlgorithmName)) {
            throw new IllegalArgumentException("hashAlgorithmName cannot be null or empty.");
        } else {
            this.hashAlgorithm = hashAlgorithmName;
        }
    }

    public String getHashAlgorithmName() {
        return this.hashAlgorithm;
    }

    public void setHashAlgorithmName(String hashAlgorithmName) {
        this.hashAlgorithm = hashAlgorithmName;
    }

    public boolean isStoredCredentialsHexEncoded() {
        return this.storedCredentialsHexEncoded;
    }

    public void setStoredCredentialsHexEncoded(boolean storedCredentialsHexEncoded) {
        this.storedCredentialsHexEncoded = storedCredentialsHexEncoded;
    }

    /** @deprecated */
    @Deprecated
    public boolean isHashSalted() {
        return this.hashSalted;
    }

    /** @deprecated */
    @Deprecated
    public void setHashSalted(boolean hashSalted) {
        this.hashSalted = hashSalted;
    }

    public int getHashIterations() {
        return this.hashIterations;
    }

    public void setHashIterations(int hashIterations) {
        if (hashIterations < 1) {
            this.hashIterations = 1;
        } else {
            this.hashIterations = hashIterations;
        }

    }

    /** @deprecated */
    @Deprecated
    protected Object getSalt(AuthenticationToken token) {
        return token.getPrincipal();
    }

    protected Object getCredentials(AuthenticationInfo info) {
        Object credentials = info.getCredentials();
        byte[] storedBytes = this.toBytes(credentials);
        if (credentials instanceof String || credentials instanceof char[]) {
            if (this.isStoredCredentialsHexEncoded()) {
                storedBytes = Hex.decode(storedBytes);
            } else {
                storedBytes = Base64.decode(storedBytes);
            }
        }

        AbstractHash hash = this.newHashInstance();
        hash.setBytes(storedBytes);
        return hash;
    }

    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        if(info.getCredentials()==null)return true;
        Object tokenHashedCredentials = this.hashProvidedCredentials(token, info);
        Object accountCredentials = this.getCredentials(info);
        return this.equals(tokenHashedCredentials, accountCredentials);
    }

    protected Object hashProvidedCredentials(AuthenticationToken token, AuthenticationInfo info) {
        Object salt = null;
        if (info instanceof SaltedAuthenticationInfo) {
            salt = ((SaltedAuthenticationInfo)info).getCredentialsSalt();
        } else if (this.isHashSalted()) {
            salt = this.getSalt(token);
        }

        return this.hashProvidedCredentials(token.getCredentials(), salt, this.getHashIterations());
    }

    private String assertHashAlgorithmName() throws IllegalStateException {
        String hashAlgorithmName = this.getHashAlgorithmName();
        if (hashAlgorithmName == null) {
            String msg = "Required 'hashAlgorithmName' property has not been set.  This is required to execute the hashing algorithm.";
            throw new IllegalStateException(msg);
        } else {
            return hashAlgorithmName;
        }
    }

    protected Hash hashProvidedCredentials(Object credentials, Object salt, int hashIterations) {
        String hashAlgorithmName = this.assertHashAlgorithmName();
        return new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
    }

    protected AbstractHash newHashInstance() {
        String hashAlgorithmName = this.assertHashAlgorithmName();
        return new SimpleHash(hashAlgorithmName);
    }
}
