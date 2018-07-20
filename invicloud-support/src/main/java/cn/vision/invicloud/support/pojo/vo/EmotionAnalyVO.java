package cn.vision.invicloud.support.pojo.vo;


import java.io.Serializable;

public class EmotionAnalyVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String emotion;

    private Integer num;

    public String getEmotion() {
        return emotion;
    }

    public Integer getNum() {
        return num;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
