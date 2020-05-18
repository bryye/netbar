package com.nuo.netbar.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
@ApiModel
public class Member {
   @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("username")
    private String username;
    @ApiModelProperty("password")
    private String password;
    @ApiModelProperty("garde")
    private String garde;
    @ApiModelProperty("phone")
    private String phone;
    @ApiModelProperty("time")
    private Date time;
    @ApiModelProperty("name")
    private String name;
    @ApiModelProperty("certificates")
    private String certificates;
    @ApiModelProperty("number")
    private String number;
    @ApiModelProperty("balance")
    private String balance;
    @ApiModelProperty("status")
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getGarde() {
        return garde;
    }

    public void setGarde(String garde) {
        this.garde = garde == null ? null : garde.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCertificates() {
        return certificates;
    }

    public void setCertificates(String certificates) {
        this.certificates = certificates == null ? null : certificates.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance == null ? null : balance.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}