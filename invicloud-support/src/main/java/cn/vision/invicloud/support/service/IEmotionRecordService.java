package cn.vision.invicloud.support.service;
import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.EmotionRecord;
import cn.vision.invicloud.support.pojo.vo.EmotionRecordVO;
import com.baomidou.mybatisplus.service.IService;

public interface IEmotionRecordService extends IService<EmotionRecord>{

    //创建一条记录
    Integer insertRecord(EmotionRecord record) throws Exception;

   /* //通过用户ID来获取记录（有疑问：一个用户对应多条记录，此方法或不适用）
    EmotionRecordVO getByCustomerId(String customerId);

    //分页显示（有疑问：是否需要）
    BasePageDTO<EmotionRecordVO> listByPage(PageInfo pageInfo);*/

}
