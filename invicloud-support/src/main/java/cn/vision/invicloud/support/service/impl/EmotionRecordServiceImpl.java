package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.entity.EmotionRecord;
import cn.vision.invicloud.support.mapper.EmotionRecordMapper;
import cn.vision.invicloud.support.service.IEmotionRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmotionRecordServiceImpl extends ServiceImpl<EmotionRecordMapper,EmotionRecord> implements IEmotionRecordService
{
    @Autowired
    private EmotionRecordMapper emotionRecordMapper;

    @Override
    public Integer insertRecord(EmotionRecord record){return emotionRecordMapper.insert(record);}


}
