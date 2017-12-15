package com.yeapao.andorid.model;

import java.io.Serializable;

/**
 * Created by fujindong on 2017/12/15.
 */

public class CreateInclusiveModel implements Serializable{

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"date":"2017-12-15","privateUseOrderCode":"bc201712150000100017","dateStart":"2017-12-15 09:59:25","price":188,"prUseOrderId":17,"fieldAddress":"多功能动感操房","time":"09:00-10:00","selectEmployee":"1"}
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

    public static class DataBean implements Serializable{
        /**
         * date : 2017-12-15
         * privateUseOrderCode : bc201712150000100017
         * dateStart : 2017-12-15 09:59:25
         * price : 188
         * prUseOrderId : 17
         * fieldAddress : 多功能动感操房
         * time : 09:00-10:00
         * selectEmployee : 1
         */

        private String date;
        private String privateUseOrderCode;
        private String dateStart;
        private int price;
        private int prUseOrderId;
        private String fieldAddress;
        private String time;
        private String selectEmployee;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getPrivateUseOrderCode() {
            return privateUseOrderCode;
        }

        public void setPrivateUseOrderCode(String privateUseOrderCode) {
            this.privateUseOrderCode = privateUseOrderCode;
        }

        public String getDateStart() {
            return dateStart;
        }

        public void setDateStart(String dateStart) {
            this.dateStart = dateStart;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getPrUseOrderId() {
            return prUseOrderId;
        }

        public void setPrUseOrderId(int prUseOrderId) {
            this.prUseOrderId = prUseOrderId;
        }

        public String getFieldAddress() {
            return fieldAddress;
        }

        public void setFieldAddress(String fieldAddress) {
            this.fieldAddress = fieldAddress;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getSelectEmployee() {
            return selectEmployee;
        }

        public void setSelectEmployee(String selectEmployee) {
            this.selectEmployee = selectEmployee;
        }
    }
}
