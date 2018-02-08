package com.yeapao.andorid.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fujindong on 06/02/2018.
 */

public class FitPlanDetailModel implements Serializable{


    /**
     * rs : Y
     * resultMap : {"errCode":0,"errMsg":"ok","warmUpMediaList":[{"img":"tpsp/1516794542540.jpeg","srcString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516794551804.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=Wyih5qd299DwC%2BoG0QHzzOYbXY8%3D","src":"tpsp/1516794551804.mp4","name":"开合跳 117","imgString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516794542540.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=P%2FaC%2Blpo%2BahwPZq0i4n%2FKJhENfg%3D","id":"5a6872c53782b1234c927681","type":"热身视频","create_date":"2018-01-24 19:49:25.0","update_date":"2018-01-24 19:49:25.0"},{"img":"tpsp/1517473292890.jpeg","srcString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1517473350092.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=2mjwgTYNk%2F2KaSbSrqI9xObPPVM%3D","src":"tpsp/1517473350092.mp4","name":"钟摆跳 +24","imgString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1517473292890.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=VdysYHvmSiq9nXwqe1ld12P0kcQ%3D","id":"5a72cea9458c4a112032b2f5","type":"热身视频","create_date":"2018-02-01 16:24:09.0","update_date":"2018-02-01 16:24:09.0"}],"stretchMediaList":[{"img":"tpsp/1516795096616.jpeg","srcString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516795102688.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=VFcOraCuJ1%2BwVf8ApBIw68RlVhk%3D","src":"tpsp/1516795102688.mp4","name":"婴儿式190","imgString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516795096616.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=IrdBcaQXPOltV7I3Cy8vihF1x4I%3D","id":"5a6874ff3782b1234c9276f5","type":"拉伸视频","create_date":"2018-01-24 19:58:55.0","update_date":"2018-01-24 19:58:55.0"},{"img":"tpsp/1516795166911.jpeg","srcString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516795174790.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=eeYr8Bb5KE6quj7PFzLU8Zy2BOk%3D","src":"tpsp/1516795174790.mp4","name":"站姿股四头肌伸展196","imgString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516795166911.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=gibD%2B4et3YoLOV%2FG%2BqxaBQ9YN7M%3D","id":"5a6875953782b1234c927706","type":"拉伸视频","create_date":"2018-01-24 20:01:25.0","update_date":"2018-01-24 20:01:25.0"},{"img":"tpsp/1517472347945.jpeg","srcString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1517472356133.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=BR0sjUZp8MKlaMSRftQkOec3RW4%3D","src":"tpsp/1517472356133.mp4","name":"臀部伸展 +8","imgString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1517472347945.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=Sa1HHwuELRz1dMy2Edy0gZNhFtQ%3D","id":"5a72ca79458c4a11203ab91a","type":"拉伸视频","create_date":"2018-02-01 16:06:17.0","update_date":"2018-02-01 16:06:17.0"},{"img":"tpsp/1517472405334.jpeg","srcString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1517472413269.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=4B60O88o9s0rE9PB%2Ba1GaSClGS0%3D","src":"tpsp/1517472413269.mp4","name":"站立式小腿拉伸 +5","imgString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1517472405334.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=nSSfDBWB8yGZNBA4PHnYYp97PkM%3D","id":"5a72cabe458c4a11203ab923","type":"拉伸视频","create_date":"2018-02-01 16:07:26.0","update_date":"2018-02-01 16:07:26.0"}],"customizeLevel":"高级","customizeParts":"有氧,核心","officialMediaList":[{"img":"tpsp/1516779267284.jpeg","srcString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516779275680.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=xR7y5%2FT5yb1DyZmI41jOdxJVK1I%3D","src":"tpsp/1516779275680.mp4","level":"高级","name":"俯撑单腿抱膝高级58","imgString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516779267284.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=7bHSlkyVgZcwrJO2URn6oQ4%2FioM%3D","id":"5a6837603782b1234c926f6e","position":"核心","type":"正式视频","create_date":"2018-01-24 15:36:00.0","update_date":"2018-01-24 15:36:00.0"},{"img":"tpsp/1516793694355.jpeg","srcString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516793702382.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=imPnGjnKgvVn5jWTrwMgaOqhVz8%3D","src":"tpsp/1516793702382.mp4","level":"高级","name":"单腿俯卧撑波比跳高级84","imgString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516793694355.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=HvfNU%2BxjkylhLslFtukSCvpW7zw%3D","id":"5a686f923782b1234c9275ed","position":"有氧","type":"正式视频","create_date":"2018-01-24 19:35:45.0","update_date":"2018-01-24 19:35:45.0"},{"img":"tpsp/1517471912465.jpeg","srcString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1517471923688.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=Cu%2BgV%2BY8h8XwzadkUZeWKKrCMAw%3D","src":"tpsp/1517471923688.mp4","level":"高级","name":"顶峰式单手触脚 +47 高难度","imgString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1517471912465.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=CLO86NiUOO5uo6nNVAPJKxH0KxE%3D","id":"5a72c8d2458c4a11203ab8d8","position":"核心","type":"正式视频","create_date":"2018-02-01 15:59:14.0","update_date":"2018-02-01 15:59:14.0"},{"img":"tpsp/1517472686755.jpeg","srcString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1517472697727.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=lU9Kddn78%2FI1eIsb4CYbnXYO5dY%3D","src":"tpsp/1517472697727.mp4","level":"高级","name":"跪姿两点支撑 +49 高难度","imgString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1517472686755.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=ej90V9ajAPJ%2BYOV5hBGGcD0Azvg%3D","id":"5a72cbd3458c4a11209a239a","position":"有氧","type":"正式视频","create_date":"2018-02-01 16:12:03.0","update_date":"2018-02-01 16:12:03.0"}]}
     */

