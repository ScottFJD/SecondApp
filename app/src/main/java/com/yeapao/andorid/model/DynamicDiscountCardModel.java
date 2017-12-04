package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/12/1.
 */

public class DynamicDiscountCardModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"isOpen":"1","endDate":"2018-11-24 15:40:05","url":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511398986920.png?Expires=1512101047&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=QvMjRgVUrCsHIj0LkW7KhYu6J2E%3D"}
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
         * isOpen : 1
         * endDate : 2018-11-24 15:40:05
         * url : http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511398986920.png?Expires=1512101047&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=QvMjRgVUrCsHIj0LkW7KhYu6J2E%3D
         */

        private String isOpen;
        private String endDate;
        private String startDate;
        private String url;
        private int price;


        public String getStartDate() {
            return startDate;
        }
        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }
        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getIsOpen() {
            return isOpen;
        }

        public void setIsOpen(String isOpen) {
            this.isOpen = isOpen;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
