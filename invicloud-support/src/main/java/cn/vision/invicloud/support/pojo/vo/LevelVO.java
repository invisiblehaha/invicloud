package cn.vision.invicloud.support.pojo.vo;

import java.io.Serializable;


public class LevelVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 消费者ID
     */
    private Integer customerId;
    /**
     * 平均金额
     */
    private Integer money;
    /**
     * 平均数量
     */
    private Integer num;

    public LevelVO() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getMoney() {
        return money;
    }

    public Integer getNum() {
        return num;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
