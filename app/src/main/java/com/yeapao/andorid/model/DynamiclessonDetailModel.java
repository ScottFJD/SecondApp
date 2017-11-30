package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/11/28.
 */

public class DynamiclessonDetailModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : {"url":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511762602327.png?Expires=1511951517&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=ZERPQO81ksZQMxfkZmH0t%2BdC3L0%3D","date":"2017-11-27","startTime":"09:00","endTime":"10:00","shopAddress":"高新区运河路47号","information":["1.有点胖.","2.非常好.","3.还不错."],"coachName":"朱永磊","jobTitle":"高级健身教练","headImage":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/null?Expires=1511951517&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=98cRw2ImNiCh92LiVKummdAKBWY%3D","note":"跟随动感的灯光和背景跑动起来","attention":"别跑死","reservation":5,"maxMember":20,"status":3,"myCalendar":null}
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
         * url : http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1511762602327.png?Expires=1511951517&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=ZERPQO81ksZQMxfkZmH0t%2BdC3L0%3D
         * date : 2017-11-27
         * startTime : 09:00
         * endTime : 10:00
         * shopAddress : 高新区运河路47号
         * information : ["1.有点胖.","2.非常好.","3.还不错."]
         * coachName : 朱永磊
         * jobTitle : 高级健身教练
         * headImage : http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/null?Expires=1511951517&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=98cRw2ImNiCh92LiVKummdAKBWY%3D
         * note : 跟随动感的灯光和背景跑动起来
         * attention : 别跑死
         * reservation : 5
         * maxMember : 20
         * status : 3
         * myCalendar : null
         */

        private String url;
        private String date;
        private String startTime;
        private String endTime;
        private String shopAddress;
        private String coachName;
        private String jobTitle;
        private String headImage;
        private String note;
        private String attention;
        private int reservation;
        private int maxMember;
        private int status;
        private String myCalendar;
        private List<String> information;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
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

        public String getCoachName() {
            return coachName;
        }

        public void setCoachName(String coachName) {
            this.coachName = coachName;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getAttention() {
            return attention;
        }

        public void setAttention(String attention) {
            this.attention = attention;
        }

        public int getReservation() {
            return reservation;
        }

        public void setReservation(int reservation) {
            this.reservation = reservation;
        }

        public int getMaxMember() {
            return maxMember;
        }

        public void setMaxMember(int maxMember) {
            this.maxMember = maxMember;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMyCalendar() {
            return myCalendar;
        }

        public void setMyCalendar(String myCalendar) {
            this.myCalendar = myCalendar;
        }

        public List<String> getInformation() {
            return information;
        }

        public void setInformation(List<String> information) {
            this.information = information;
        }
    }
}
