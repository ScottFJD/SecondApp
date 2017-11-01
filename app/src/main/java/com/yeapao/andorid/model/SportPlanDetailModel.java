package com.yeapao.andorid.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fujindong on 2017/10/24.
 */

public class SportPlanDetailModel implements Serializable{


    /**
     * errcode : 0
     * errmsg : ok
     * data : {"gender":"男","sportsCondition":"1","distance":213.4,"type":"标准","programmeId":18,"heartRate":"131～186","date":1,"BMI":"19.6","direction":"增肌","score":71,"programmeDetailList":[{"isMotion":"0","isWatchVideosOne":"0","isWatchVideosTwo":"0"}],"MediaList":[{"videoId":35,"title":"腿臀（中级）","mediaSrc":"http://yp-app-sp.oss-cn-hangzhou.aliyuncs.com/1/1.1/1/1508896733245.mp4?Expires=1508916962&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=Xp30uMg8YpuizBC5nOvfwgxWAQg%3D","imageUrl":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1508896659228.png?Expires=1508916962&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=TXk1O90cs611aotcQgvt2z%2BF8e8%3D","level":"1"},{"videoId":36,"title":"下肢伸展（初级）","mediaSrc":"http://yp-app-sp.oss-cn-hangzhou.aliyuncs.com/1/1.1/2/1508896896020.mp4?Expires=1508916962&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=yb8yPuO6CCUa8K77QaYUM%2FQ1R48%3D","imageUrl":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1508896823962.png?Expires=1508916962&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=rkHHw66dNT5wIG0R3PISx07ovFQ%3D","level":"2"}]}
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
         * gender : 男
         * sportsCondition : 1
         * distance : 213.4
         * type : 标准
         * programmeId : 18
         * heartRate : 131～186
         * date : 1
         * BMI : 19.6
         * myPlan : 1
         * direction : 增肌
         * score : 71
         * programmeDetailList : [{"isMotion":"0","isWatchVideosOne":"0","isWatchVideosTwo":"0"}]
         * MediaList : [{"videoId":35,"title":"腿臀（中级）","mediaSrc":"http://yp-app-sp.oss-cn-hangzhou.aliyuncs.com/1/1.1/1/1508896733245.mp4?Expires=1508916962&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=Xp30uMg8YpuizBC5nOvfwgxWAQg%3D","imageUrl":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1508896659228.png?Expires=1508916962&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=TXk1O90cs611aotcQgvt2z%2BF8e8%3D","level":"1"},{"videoId":36,"title":"下肢伸展（初级）","mediaSrc":"http://yp-app-sp.oss-cn-hangzhou.aliyuncs.com/1/1.1/2/1508896896020.mp4?Expires=1508916962&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=yb8yPuO6CCUa8K77QaYUM%2FQ1R48%3D","imageUrl":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1508896823962.png?Expires=1508916962&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=rkHHw66dNT5wIG0R3PISx07ovFQ%3D","level":"2"}]
         */

        private String gender;
        private String sportsCondition;
        private double distance;
        private String type;
        private int programmeId;
        private String heartRate;
        private int date;
        private String BMI;
        private String myPlan;
        private String direction;
        private int score;
        private List<ProgrammeDetailListBean> programmeDetailList;
        private List<MediaListBean> MediaList;



        public String getMyPlan() {
            return myPlan;
        }

        public void setMyPlan(String myPlan) {
            this.myPlan = myPlan;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getSportsCondition() {
            return sportsCondition;
        }

        public void setSportsCondition(String sportsCondition) {
            this.sportsCondition = sportsCondition;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getProgrammeId() {
            return programmeId;
        }

        public void setProgrammeId(int programmeId) {
            this.programmeId = programmeId;
        }

        public String getHeartRate() {
            return heartRate;
        }

        public void setHeartRate(String heartRate) {
            this.heartRate = heartRate;
        }

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }

        public String getBMI() {
            return BMI;
        }

        public void setBMI(String BMI) {
            this.BMI = BMI;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public List<ProgrammeDetailListBean> getProgrammeDetailList() {
            return programmeDetailList;
        }

        public void setProgrammeDetailList(List<ProgrammeDetailListBean> programmeDetailList) {
            this.programmeDetailList = programmeDetailList;
        }

        public List<MediaListBean> getMediaList() {
            return MediaList;
        }

        public void setMediaList(List<MediaListBean> MediaList) {
            this.MediaList = MediaList;
        }

        public static class ProgrammeDetailListBean implements Serializable{
            /**
             * isMotion : 0
             * isWatchVideosOne : 0
             * isWatchVideosTwo : 0
             */

            private int isMotion;
            private int isWatchVideosOne;
            private int isWatchVideosTwo;

            public int getIsMotion() {
                return isMotion;
            }

            public void setIsMotion(int isMotion) {
                this.isMotion = isMotion;
            }

            public int getIsWatchVideosOne() {
                return isWatchVideosOne;
            }

            public void setIsWatchVideosOne(int isWatchVideosOne) {
                this.isWatchVideosOne = isWatchVideosOne;
            }

            public int getIsWatchVideosTwo() {
                return isWatchVideosTwo;
            }

            public void setIsWatchVideosTwo(int isWatchVideosTwo) {
                this.isWatchVideosTwo = isWatchVideosTwo;
            }
        }

        public static class MediaListBean implements Serializable{
            /**
             * videoId : 35
             * title : 腿臀（中级）
             * mediaSrc : http://yp-app-sp.oss-cn-hangzhou.aliyuncs.com/1/1.1/1/1508896733245.mp4?Expires=1508916962&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=Xp30uMg8YpuizBC5nOvfwgxWAQg%3D
             * imageUrl : http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1508896659228.png?Expires=1508916962&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=TXk1O90cs611aotcQgvt2z%2BF8e8%3D
             * level : 1
             */

            private int videoId;
            private String title;
            private String mediaSrc;
            private String imageUrl;
            private String level;

            public int getVideoId() {
                return videoId;
            }

            public void setVideoId(int videoId) {
                this.videoId = videoId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getMediaSrc() {
                return mediaSrc;
            }

            public void setMediaSrc(String mediaSrc) {
                this.mediaSrc = mediaSrc;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }
        }
    }
}