    private String rs;
    private ResultMapBean resultMap;

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public ResultMapBean getResultMap() {
        return resultMap;
    }

    public void setResultMap(ResultMapBean resultMap) {
        this.resultMap = resultMap;
    }

    public static class ResultMapBean implements Serializable{
        /**
         * errCode : 0
         * errMsg : ok
         * warmUpMediaList : [{"img":"tpsp/1516794542540.jpeg","srcString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516794551804.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=Wyih5qd299DwC%2BoG0QHzzOYbXY8%3D","src":"tpsp/1516794551804.mp4","name":"开合跳 117","imgString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516794542540.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=P%2FaC%2Blpo%2BahwPZq0i4n%2FKJhENfg%3D","id":"5a6872c53782b1234c927681","type":"热身视频","create_date":"2018-01-24 19:49:25.0","update_date":"2018-01-24 19:49:25.0"},{"img":"tpsp/1517473292890.jpeg","srcString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1517473350092.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=2mjwgTYNk%2F2KaSbSrqI9xObPPVM%3D","src":"tpsp/1517473350092.mp4","name":"钟摆跳 +24","imgString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1517473292890.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=VdysYHvmSiq9nXwqe1ld12P0kcQ%3D","id":"5a72cea9458c4a112032b2f5","type":"热身视频","create_date":"2018-02-01 16:24:09.0","update_date":"2018-02-01 16:24:09.0"}]
         * stretchMediaList : [{"img":"tpsp/1516795096616.jpeg","srcString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516795102688.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=VFcOraCuJ1%2BwVf8ApBIw68RlVhk%3D","src":"tpsp/1516795102688.mp4","name":"婴儿式190","imgString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516795096616.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=IrdBcaQXPOltV7I3Cy8vihF1x4I%3D","id":"5a6874ff3782b1234c9276f5","type":"拉伸视频","create_date":"2018-01-24 19:58:55.0","update_date":"2018-01-24 19:58:55.0"},{"img":"tpsp/1516795166911.jpeg","srcString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516795174790.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=eeYr8Bb5KE6quj7PFzLU8Zy2BOk%3D","src":"tpsp/1516795174790.mp4","name":"站姿股四头肌伸展196","imgString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516795166911.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=gibD%2B4et3YoLOV%2FG%2BqxaBQ9YN7M%3D","id":"5a6875953782b1234c927706","type":"拉伸视频","create_date":"2018-01-24 20:01:25.0","update_date":"2018-01-24 20:01:25.0"},{"img":"tpsp/1517472347945.jpeg","srcString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1517472356133.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=BR0sjUZp8MKlaMSRftQkOec3RW4%3D","src":"tpsp/1517472356133.mp4","name":"臀部伸展 +8","imgString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1517472347945.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=Sa1HHwuELRz1dMy2Edy0gZNhFtQ%3D","id":"5a72ca79458c4a11203ab91a","type":"拉伸视频","create_date":"2018-02-01 16:06:17.0","update_date":"2018-02-01 16:06:17.0"},{"img":"tpsp/1517472405334.jpeg","srcString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1517472413269.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=4B60O88o9s0rE9PB%2Ba1GaSClGS0%3D","src":"tpsp/1517472413269.mp4","name":"站立式小腿拉伸 +5","imgString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1517472405334.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=nSSfDBWB8yGZNBA4PHnYYp97PkM%3D","id":"5a72cabe458c4a11203ab923","type":"拉伸视频","create_date":"2018-02-01 16:07:26.0","update_date":"2018-02-01 16:07:26.0"}]
         * customizeLevel : 高级
         * customizeParts : 有氧,核心
         * officialMediaList : [{"img":"tpsp/1516779267284.jpeg","srcString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516779275680.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=xR7y5%2FT5yb1DyZmI41jOdxJVK1I%3D","src":"tpsp/1516779275680.mp4","level":"高级","name":"俯撑单腿抱膝高级58","imgString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516779267284.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=7bHSlkyVgZcwrJO2URn6oQ4%2FioM%3D","id":"5a6837603782b1234c926f6e","position":"核心","type":"正式视频","create_date":"2018-01-24 15:36:00.0","update_date":"2018-01-24 15:36:00.0"},{"img":"tpsp/1516793694355.jpeg","srcString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516793702382.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=imPnGjnKgvVn5jWTrwMgaOqhVz8%3D","src":"tpsp/1516793702382.mp4","level":"高级","name":"单腿俯卧撑波比跳高级84","imgString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516793694355.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=HvfNU%2BxjkylhLslFtukSCvpW7zw%3D","id":"5a686f923782b1234c9275ed","position":"有氧","type":"正式视频","create_date":"2018-01-24 19:35:45.0","update_date":"2018-01-24 19:35:45.0"},{"img":"tpsp/1517471912465.jpeg","srcString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1517471923688.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=Cu%2BgV%2BY8h8XwzadkUZeWKKrCMAw%3D","src":"tpsp/1517471923688.mp4","level":"高级","name":"顶峰式单手触脚 +47 高难度","imgString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1517471912465.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=CLO86NiUOO5uo6nNVAPJKxH0KxE%3D","id":"5a72c8d2458c4a11203ab8d8","position":"核心","type":"正式视频","create_date":"2018-02-01 15:59:14.0","update_date":"2018-02-01 15:59:14.0"},{"img":"tpsp/1517472686755.jpeg","srcString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1517472697727.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=lU9Kddn78%2FI1eIsb4CYbnXYO5dY%3D","src":"tpsp/1517472697727.mp4","level":"高级","name":"跪姿两点支撑 +49 高难度","imgString":"http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1517472686755.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=ej90V9ajAPJ%2BYOV5hBGGcD0Azvg%3D","id":"5a72cbd3458c4a11209a239a","position":"有氧","type":"正式视频","create_date":"2018-02-01 16:12:03.0","update_date":"2018-02-01 16:12:03.0"}]
         */

