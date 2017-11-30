package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/11/28.
 */

public class DynamicLessonListModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"calendarId":1,"status":3,"headImage":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/null?Expires=1511857899&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=yMHndHpSy5GD%2BfCN7Jx6ovv4RyQ%3D","employeeName":"朱永磊","subjectName":"动感光影跑","week":"星期一","startTime":"09:00","endTime":"10:00","shopAddress":"高新区运河路47号","url":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511762602327.png?Expires=1511857899&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=eVAECnC6k6q0WuY%2Ft8N76shjJSg%3D","myCalendar":0},{"calendarId":2,"status":3,"headImage":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/null?Expires=1511857899&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=yMHndHpSy5GD%2BfCN7Jx6ovv4RyQ%3D","employeeName":"雨晴","subjectName":"动感光影跑","week":"星期一","startTime":"13:00","endTime":"14:00","shopAddress":"高新区运河路47号","url":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511762602327.png?Expires=1511857899&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=eVAECnC6k6q0WuY%2Ft8N76shjJSg%3D","myCalendar":0},{"calendarId":3,"status":3,"headImage":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/null?Expires=1511857899&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=yMHndHpSy5GD%2BfCN7Jx6ovv4RyQ%3D","employeeName":"马敏","subjectName":"动感健身操","week":"星期一","startTime":"15:00","endTime":"16:00","shopAddress":"高新区运河路47号","url":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511762889347.png?Expires=1511857899&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=IcmtmjeIAtrnrRdQW9IWWjEtbao%3D","myCalendar":0},{"calendarId":4,"status":1,"headImage":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/null?Expires=1511857899&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=yMHndHpSy5GD%2BfCN7Jx6ovv4RyQ%3D","employeeName":"Neo","subjectName":"动感光影跑","week":"星期一","startTime":"20:00","endTime":"21:00","shopAddress":"高新区运河路47号","url":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511762602327.png?Expires=1511857899&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=eVAECnC6k6q0WuY%2Ft8N76shjJSg%3D","myCalendar":0}]
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
         * calendarId : 1
         * status : 3
         * headImage : http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/null?Expires=1511857899&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=yMHndHpSy5GD%2BfCN7Jx6ovv4RyQ%3D
         * employeeName : 朱永磊
         * subjectName : 动感光影跑
         * week : 星期一
         * startTime : 09:00
         * endTime : 10:00
         * shopAddress : 高新区运河路47号
         * url : http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511762602327.png?Expires=1511857899&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=eVAECnC6k6q0WuY%2Ft8N76shjJSg%3D
         * myCalendar : 0
         */

        private int calendarId;
        private int status;
        private String headImage;
        private String employeeName;
        private String subjectName;
        private String week;
        private String startTime;
        private String endTime;
        private String shopAddress;
        private String url;
        private int myCalendar;

        public int getCalendarId() {
            return calendarId;
        }

        public void setCalendarId(int calendarId) {
            this.calendarId = calendarId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getShopAddress() {
            return shopAddress;
        }

        public void setShopAddress(String shopAddress) {
            this.shopAddress = shopAddress;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getMyCalendar() {
            return myCalendar;
        }

        public void setMyCalendar(int myCalendar) {
            this.myCalendar = myCalendar;
        }
    }
}
