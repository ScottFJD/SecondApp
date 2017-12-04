package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/12/2.
 */

public class StationDynamicOrderModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"date":"2017-11-27","discountName":"八折卡","types":"1","calendarOrderId":1,"calendarOrderCode":"tk201711220000100001","actualPrice":98,"num":1,"discountPrice":98,"time":"09:00-10:00","shopAddress":"高新区运河路47号","startDate":"2017-11-22 08:58:44"}
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
         * date : 2017-11-27
         * discountName : 八折卡
         * types : 1
         * calendarOrderId : 1
         * calendarOrderCode : tk201711220000100001
         * actualPrice : 98
         * num : 1
         * discountPrice : 98
         * time : 09:00-10:00
         * shopAddress : 高新区运河路47号
         * startDate : 2017-11-22 08:58:44
         */

        private String date;
        private String discountName;
        private String types;
        private int calendarOrderId;
        private String calendarOrderCode;
        private int actualPrice;
        private int num;
        private int discountPrice;
        private String time;
        private String shopAddress;
        private String startDate;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDiscountName() {
            return discountName;
        }

        public void setDiscountName(String discountName) {
            this.discountName = discountName;
        }

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
        }

        public int getCalendarOrderId() {
            return calendarOrderId;
        }

        public void setCalendarOrderId(int calendarOrderId) {
            this.calendarOrderId = calendarOrderId;
        }

        public String getCalendarOrderCode() {
            return calendarOrderCode;
        }

        public void setCalendarOrderCode(String calendarOrderCode) {
            this.calendarOrderCode = calendarOrderCode;
        }

        public int getActualPrice() {
            return actualPrice;
        }

        public void setActualPrice(int actualPrice) {
            this.actualPrice = actualPrice;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getDiscountPrice() {
            return discountPrice;
        }

        public void setDiscountPrice(int discountPrice) {
            this.discountPrice = discountPrice;
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

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }
    }
}
