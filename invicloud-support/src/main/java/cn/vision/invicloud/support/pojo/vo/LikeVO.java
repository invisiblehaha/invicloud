package cn.vision.invicloud.support.pojo.vo;

import java.io.Serializable;
import java.util.List;


public class LikeVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 消费者ID
     */
    private Integer customerId;
    /**
     * 喜好列表
     */
    private List<Integer> likeList;

    public LikeVO() {

    }

    public Integer getCustomerId() {
        return customerId;
    }

    public List<Integer> getLikeList() {
        return likeList;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setLikeList(List<Integer> likeList) {
        this.likeList = likeList;
    }


}
