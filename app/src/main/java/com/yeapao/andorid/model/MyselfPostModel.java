package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/10/12.
 */

public class MyselfPostModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : {"totalPage":1,"communityList":[{"images":[{"imageUrl":"http://47.92.113.97:8080/yepao/image/51/ce/51ce1e3ddc894409b52ef91be34c85af.jpg"}],"gender":"女","createTime":1507529704563,"grade":1,"headUrl":"http://47.92.113.97:8080/yepao/image/head01.png","userName":"Cherry","type":"community","communityId":5,"content":"中秋节","master":"0"}],"page":0}
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
         * totalPage : 1
         * communityList : [{"images":[{"imageUrl":"http://47.92.113.97:8080/yepao/image/51/ce/51ce1e3ddc894409b52ef91be34c85af.jpg"}],"gender":"女","createTime":1507529704563,"grade":1,"headUrl":"http://47.92.113.97:8080/yepao/image/head01.png","userName":"Cherry","type":"community","communityId":5,"content":"中秋节","master":"0"}]
         * page : 0
         */

        private int totalPage;
        private int page;
        private List<CommunityListBean> communityList;

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<CommunityListBean> getCommunityList() {
            return communityList;
        }

        public void setCommunityList(List<CommunityListBean> communityList) {
            this.communityList = communityList;
        }

        public static class CommunityListBean {
            /**
             * images : [{"imageUrl":"http://47.92.113.97:8080/yepao/image/51/ce/51ce1e3ddc894409b52ef91be34c85af.jpg"}]
             * gender : 女
             * createTime : 1507529704563
             * grade : 1
             * headUrl : http://47.92.113.97:8080/yepao/image/head01.png
             * userName : Cherry
             * type : community
             * communityId : 5
             * content : 中秋节
             * master : 0
             */

            private String gender;
            private long createTime;
            private int grade;
            private String headUrl;
            private String userName;
            private String type;
            private int communityId;
            private String content;
            private String master;
            private List<CircleListModel.DataBean.CommunityListBean.imagesUrl> images;

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getGrade() {
                return grade;
            }

            public void setGrade(int grade) {
                this.grade = grade;
            }

            public String getHeadUrl() {
                return headUrl;
            }

            public void setHeadUrl(String headUrl) {
                this.headUrl = headUrl;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getCommunityId() {
                return communityId;
            }

            public void setCommunityId(int communityId) {
                this.communityId = communityId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getMaster() {
                return master;
            }

            public void setMaster(String master) {
                this.master = master;
            }

            public List<CircleListModel.DataBean.CommunityListBean.imagesUrl> getImages() {
                return images;
            }

            public void setImages(List<CircleListModel.DataBean.CommunityListBean.imagesUrl> images) {
                this.images = images;
            }

            public static class ImagesBean {
                /**
                 * imageUrl : http://47.92.113.97:8080/yepao/image/51/ce/51ce1e3ddc894409b52ef91be34c85af.jpg
                 */

                private String imageUrl;

                public String getImageUrl() {
                    return imageUrl;
                }

                public void setImageUrl(String imageUrl) {
                    this.imageUrl = imageUrl;
                }
            }
        }
    }
}
