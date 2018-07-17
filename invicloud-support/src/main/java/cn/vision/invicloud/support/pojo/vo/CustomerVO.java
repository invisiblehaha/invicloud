package cn.vision.invicloud.support.pojo.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Hattori
 * @Date: 2018/7/8 2:38
 * @Description:
 */
public class CustomerVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 顾客ID
     */
    private Integer customerId;
    /**
     * 人脸标识
     */
    private String customerToken;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 性别 0=保密/1=男/2=女
     */
    private Integer sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 顾客图片
     */
    private String picImg;
    /**
     * 状态 0=冻结/1=正常
     */
    private Integer status;
    /**
     * 手机号码
     */
    private String telephone;
    /**
     * 注册时间
     */
    private Date regeistTime;
    /**
     * 会员级别
     */
    private Integer noble;


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerToken() {
        return customerToken;
    }

    public void setCustomerToken(String customerToken) {
        this.customerToken = customerToken;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPicImg() {
        return picImg;
    }

    public void setPicImg(String picImg) {
        this.picImg = picImg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getRegeistTime() {
        return regeistTime;
    }

    public void setRegeistTime(Date regeistTime) {
        this.regeistTime = regeistTime;
    }

    public Integer getNoble() {
        return noble;
    }

    public void setNoble(Integer noble) {
        this.noble = noble;
    }

}
