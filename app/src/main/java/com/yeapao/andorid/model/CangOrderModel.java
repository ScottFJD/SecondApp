package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/12/2.
 */

public class CangOrderModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"id":14,"price":1,"duration":0,"status":"2","type":"2","houseName":"健身舱预约","code":"yy201709200000100014"},{"id":65,"price":0.2,"duration":1,"status":"2","type":"1","houseName":"健身舱使用","code":"sj201709290000100065"},{"id":66,"price":0.2,"duration":1,"status":"2","type":"1","houseName":"健身舱使用","code":"sj201709290000100066"},{"id":67,"price":0.2,"duration":1,"status":"2","type":"1","houseName":"健身舱使用","code":"sj201709290000100067"},{"id":70,"price":0.2,"duration":1,"status":"2","type":"1","houseName":"健身舱使用","code":"sj201709290000100070"},{"id":74,"price":0.2,"duration":1,"status":"2","type":"1","houseName":"健身舱使用","code":"sj201709290000100074"},{"id":30,"price":1,"duration":5,"status":"2","type":"2","houseName":"健身舱预约","code":"yy201709290000100030"},{"id":33,"price":1,"duration":5,"status":"2","type":"2","houseName":"健身舱预约","code":"yy201709290000100033"},{"id":34,"price":1,"duration":5,"status":"2","type":"2","houseName":"健身舱预约","code":"yy201709290000100034"},{"id":81,"price":0.2,"duration":1,"status":"2","type":"1","houseName":"健身舱使用","code":"sj201709290000100081"},{"id":88,"price":0.2,"duration":1,"status":"2","type":"1","houseName":"健身舱使用","code":"sj201709300000100088"},{"id":91,"price":0.2,"duration":1,"status":"2","type":"1","houseName":"健身舱使用","code":"sj201709300000100091"},{"id":92,"price":0.2,"duration":1,"status":"2","type":"1","houseName":"健身舱使用","code":"sj201709300000100092"},{"id":93,"price":0.2,"duration":1,"status":"2","type":"1","houseName":"健身舱使用","code":"sj201709300000100093"},{"id":94,"price":0.2,"duration":1,"status":"2","type":"1","houseName":"健身舱使用","code":"sj201709300000100094"}]
     */

    private int errcode;
    private String errmsg;
    private List<DataBean> data;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 14
         * price : 1
         * duration : 0
         * status : 2
         * type : 2
         * houseName : 健身舱预约
         * code : yy201709200000100014
         * "isOpen": "1",
         "discountName": "八折卡",
         "discountPrice": 2.4
         */

        private int id;
        private float price;
        private int duration;
        private String status;
        private String type;
        private String houseName;
        private String code;
        private String isOpen;
        private String discountName;
        private double discountPrice;

        public String getIsOpen() {
            return isOpen;
        }

        public void setIsOpen(String isOpen) {
            this.isOpen = isOpen;
        }

        public String getDiscountName() {
            return discountName;
        }

        public void setDiscountName(String discountName) {
            this.discountName = discountName;
        }

        public double getDiscountPrice() {
            return discountPrice;
        }

        public void setDiscountPrice(double discountPrice) {
            this.discountPrice = discountPrice;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getHouseName() {
            return houseName;
        }

        public void setHouseName(String houseName) {
            this.houseName = houseName;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
