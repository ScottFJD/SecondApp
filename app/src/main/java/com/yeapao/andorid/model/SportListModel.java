package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/10/13.
 */

public class SportListModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"rankingList":[{"ranking":1,"name":"猪瘟","head":"http://47.92.113.97:8008/yepao/image/head05.png","duration":739,"customerId":11},{"ranking":2,"name":"neo","head":"http://47.92.113.97:8008/yepao/image/bb/f9/bbf915365a16410eb53fcd7d42a1fa20.png","duration":74,"customerId":5},{"ranking":3,"name":"Scott","head":"http://47.92.113.97:8008/yepao/image/c9/cf/c9cfaf1fe0e844eab4cceb2c4e824bbd.jpg","duration":71,"customerId":1},{"ranking":4,"name":"念安","head":"http://47.92.113.97:8008/yepao/image/head01.png","duration":37,"customerId":27},{"ranking":5,"name":"许庆涛","head":"http://47.92.113.97:8008/yepao/image/head04.png","duration":31,"customerId":8},{"ranking":6,"name":"方之","head":"http://47.92.113.97:8008/yepao/image/0e/22/0e224394780a48f29e7149fbf2c7ac17.png","duration":22,"customerId":4},{"ranking":7,"name":"马敏","head":"http://47.92.113.97:8008/yepao/image/2c/b6/2cb611403d144c1bac6025839a8873a2.jpg","duration":12,"customerId":7},{"ranking":8,"name":"小晴晴","head":"http://47.92.113.97:8008/yepao/image/1e/04/1e040440bcf34540b6b0bb6fac1e0666.png","duration":6,"customerId":3},{"ranking":9,"name":"Cherry","head":"http://47.92.113.97:8008/yepao/image/head01.png","duration":2,"customerId":28}],"head":"http://47.92.113.97:8008/yepao/image/head01.png","name":"Cherry","duration":2,"ranking":1,"customerId":28}
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
         * rankingList : [{"ranking":1,"name":"猪瘟","head":"http://47.92.113.97:8008/yepao/image/head05.png","duration":739,"customerId":11},{"ranking":2,"name":"neo","head":"http://47.92.113.97:8008/yepao/image/bb/f9/bbf915365a16410eb53fcd7d42a1fa20.png","duration":74,"customerId":5},{"ranking":3,"name":"Scott","head":"http://47.92.113.97:8008/yepao/image/c9/cf/c9cfaf1fe0e844eab4cceb2c4e824bbd.jpg","duration":71,"customerId":1},{"ranking":4,"name":"念安","head":"http://47.92.113.97:8008/yepao/image/head01.png","duration":37,"customerId":27},{"ranking":5,"name":"许庆涛","head":"http://47.92.113.97:8008/yepao/image/head04.png","duration":31,"customerId":8},{"ranking":6,"name":"方之","head":"http://47.92.113.97:8008/yepao/image/0e/22/0e224394780a48f29e7149fbf2c7ac17.png","duration":22,"customerId":4},{"ranking":7,"name":"马敏","head":"http://47.92.113.97:8008/yepao/image/2c/b6/2cb611403d144c1bac6025839a8873a2.jpg","duration":12,"customerId":7},{"ranking":8,"name":"小晴晴","head":"http://47.92.113.97:8008/yepao/image/1e/04/1e040440bcf34540b6b0bb6fac1e0666.png","duration":6,"customerId":3},{"ranking":9,"name":"Cherry","head":"http://47.92.113.97:8008/yepao/image/head01.png","duration":2,"customerId":28}]
         * head : http://47.92.113.97:8008/yepao/image/head01.png
         * name : Cherry
         * duration : 2
         * ranking : 1
         * customerId : 28
         */

        private String head;
        private String name;
        private int duration;
        private int ranking;
        private int customerId;
        private List<RankingListBean> rankingList;

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getRanking() {
            return ranking;
        }

        public void setRanking(int ranking) {
            this.ranking = ranking;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public List<RankingListBean> getRankingList() {
            return rankingList;
        }

        public void setRankingList(List<RankingListBean> rankingList) {
            this.rankingList = rankingList;
        }

        public static class RankingListBean {
            /**
             * ranking : 1
             * name : 猪瘟
             * head : http://47.92.113.97:8008/yepao/image/head05.png
             * duration : 739
             * customerId : 11
             */

            private int ranking;
            private String name;
            private String head;
            private long duration;
            private int customerId;

            public int getRanking() {
                return ranking;
            }

            public void setRanking(int ranking) {
                this.ranking = ranking;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }

            public long getDuration() {
                return duration;
            }

            public void setDuration(long duration) {
                this.duration = duration;
            }

            public int getCustomerId() {
                return customerId;
            }

            public void setCustomerId(int customerId) {
                this.customerId = customerId;
            }
        }
    }
}
