package cn.vision.invicloud.support.pojo.dto;

import java.io.Serializable;

/**
 * @Author: Hattori
 * @Date: 2018/7/7 3:35
 * @Description:
 */
public class MenuDTO  implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 权限ID
     */
    private Integer menuId;
    /**
     * 父目录ID
     */
    private Integer parentId;
    /**
     * 权限类型 0=菜单/1=功能/2=操作
     */
    private Integer menuType;
    /**
     * 目录标识
     */
    private String menuSign;
    /**
     * 目录名称
     */
    private String menuName;
    /**
     * 排序码
     */
    private Integer sort;
    /**
     * 链接地址
     */
    private String href;
    /**
     * 图标名称
     */
    private String icon;
    /**
     * 状态 0=隐藏/1=显示
     */
    private Integer status;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 备注信息
     */
    private String remarks;


    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public String getMenuSign() {
        return menuSign;
    }

    public void setMenuSign(String menuSign) {
        this.menuSign = menuSign;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
