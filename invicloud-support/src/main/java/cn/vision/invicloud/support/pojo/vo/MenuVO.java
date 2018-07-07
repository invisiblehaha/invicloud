package cn.vision.invicloud.support.pojo.vo;

import cn.vision.invicloud.support.pojo.dto.MenuDTO;

import java.util.List;

/**
 * @Author: Hattori
 * @Date: 2018/7/7 3:36
 * @Description:
 */
public class MenuVO extends MenuDTO {
    private static final long serialVersionUID = 1L;

    /** 子级权限List */
    private List<MenuDTO> childMenus;

    public List<MenuDTO> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<MenuDTO> childMenus) {
        this.childMenus = childMenus;
    }
}
