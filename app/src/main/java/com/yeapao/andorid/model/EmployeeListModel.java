package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/12/8.
 */

public class EmployeeListModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"employeeId":4,"employeeName":"Neo","jobTitle":"高级健身教练","headImage":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1512107887940.jpeg?Expires=1512727478&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=KEhux1iJOhlQ6qwXkC5hhuvvRpM%3D","information":["1.国际健身协会有氧训练师认证","2.美国有氧体适能协会认证的专业健身教练","3.国家认证高级康复师","4.国际级体能教练"],"highServerTime":40},{"employeeId":3,"employeeName":"马敏","jobTitle":"中级健身教练","headImage":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1512107874583.jpeg?Expires=1512727478&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=UsEsjyDp4028bOnCW%2FAj6%2B9JTI8%3D","information":["1.国际健身协会有氧训练师认证","2.美国有氧体适能协会认证的专业健身教练","3.国家认证高级康复师","4.国际级体能教练"],"highServerTime":30},{"employeeId":2,"employeeName":"雨晴","jobTitle":"高级健身教练","headImage":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1512107855996.jpeg?Expires=1512727478&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=tSmuu7hDobG3LMW5w%2FwzyvWSowI%3D","information":["1.国际健身协会有氧训练师认证","2.美国有氧体适能协会认证的专业健身教练","3.国家认证高级康复师","4.国际级体能教练"],"highServerTime":20},{"employeeId":1,"employeeName":"朱永磊","jobTitle":"高级健身教练","headImage":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1512107739972.jpeg?Expires=1512727478&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=TGUcO1ksPU4sZ1JSgIoMXaDdJvo%3D","information":["1.国际健身协会有氧训练师认证","2.美国有氧体适能协会认证的专业健身教练","3.国家认证高级康复师","4.国际级体能教练"],"highServerTime":10},{"employeeId":5,"employeeName":"张三","jobTitle":"高级健身教练","headImage":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1512030847159.png?Expires=1512727478&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=qIcGgKr9HbyFThnK5ZrAsXIr0LI%3D","information":["1.国际健身协会有氧训练师认证","2.美国有氧体适能协会认证的专业健身教练","3.国家认证高级康复师","4.国际级体能教练","\n\n"],"highServerTime":null}]
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
         * employeeId : 4
         * employeeName : Neo
         * jobTitle : 高级健身教练
         * headImage : http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1512107887940.jpeg?Expires=1512727478&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=KEhux1iJOhlQ6qwXkC5hhuvvRpM%3D
         * information : ["1.国际健身协会有氧训练师认证","2.美国有氧体适能协会认证的专业健身教练","3.国家认证高级康复师","4.国际级体能教练"]
         * highServerTime : 40
         */

        private int employeeId;
        private String employeeName;
        private String jobTitle;
        private String headImage;
        private int highServerTime;
        private List<String> information;

        public int getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(int employeeId) {
            this.employeeId = employeeId;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
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

        public int getHighServerTime() {
            return highServerTime;
        }

        public void setHighServerTime(int highServerTime) {
            this.highServerTime = highServerTime;
        }

        public List<String> getInformation() {
            return information;
        }

        public void setInformation(List<String> information) {
            this.information = information;
        }
    }
}
