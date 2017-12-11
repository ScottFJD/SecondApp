package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/12/11.
 */

public class HighEmOrderModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : {"empHighOrderCode":"gj201712110000100001","price":199,"shopAddress":"高新区运河路47号","subjectType":"1周高阶训练收费","startDate":"2017-12-11 14:35:50"}
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
         * empHighOrderCode : gj201712110000100001
         * price : 199
         * shopAddress : 高新区运河路47号
         * subjectType : 1周高阶训练收费
         * startDate : 2017-12-11 14:35:50
         */

        private String empHighOrderCode;
        private float price;
        private String shopAddress;
        private String subjectType;
        private String startDate;
        private String endDate;
        private String recoveryOrderCode;

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getRecoveryOrderCode() {
            return recoveryOrderCode;
        }

        public void setRecoveryOrderCode(String recoveryOrderCode) {
            this.recoveryOrderCode = recoveryOrderCode;
        }

        public String getEmpHighOrderCode() {
            return empHighOrderCode;
        }

        public void setEmpHighOrderCode(String empHighOrderCode) {
            this.empHighOrderCode = empHighOrderCode;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public String getShopAddress() {
            return shopAddress;
        }

        public void setShopAddress(String shopAddress) {
            this.shopAddress = shopAddress;
        }

        public String getSubjectType() {
            return subjectType;
        }

        public void setSubjectType(String subjectType) {
            this.subjectType = subjectType;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }
    }
}
