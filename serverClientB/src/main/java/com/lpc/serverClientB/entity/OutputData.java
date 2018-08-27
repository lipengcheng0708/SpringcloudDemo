package com.lpc.serverClientB.entity;

import java.io.Serializable;

public class OutputData implements Serializable {

    String nsrsbh;//纳税人识别号
    String fpdm;//发票代码
    String fpzl;//发票种类
    String date;//日期
    String fpzt;//发票状态

    public String getFpzt() {
        return fpzt;
    }

    public void setFpzt(String fpzt) {
        this.fpzt = fpzt;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OutputData{" +
                "nsrsbh='" + nsrsbh + '\'' +
                ", fpdm='" + fpdm + '\'' +
                ", fpzl='" + fpzl + '\'' +
                ", date='" + date + '\'' +
                ", fpzt='" + fpzt + '\'' +
                '}';
    }
}
