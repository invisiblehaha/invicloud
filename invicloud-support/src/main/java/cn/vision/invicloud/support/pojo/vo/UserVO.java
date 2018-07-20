package cn.vision.invicloud.support.pojo.vo;

import cn.vision.invicloud.support.entity.Role;
import cn.vision.invicloud.support.entity.User;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;
import java.util.List;

public class UserVO extends User {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId(value = "user_id", type = IdType.AUTO)
	private Integer userId;
	/**
	 * 用户账号
	 */
	@TableField("login_name")
	private String loginName;
	/**
	 * 用户密码
	 */
	@TableField("login_password")
	private String loginPassword;
	/**
	 * 加密盐
	 */
	private String salt;
	/**
	 * 真实姓名
	 */
	@TableField("user_name")
	private String userName;
	/**
	 * 性别 0=保密/1=男/2=女
	 */
	private Integer sex;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 用户图片
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
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@TableField("update_time")
	private Date updateTime;

	@TableField("faceToken")
	private String faceToken;
	/** 用户角色 */
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
}
