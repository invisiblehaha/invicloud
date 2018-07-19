package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Log;
import cn.vision.invicloud.support.mapper.LogMapper;
import cn.vision.invicloud.support.service.ILogService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 日志记录表 服务实现类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public BasePageDTO<Log> listByPage(PageInfo pageInfo, String search) {
        Page<Log> page = new Page<>(pageInfo.getCurrent(), pageInfo.getLimit());
        List<Log> adverts = logMapper.listByPage(pageInfo, search, page);
        pageInfo.setTotal((int)page.getTotal());
        return new BasePageDTO<Log>(pageInfo, adverts);
    }

}
