package cn.vision.invicloud.support.entity;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


@TableName("crm_emotion_records")
public class EmotionRecord extends Model<EmotionRecord>{
    //情绪记录
    private static final long serialVersionUID = 1L;

    @TableId(value = "record_id", type = IdType.AUTO)
    private Integer recordId;

    @TableField("customer_id")
    private String customerId;

    @TableField("emotion")
    private String emotion;

    @TableField("record_time")
    private Date recordTime;

    public Integer getRecordId(){return recordId;}

    public void setRecordId(Integer recordId){this.recordId = recordId;}

    public String getCustomerId(){return customerId;}

    public void setCustomerId(String customerId){this.customerId = customerId;}

    public String  getEmotion(){return emotion;}

    public void setEmotion(String emotion){this.emotion = emotion;}

    public Date getRecordTime(){return recordTime;}

    public void setRecordTime(Date recordTime){this.recordTime = recordTime;}

    @Override
    protected Serializable pkVal() {
        return this.recordId;
    }

    @Override
    public String toString() {
        return "Record{" +
                ", recordId=" + recordId +
                ", customerId=" + customerId +
                ", emotion=" + emotion +
                ", recordTime=" + recordTime +
                "}";
    }
}
