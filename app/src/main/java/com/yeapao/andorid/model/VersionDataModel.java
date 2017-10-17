package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/10/17.
 */

public class VersionDataModel {
    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"id":1,"version":"1.0.0.0","url":"http://imtt.dd.qq.com/16891/922DA35A00FADB01CCCD974D6985513D.apk?fsname=com.yeapao.andorid_1.0_1.apk&csr=1bbd","type":"android"}]
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
         * version : 1.0.0.0
         * url : http://imtt.dd.qq.com/16891/922DA35A00FADB01CCCD974D6985513D.apk?fsname=com.yeapao.andorid_1.0_1.apk&csr=1bbd
         * type : android
         */

        private int id;
        private String version;
        private String url;
        private String type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
