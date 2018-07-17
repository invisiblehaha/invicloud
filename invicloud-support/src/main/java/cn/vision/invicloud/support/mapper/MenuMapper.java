package cn.vision.invicloud.support.mapper;

import cn.vision.invicloud.support.entity.Menu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 目录表 Mapper 接口
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据目录类型查询目录列表
     * @param menuType 目录类型
     * @return
     */
    List<Menu> listByType(@Param("menuType") Integer menuType);
}
