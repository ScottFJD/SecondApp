package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/12/19.
 */

public class RecoveryDetailModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"subjectFeeType":"5节康复课程收费","types":"2","recoveryOrderCode":"kf201711270000100002","endDate":"2017-12-27 15:08:28","price":358,"shopAddress":"高新区运河路47号","recoveryOrderId":2,"startDate":"2017-11-27 15:08:28"}
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
         * subjectFeeType : 5节康复课程收费
         * types : 2
         * recoveryOrderCode : kf201711270000100002
         * endDate : 2017-12-27 15:08:28
         * price : 358
         * shopAddress : 高新区运河路47号
         * recoveryOrderId : 2
         * startDate : 2017-11-27 15:08:28
         */

        private String subjectFeeType;
        private String types;
        private String recoveryOrderCode;
        private String endDate;
        private double price;
        private String shopAddress;
        private int recoveryOrderId;
        private String startDate;

        public String getSubjectFeeType() {
            return subjectFeeType;
        }

        public void setSubjectFeeType(String subjectFeeType) {
            this.subjectFeeType = subjectFeeType;
        }

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
        }

        public String getRecoveryOrderCode() {
            return recoveryOrderCode;
        }

        public void setRecoveryOrderCode(String recoveryOrderCode) {
            this.recoveryOrderCode = recoveryOrderCode;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
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

        public int getRecoveryOrderId() {
            return recoveryOrderId;
        }

        public void setRecoveryOrderId(int recoveryOrderId) {
            this.recoveryOrderId = recoveryOrderId;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }
    }
}
