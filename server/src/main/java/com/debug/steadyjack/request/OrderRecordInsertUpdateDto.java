package com.debug.steadyjack.request;

import java.io.Serializable;

public class OrderRecordInsertUpdateDto implements Serializable {
    private Integer id;

    private String orderNo;

    private String orderType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "OrderRecordInsertUpdateDto{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", orderType='" + orderType + '\'' +
                '}';
    }
}
