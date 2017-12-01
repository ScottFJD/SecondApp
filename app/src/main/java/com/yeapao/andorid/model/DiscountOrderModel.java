package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/12/1.
 */

public class DiscountOrderModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"price":199,"disCardOrderCode":"dz201712010000100003"}
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
         * price : 199
         * disCardOrderCode : dz201712010000100003
         */

        private int price;
        private String disCardOrderCode;

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getDisCardOrderCode() {
            return disCardOrderCode;
        }

        public void setDisCardOrderCode(String disCardOrderCode) {
            this.disCardOrderCode = disCardOrderCode;
        }
    }
}
