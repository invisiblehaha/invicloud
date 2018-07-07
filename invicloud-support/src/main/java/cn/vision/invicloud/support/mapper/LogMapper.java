package cn.vision.invicloud.support.mapper;

import cn.vision.invicloud.support.entity.Log;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import java.util.List;

/**
 * <p>
 * 日志记录表 Mapper 接口
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface LogMapper extends BaseMapper<Log> {

    List<Log> selectPageBySearch(Pagination page, String search);
}
