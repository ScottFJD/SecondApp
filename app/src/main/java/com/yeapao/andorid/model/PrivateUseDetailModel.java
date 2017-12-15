package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/12/14.
 */

public class PrivateUseDetailModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : {"id":1,"url":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/null?Expires=1513242928&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=nrH0hNVbrG%2BX%2BSu%2Ft5VMk3J699Q%3D","fliedPrice":128,"employeePrice":60}
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
         * id : 1
         * url : http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/null?Expires=1513242928&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=nrH0hNVbrG%2BX%2BSu%2Ft5VMk3J699Q%3D
         * fliedPrice : 128
         * employeePrice : 60
         */

        private int id;
        private String url;
        private int fliedPrice;
        private int employeePrice;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getFliedPrice() {
            return fliedPrice;
        }

        public void setFliedPrice(int fliedPrice) {
            this.fliedPrice = fliedPrice;
        }

        public int getEmployeePrice() {
            return employeePrice;
        }

        public void setEmployeePrice(int employeePrice) {
            this.employeePrice = employeePrice;
        }
    }
}
