package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/11/28.
 */

public class StationMainBannerModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"url":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511247446933.png?Expires=1511856488&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=rJ%2FJBUZElj%2FzPGrlk0rAfldXUFQ%3D","type":1,"headImage":null},{"url":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511248986914.png?Expires=1511856488&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=H17EEA81dJ1OkPHkqVnjbsCDgRs%3D","type":2,"headImage":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/null?Expires=1511856488&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=wGsMIZ30k%2BZBJoi1CdTWXcLVJrA%3D"},{"url":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511248996830.png?Expires=1511856488&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=7mq7y1MjbaVmucfwX7xenim%2F2AE%3D","type":3,"headImage":null}]
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
         * url : http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511247446933.png?Expires=1511856488&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=rJ%2FJBUZElj%2FzPGrlk0rAfldXUFQ%3D
         * type : 1
         * headImage : null
         */

        private String url;
        private int type;
        private String headImage;

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
    }
}
