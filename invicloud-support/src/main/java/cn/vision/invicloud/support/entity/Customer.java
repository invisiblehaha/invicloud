package cn.vision.invicloud.support.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
@TableName("crm_customer")
public class Customer extends Model<Customer> {

    private static final long serialVersionUID = 1L;

    /**
     * 顾客ID
     */
    @TableId(value = "customer_id", type = IdType.AUTO)
    private Integer customerId;
    /**
     * 人脸标识
     */
    @TableField("customer_token")
    private String customerToken;
    /**
     * 真实姓名
     */
    @TableField("real_name")
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
    @TableField("pic_img")
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
    @TableField("regeist_time")
    private Date regeistTime;
    /**
     * 会员级别
     */
    private Integer rank;


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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    protected Serializable pkVal() {
        return this.customerId;
    }

    @Override
    public String toString() {
        return "Customer{" +
        ", customerId=" + customerId +
        ", customerToken=" + customerToken +
        ", realName=" + realName +
        ", sex=" + sex +
        ", age=" + age +
        ", picImg=" + picImg +
        ", status=" + status +
        ", telephone=" + telephone +
        ", regeistTime=" + regeistTime +
        ", rank=" + rank +
        "}";
    }
}