        private int errCode;
        private String errMsg;
        private String customizeLevel;
        private String customizeParts;
        private List<WarmUpMediaListBean> warmUpMediaList;
        private List<WarmUpMediaListBean> stretchMediaList;
        private List<WarmUpMediaListBean> officialMediaList;

        public int getErrCode() {
            return errCode;
        }

        public void setErrCode(int errCode) {
            this.errCode = errCode;
        }

        public String getErrMsg() {
            return errMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }

        public String getCustomizeLevel() {
            return customizeLevel;
        }

        public void setCustomizeLevel(String customizeLevel) {
            this.customizeLevel = customizeLevel;
        }

        public String getCustomizeParts() {
            return customizeParts;
        }

        public void setCustomizeParts(String customizeParts) {
            this.customizeParts = customizeParts;
        }

        public List<WarmUpMediaListBean> getWarmUpMediaList() {
            return warmUpMediaList;
        }

        public void setWarmUpMediaList(List<WarmUpMediaListBean> warmUpMediaList) {
            this.warmUpMediaList = warmUpMediaList;
        }

        public List<WarmUpMediaListBean> getStretchMediaList() {
            return stretchMediaList;
        }

        public void setStretchMediaList(List<WarmUpMediaListBean> stretchMediaList) {
            this.stretchMediaList = stretchMediaList;
        }

