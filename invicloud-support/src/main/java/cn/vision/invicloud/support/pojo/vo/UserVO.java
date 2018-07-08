package cn.vision.invicloud.support.pojo.vo;

import cn.vision.invicloud.support.entity.Role;
import cn.vision.invicloud.support.entity.User;

import java.util.List;

public class UserVO extends User {

	private static final long serialVersionUID = 1L;

	/** 用户角色 */
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
