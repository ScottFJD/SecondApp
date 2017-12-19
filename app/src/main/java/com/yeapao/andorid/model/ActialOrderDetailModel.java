package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/9/15.
 */

public class ActialOrderDetailModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"actualOrderId":"1","price":"12","time":"60","warehouseName":"0210001","actualOrdersCode":"sj201708290000100001"}
     */

    private String errcode;
    private String errmsg;
    private DataBean data;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
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
         * actualOrderId : 1
         * price : 12
         * time : 60
         * warehouseName : 0210001
         * actualOrdersCode : sj201708290000100001
         */

        private String actualOrderId;
        private double price;
        private String time;
        private String warehouseName;
        private String actualOrdersCode;
        private double discountPrice;
        private String isOpen;
        private String discountName;

        public double getDiscountPrice() {
            return discountPrice;
        }

        public void setDiscountPrice(double discountPrice) {
            this.discountPrice = discountPrice;
        }

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

        public String getActualOrderId() {
            return actualOrderId;
        }

        public void setActualOrderId(String actualOrderId) {
            this.actualOrderId = actualOrderId;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public String getActualOrdersCode() {
            return actualOrdersCode;
        }

        public void setActualOrdersCode(String actualOrdersCode) {
            this.actualOrdersCode = actualOrdersCode;
        }
    }
}
