package com.nuo.netbar.pojo;

import java.math.BigDecimal;

public class Computer {
    private Integer id;

    private Integer no;

    private String type;

    private BigDecimal price;

    private BigDecimal allnightprice;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAllnightprice() {
        return allnightprice;
    }

    public void setAllnightprice(BigDecimal allnightprice) {
        this.allnightprice = allnightprice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}