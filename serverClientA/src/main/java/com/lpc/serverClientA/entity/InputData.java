package com.lpc.serverClientA.entity;

import java.io.Serializable;

public class  InputData implements Serializable {

    String nsrsbh;//纳税人识别号
    String fpdm;//发票代码
    String fpzl;//发票种类
    String fpzt;//发票状态
    String date;//日期

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getFpdm() {
        return fpdm;
    }

    public void setFpdm(String fpdm) {
        this.fpdm = fpdm;
    }

    public String getFpzl() {
        return fpzl;
    }

    public void setFpzl(String fpzl) {
        this.fpzl = fpzl;
    }

    public String getFpzt() {
        return fpzt;
    }

    public void setFpzt(String fpzt) {
        this.fpzt = fpzt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "InputData{" +
                "nsrsbh='" + nsrsbh + '\'' +
                ", fpdm='" + fpdm + '\'' +
                ", fpzl='" + fpzl + '\'' +
                ", fpzt='" + fpzt + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
