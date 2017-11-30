package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/11/29.
 */

public class DynamicLessonOrderModel {
    /**
     * errcode : 0
     * errmsg : ok
     * data : {"date":"2017-11-27 10:18:01","isOpen":"0","calendarOrderCode":"tk201711290000200004","price":35,"time":"09:00-10:00","shopAddress":"高新区运河路47号","recoveryMax":15,"startDate":"2017-11-29 14:27:24"}
     */

    private int errcode;
    private String errmsg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * date : 2017-11-27 10:18:01
         * isOpen : 0
         * calendarOrderCode : tk201711290000200004
         * price : 35
         * time : 09:00-10:00
         * shopAddress : 高新区运河路47号
         * recoveryMax : 15
         * startDate : 2017-11-29 14:27:24
         */

        private String date;
        private String isOpen;
        private String calendarOrderCode;
        private int price;
        private String time;
        private String shopAddress;
        private int recoveryMax;
        private String startDate;
        private String discountName;
        private String discount;

        public String getDiscountName() {
            return discountName;
        }

        public void setDiscountName(String discountName) {
            this.discountName = discountName;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getIsOpen() {
            return isOpen;
        }

        public void setIsOpen(String isOpen) {
            this.isOpen = isOpen;
        }

        public String getCalendarOrderCode() {
            return calendarOrderCode;
        }

        public void setCalendarOrderCode(String calendarOrderCode) {
            this.calendarOrderCode = calendarOrderCode;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getShopAddress() {
            return shopAddress;
        }

        public void setShopAddress(String shopAddress) {
            this.shopAddress = shopAddress;
        }

        public int getRecoveryMax() {
            return recoveryMax;
        }

        public void setRecoveryMax(int recoveryMax) {
            this.recoveryMax = recoveryMax;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }
    }
}
