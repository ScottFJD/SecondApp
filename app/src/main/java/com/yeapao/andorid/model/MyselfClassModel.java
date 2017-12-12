package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/12/12.
 */

public class MyselfClassModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"todayIsClassList":[],"myClassList":[{"id":276,"date":"2017-12-07","time":"10:30-11:30","status":"3","subjectName":"动感健身操","shopAddress":"高新区运河路47号"},{"id":291,"date":"2017-12-06","time":"19:00-20:00","status":"0","subjectName":"动感光影跑","shopAddress":"高新区运河路47号"},{"id":285,"date":"2017-12-05","time":"19:00-20:00","status":"0","subjectName":"动感光影跑","shopAddress":"高新区运河路47号"},{"id":245,"date":"2017-12-05","time":"13:30-14:30","status":"3","subjectName":"动感光影跑","shopAddress":"高新区运河路47号"}]}
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
        private List<MyClassListBean> todayIsClassList;
        private List<MyClassListBean> myClassList;

        public List<MyClassListBean> getTodayIsClassList() {
            return todayIsClassList;
        }

        public void setTodayIsClassList(List<MyClassListBean> todayIsClassList) {
            this.todayIsClassList = todayIsClassList;
        }

        public List<MyClassListBean> getMyClassList() {
            return myClassList;
        }

        public void setMyClassList(List<MyClassListBean> myClassList) {
            this.myClassList = myClassList;
        }

        public static class MyClassListBean {
            /**
             * id : 276
             * date : 2017-12-07
             * time : 10:30-11:30
             * status : 3
             * subjectName : 动感健身操
             * shopAddress : 高新区运河路47号
             */

            private int id;
            private String date;
            private String time;
            private String status;
            private String subjectName;
            private String shopAddress;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getSubjectName() {
                return subjectName;
            }

            public void setSubjectName(String subjectName) {
                this.subjectName = subjectName;
            }

            public String getShopAddress() {
                return shopAddress;
            }

            public void setShopAddress(String shopAddress) {
                this.shopAddress = shopAddress;
            }
        }
    }
}
