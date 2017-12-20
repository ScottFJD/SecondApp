package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/12/20.
 */

public class InclusiveOrderDetailModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"prUseOrderCode":"bc201712150000100032","date":"2017-12-15","types":"1","field":"多功能动感操房","price":188,"prUserOrderId":32,"time":"22:00-23:00","startDate":"2017-12-15 11:24:54","selectEmployee":"1"}
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
         * prUseOrderCode : bc201712150000100032
         * date : 2017-12-15
         * types : 1
         * field : 多功能动感操房
         * price : 188
         * prUserOrderId : 32
         * time : 22:00-23:00
         * startDate : 2017-12-15 11:24:54
         * selectEmployee : 1
         */

        private String prUseOrderCode;
        private String date;
        private String types;
        private String field;
        private double price;
        private int prUserOrderId;
        private String time;
        private String startDate;
        private String selectEmployee;

        public String getPrUseOrderCode() {
            return prUseOrderCode;
        }

        public void setPrUseOrderCode(String prUseOrderCode) {
            this.prUseOrderCode = prUseOrderCode;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getPrUserOrderId() {
            return prUserOrderId;
        }

        public void setPrUserOrderId(int prUserOrderId) {
            this.prUserOrderId = prUserOrderId;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getSelectEmployee() {
            return selectEmployee;
        }

        public void setSelectEmployee(String selectEmployee) {
            this.selectEmployee = selectEmployee;
        }
    }
}