        public List<WarmUpMediaListBean> getOfficialMediaList() {
            return officialMediaList;
        }

        public void setOfficialMediaList(List<WarmUpMediaListBean> officialMediaList) {
            this.officialMediaList = officialMediaList;
        }

        public static class WarmUpMediaListBean implements Serializable {
            /**
             * img : tpsp/1516794542540.jpeg
             * srcString : http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516794551804.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=Wyih5qd299DwC%2BoG0QHzzOYbXY8%3D
             * src : tpsp/1516794551804.mp4
             * name : 开合跳 117
             * imgString : http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516794542540.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=P%2FaC%2Blpo%2BahwPZq0i4n%2FKJhENfg%3D
             * id : 5a6872c53782b1234c927681
             * type : 热身视频
             * create_date : 2018-01-24 19:49:25.0
             * update_date : 2018-01-24 19:49:25.0
             */

            private String img;
            private String srcString;
            private String src;
            private String name;
            private String imgString;
            private String id;
            private String type;
            private String create_date;
            private String update_date;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getSrcString() {
                return srcString;
            }

            public void setSrcString(String srcString) {
                this.srcString = srcString;
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImgString() {
                return imgString;
            }

            public void setImgString(String imgString) {
                this.imgString = imgString;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCreate_date() {
                return create_date;
            }

            public void setCreate_date(String create_date) {
                this.create_date = create_date;
            }

            public String getUpdate_date() {
                return update_date;
            }

            public void setUpdate_date(String update_date) {
                this.update_date = update_date;
            }
        }

        public static class StretchMediaListBean {
            /**
             * img : tpsp/1516795096616.jpeg
             * srcString : http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516795102688.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=VFcOraCuJ1%2BwVf8ApBIw68RlVhk%3D
             * src : tpsp/1516795102688.mp4
             * name : 婴儿式190
             * imgString : http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516795096616.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=IrdBcaQXPOltV7I3Cy8vihF1x4I%3D
             * id : 5a6874ff3782b1234c9276f5
             * type : 拉伸视频
             * create_date : 2018-01-24 19:58:55.0
             * update_date : 2018-01-24 19:58:55.0
             */

            private String img;
            private String srcString;
            private String src;
            private String name;
            private String imgString;
            private String id;
            private String type;
            private String create_date;
            private String update_date;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getSrcString() {
                return srcString;
            }

            public void setSrcString(String srcString) {
                this.srcString = srcString;
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImgString() {
                return imgString;
            }

            public void setImgString(String imgString) {
                this.imgString = imgString;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCreate_date() {
                return create_date;
            }

            public void setCreate_date(String create_date) {
                this.create_date = create_date;
            }

            public String getUpdate_date() {
                return update_date;
            }

            public void setUpdate_date(String update_date) {
                this.update_date = update_date;
            }
        }

        public static class OfficialMediaListBean {
            /**
             * img : tpsp/1516779267284.jpeg
             * srcString : http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516779275680.mp4?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=xR7y5%2FT5yb1DyZmI41jOdxJVK1I%3D
             * src : tpsp/1516779275680.mp4
             * level : 高级
             * name : 俯撑单腿抱膝高级58
             * imgString : http://new-saas.oss-cn-hangzhou.aliyuncs.com/tpsp/1516779267284.jpeg?Expires=1518061341&OSSAccessKeyId=LTAI8CrYTB24FRQU&Signature=7bHSlkyVgZcwrJO2URn6oQ4%2FioM%3D
             * id : 5a6837603782b1234c926f6e
             * position : 核心
             * type : 正式视频
             * create_date : 2018-01-24 15:36:00.0
             * update_date : 2018-01-24 15:36:00.0
             */

            private String img;
            private String srcString;
            private String src;
            private String level;
            private String name;
            private String imgString;
            private String id;
            private String position;
            private String type;
            private String create_date;
            private String update_date;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getSrcString() {
                return srcString;
            }

            public void setSrcString(String srcString) {
                this.srcString = srcString;
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImgString() {
                return imgString;
            }

            public void setImgString(String imgString) {
                this.imgString = imgString;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCreate_date() {
                return create_date;
            }

            public void setCreate_date(String create_date) {
                this.create_date = create_date;
            }

            public String getUpdate_date() {
                return update_date;
            }

            public void setUpdate_date(String update_date) {
                this.update_date = update_date;
            }
        }
    }
}
