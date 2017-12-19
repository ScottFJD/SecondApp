package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/12/19.
 */

public class HighLessonModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"subjectFeeType":"4周高阶训练","emHighOrderId":2,"emHighOrderCode":"gj201711270000100002","types":"1","price":358,"shopAddress":"高新区运河路47号","startDate":"2017-11-27 14:16:38"}
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
         * subjectFeeType : 4周高阶训练
         * emHighOrderId : 2
         * emHighOrderCode : gj201711270000100002
         * types : 1
         * price : 358
         * shopAddress : 高新区运河路47号
         * startDate : 2017-11-27 14:16:38
         */

        private String subjectFeeType;
        private int emHighOrderId;
        private String emHighOrderCode;
        private String types;
        private double price;
        private String shopAddress;
        private String startDate;

        public String getSubjectFeeType() {
            return subjectFeeType;
        }

        public void setSubjectFeeType(String subjectFeeType) {
            this.subjectFeeType = subjectFeeType;
        }

        public int getEmHighOrderId() {
            return emHighOrderId;
        }

        public void setEmHighOrderId(int emHighOrderId) {
            this.emHighOrderId = emHighOrderId;
        }

        public String getEmHighOrderCode() {
            return emHighOrderCode;
        }

        public void setEmHighOrderCode(String emHighOrderCode) {
            this.emHighOrderCode = emHighOrderCode;
        }

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
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
