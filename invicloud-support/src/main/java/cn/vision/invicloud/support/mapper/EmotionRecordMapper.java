package cn.vision.invicloud.support.mapper;

import cn.vision.invicloud.support.entity.EmotionRecord;
import cn.vision.invicloud.support.pojo.vo.EmotionAnalyVO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmotionRecordMapper extends BaseMapper<EmotionRecord>
{
    //根据传入的EmotionRecord对象进行insert操作
    Integer insert(@Param("record")EmotionRecord record);
    //返回心情饼图list
    List<EmotionAnalyVO> getEList(@Param("customerId")Integer customerId);
}
