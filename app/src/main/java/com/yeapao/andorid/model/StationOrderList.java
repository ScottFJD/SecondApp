package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/12/1.
 */

public class StationOrderList {

    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"id":1,"types":"1","price":98,"subjectType":"动感课程","status":"2","url":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511247446933.png?Expires=1512126077&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=s1q4JxDZyfLLVnuBdECBAHEHqbg%3D","subjectName":"动感光影跑"},{"id":1,"types":"2","price":98,"subjectType":"高阶服务","status":"2","url":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511248986914.png?Expires=1512126077&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=DaCbd%2FYkOiBXygv0FL61Me7Ax%2BU%3D","subjectName":"1周高级训练收费"},{"id":1,"types":"3","price":358,"subjectType":"康复服务","status":"2","url":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511248996830.png?Expires=1512126077&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=b4QdFGXmjbL%2BN8W9AkHJ%2F%2FW3r6w%3D","subjectName":"5节康复课程收费"},{"id":1,"types":"4","price":188,"subjectType":"土豪包场","status":"2","url":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511248996830.png?Expires=1512126077&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=b4QdFGXmjbL%2BN8W9AkHJ%2F%2FW3r6w%3D","subjectName":"搏击操"}]
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
         * id : 1
         * types : 1
         * price : 98
         * subjectType : 动感课程
         * status : 2
         * url : http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511247446933.png?Expires=1512126077&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=s1q4JxDZyfLLVnuBdECBAHEHqbg%3D
         * subjectName : 动感光影跑
         */

        private int id;
        private String types;
        private float price;
        private String subjectType;
        private String status;
        private String url;
        private String subjectName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public String getSubjectType() {
            return subjectType;
        }

        public void setSubjectType(String subjectType) {
            this.subjectType = subjectType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }
    }
}
