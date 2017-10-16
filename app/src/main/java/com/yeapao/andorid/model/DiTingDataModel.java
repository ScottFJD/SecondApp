package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/10/16.
 */

public class DiTingDataModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"actionOption":"action_0","message":"success","uuid":"28","available":"0","answer":"拜拜~","username":"18817317126","question":"再见"}
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
         * actionOption : action_0
         * message : success
         * uuid : 28
         * available : 0
         * answer : 拜拜~
         * username : 18817317126
         * question : 再见
         */

        private String actionOption;
        private String message;
        private String uuid;
        private String available;
        private String answer;
        private String username;
        private String question;

        public String getActionOption() {
            return actionOption;
        }

        public void setActionOption(String actionOption) {
            this.actionOption = actionOption;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getAvailable() {
            return available;
        }

        public void setAvailable(String available) {
            this.available = available;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }
    }
}
