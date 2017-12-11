package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/12/8.
 */

public class EmployeeDetailModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"haveCoach":0,"employeeFeeOut":[{"subjectFeeId":37,"feeName":"高级训练收费","subjectNum":3,"price":98,"dateRange":1},{"subjectFeeId":38,"feeName":"高级训练收费","subjectNum":12,"price":358,"dateRange":4},{"subjectFeeId":39,"feeName":"高级训练收费","subjectNum":36,"price":798,"dateRange":12},{"subjectFeeId":40,"feeName":"高级训练收费","subjectNum":72,"price":1398,"dateRange":24}],"employeeDetailOut":{"employeeId":1,"coachName":"朱永磊","jobTitle":"高级健身教练","information":["1.国际健身协会有氧训练师认证","2.美国有氧体适能协会认证的专业健身教练","3.国家认证高级康复师","4.国际级体能教练"],"headImage":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1512107739972.jpeg?Expires=1512730734&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=RK5kEeStBVEiqpnJKlcu5C60A9s%3D","url":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1512107799100.png?Expires=1512730734&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=TZVMeiEftHQknXeO3699yZSvd0U%3D","shopAddress":"高新区运河路47号"}}
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
         * haveCoach : 0
         * employeeFeeOut : [{"subjectFeeId":37,"feeName":"高级训练收费","subjectNum":3,"price":98,"dateRange":1},{"subjectFeeId":38,"feeName":"高级训练收费","subjectNum":12,"price":358,"dateRange":4},{"subjectFeeId":39,"feeName":"高级训练收费","subjectNum":36,"price":798,"dateRange":12},{"subjectFeeId":40,"feeName":"高级训练收费","subjectNum":72,"price":1398,"dateRange":24}]
         * employeeDetailOut : {"employeeId":1,"coachName":"朱永磊","jobTitle":"高级健身教练","information":["1.国际健身协会有氧训练师认证","2.美国有氧体适能协会认证的专业健身教练","3.国家认证高级康复师","4.国际级体能教练"],"headImage":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1512107739972.jpeg?Expires=1512730734&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=RK5kEeStBVEiqpnJKlcu5C60A9s%3D","url":"http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1512107799100.png?Expires=1512730734&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=TZVMeiEftHQknXeO3699yZSvd0U%3D","shopAddress":"高新区运河路47号"}
         */

        private int haveCoach;
        private EmployeeDetailOutBean employeeDetailOut;
        private List<EmployeeFeeOutBean> employeeFeeOut;

        public int getHaveCoach() {
            return haveCoach;
        }

        public void setHaveCoach(int haveCoach) {
            this.haveCoach = haveCoach;
        }

        public EmployeeDetailOutBean getEmployeeDetailOut() {
            return employeeDetailOut;
        }

        public void setEmployeeDetailOut(EmployeeDetailOutBean employeeDetailOut) {
            this.employeeDetailOut = employeeDetailOut;
        }

        public List<EmployeeFeeOutBean> getEmployeeFeeOut() {
            return employeeFeeOut;
        }

        public void setEmployeeFeeOut(List<EmployeeFeeOutBean> employeeFeeOut) {
            this.employeeFeeOut = employeeFeeOut;
        }

        public static class EmployeeDetailOutBean {
            /**
             * employeeId : 1
             * coachName : 朱永磊
             * jobTitle : 高级健身教练
             * information : ["1.国际健身协会有氧训练师认证","2.美国有氧体适能协会认证的专业健身教练","3.国家认证高级康复师","4.国际级体能教练"]
             * headImage : http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1512107739972.jpeg?Expires=1512730734&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=RK5kEeStBVEiqpnJKlcu5C60A9s%3D
             * url : http://yp-all-tp.oss-cn-hangzhou.aliyuncs.com/image/1512107799100.png?Expires=1512730734&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=TZVMeiEftHQknXeO3699yZSvd0U%3D
             * shopAddress : 高新区运河路47号
             */

            private int employeeId;
            private String coachName;
            private String jobTitle;
            private String headImage;
            private String url;
            private String shopAddress;
            private List<String> information;

            public int getEmployeeId() {
                return employeeId;
            }

            public void setEmployeeId(int employeeId) {
                this.employeeId = employeeId;
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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getShopAddress() {
                return shopAddress;
            }

            public void setShopAddress(String shopAddress) {
                this.shopAddress = shopAddress;
            }

            public List<String> getInformation() {
                return information;
            }

            public void setInformation(List<String> information) {
                this.information = information;
            }
        }

        public static class EmployeeFeeOutBean {
            /**
             * subjectFeeId : 37
             * feeName : 高级训练收费
             * subjectNum : 3
             * price : 98
             * dateRange : 1
             */

            private int subjectFeeId;
            private String feeName;
            private int subjectNum;
            private float price;
            private int dateRange;

            public int getSubjectFeeId() {
                return subjectFeeId;
            }

            public void setSubjectFeeId(int subjectFeeId) {
                this.subjectFeeId = subjectFeeId;
            }

            public String getFeeName() {
                return feeName;
            }

            public void setFeeName(String feeName) {
                this.feeName = feeName;
            }

            public int getSubjectNum() {
                return subjectNum;
            }

            public void setSubjectNum(int subjectNum) {
                this.subjectNum = subjectNum;
            }

            public float getPrice() {
                return price;
            }

            public void setPrice(float price) {
                this.price = price;
            }

            public int getDateRange() {
                return dateRange;
            }

            public void setDateRange(int dateRange) {
                this.dateRange = dateRange;
            }
        }
    }
}
