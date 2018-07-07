package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.entity.Log;
import cn.vision.invicloud.support.mapper.LogMapper;
import cn.vision.invicloud.support.service.ILogService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Page<Log> listByPage(Page<Log> page, String search) {
        return page.setRecords(logMapper.selectPageBySearch(page,search));
    }
}
