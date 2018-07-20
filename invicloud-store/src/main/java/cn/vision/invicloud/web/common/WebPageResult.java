package cn.vision.invicloud.web.common;

import java.io.Serializable;

/**
 * @Author: Hattori
 * @Date: 2018/7/8 2:59
 * @Description:
 */

public class WebPageResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private Object rows;

    private Integer total;

    public WebPageResult(Object rows, Integer total) {
        super();
        this.rows = rows;
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
