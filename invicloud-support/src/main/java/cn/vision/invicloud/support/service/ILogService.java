package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Log;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 日志记录表 服务类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface ILogService extends IService<Log> {

    /**
     * 根据分页信息/搜索内容查找日志记录列表
     * @param pageInfo 分页信息
     * @param search 搜索内容
     * @return
     */
    BasePageDTO<Log> listByPage(PageInfo pageInfo, String search);
}
