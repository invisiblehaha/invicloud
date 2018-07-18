package cn.vision.invicloud.support.pojo.vo;

import java.io.Serializable;

public class RFMVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int customerId;

    private String recent;

    private int monetary;

    private int frequency;

    public int getCustomerId() {
        return customerId;
    }

    public String getRecent() {
        return recent;
    }

    public int getMonetary() {
        return monetary;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setRecent(String recent) {
        this.recent = recent;
    }

    public void setMonetary(int monetary) {
        this.monetary = monetary;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return  customerId +"\t"+ recent +"\t" + monetary +"\t" + frequency;
    }
}
