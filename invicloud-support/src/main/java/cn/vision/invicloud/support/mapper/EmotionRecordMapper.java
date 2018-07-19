package cn.vision.invicloud.support.mapper;

import cn.vision.invicloud.support.entity.EmotionRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface EmotionRecordMapper extends BaseMapper<EmotionRecord>
{
    //根据传入的EmotionRecord对象进行insert操作
    Integer insert(@Param("record")EmotionRecord record);

}
