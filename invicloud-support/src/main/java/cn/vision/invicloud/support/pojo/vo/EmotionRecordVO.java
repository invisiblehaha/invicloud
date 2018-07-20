package cn.vision.invicloud.support.pojo.vo;
import java.io.Serializable;
import java.util.Date;


public class EmotionRecordVO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer recordId;

    private String customerId;

    private String emotion;

    private Date recordTime;

    public Integer getRecordId(){return recordId;}

    public void setRecordId(Integer recordId){this.recordId = recordId;}

    public String getCustomerId(){return customerId;}

    public void setCustomerId(String customerId){this.customerId = customerId;}

    public String  getEmotion(){return emotion;}

    public void setEmotion(String emotion){this.emotion = emotion;}

    public Date getRecordTime(){return recordTime;}

    public void setRecordTime(Date recordTime){this.recordTime = recordTime;}

}
