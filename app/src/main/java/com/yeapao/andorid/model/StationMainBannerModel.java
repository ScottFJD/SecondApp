package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/11/28.
 */

public class StationMainBannerModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"url":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511247446933.png?Expires=1514871208&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=XIfZ0dkSsFa6At%2B52%2FW2hoChlWk%3D","type":1,"headImage":null,"hasBeenUsed":null,"totalNum":null,"orderId":null,"coachName":null},{"url":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511248986914.png?Expires=1514871208&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=xUP1lYwmjixOFVaIMBJo3XXOVuM%3D","type":2,"headImage":null,"hasBeenUsed":null,"totalNum":null,"orderId":null,"coachName":null},{"url":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511248996830.png?Expires=1514871208&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=tdQIPLA%2FfFTTVYPS0rg%2Flr8cfWc%3D","type":3,"headImage":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1512107887940.jpeg?Expires=1514871208&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=%2FaWPOZipfT9%2FGbERjLGSnN1fphQ%3D","hasBeenUsed":0,"totalNum":1,"orderId":11,"coachName":"Neo"}]
     */

    private int errcode;
    private String errmsg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * url : http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511247446933.png?Expires=1514871208&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=XIfZ0dkSsFa6At%2B52%2FW2hoChlWk%3D
         * type : 1
         * headImage : null
         * hasBeenUsed : null
         * totalNum : null
         * orderId : null
         * coachName : null
         */

        private String url;
        private int type;
        private String headImage;
        private int hasBeenUsed;
        private int totalNum;
        private int orderId;
        private String coachName;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public int getHasBeenUsed() {
            return hasBeenUsed;
        }

        public void setHasBeenUsed(int hasBeenUsed) {
            this.hasBeenUsed = hasBeenUsed;
        }

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getCoachName() {
            return coachName;
        }

        public void setCoachName(String coachName) {
            this.coachName = coachName;
        }
    }
}
