package com.oracle.oaec.androidproject.po;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */

public class Music {


    /**
     * code : 0
     * status : success
     * msg : 数据请求成功
     * data : {"current_page":"1","keyword":"十年","total_rows":480,"total_page":48,"page_size":"10","data":[{"filename":"赵丽颖 - 十年【《我们的十年》电影主题曲】","songname":"十年","m4afilesize":832156,"filesize":3328348,"bitrate":132,"album_name":"我们的十年 电影原声带","isnew":0,"duration":201,"singername":"赵丽颖","extname":"mp3","hash":"bc1bf186645db2dc11cb77b53e3d50fa","othername":""},{"filename":"韩红、陈奕迅 - 十年","songname":"十年","m4afilesize":1259429,"filesize":4911226,"bitrate":128,"album_name":"我是歌手第三季合辑","isnew":0,"duration":307,"singername":"韩红、陈奕迅","extname":"mp3","hash":"7c00d6e8a457ce6ee8a558d37fe37aa3","othername":""},{"filename":"陈翔 - 十年","songname":"十年","m4afilesize":835583,"filesize":3251866,"bitrate":128,"album_name":"","isnew":0,"duration":203,"singername":"陈翔","extname":"mp3","hash":"2397a8932678d932242de9e8853c2d20","othername":""},{"filename":"网络歌手 - 十年 - 英雄联盟版","songname":"十年","m4afilesize":833981,"filesize":3246707,"bitrate":127,"album_name":"","isnew":0,"duration":202,"singername":"网络歌手","extname":"mp3","hash":"352389726a226ba0d43930fb6f645590","othername":"英雄联盟版"},{"filename":"TFBOYS - 十年","songname":"十年","m4afilesize":938719,"filesize":3670644,"bitrate":128,"album_name":"","isnew":0,"duration":229,"singername":"TFBOYS","extname":"mp3","hash":"b30f03df9b3247118eeef5796e25551a","othername":""},{"filename":"刘德华 - 十年 - 香港Unforgettable Concert","songname":"十年","m4afilesize":1175158,"filesize":4511694,"bitrate":128,"album_name":"Unforgettable Concert 2010","isnew":0,"duration":279,"singername":"刘德华","extname":"mp3","hash":"2e0714a12dc974035c9fca28d57352b8","othername":"香港Unforgettable Concert"},{"filename":"刘若英 - 十年 - CCTV精彩音乐汇现场","songname":"十年","m4afilesize":819021,"filesize":3183304,"bitrate":128,"album_name":"cctv音乐频道精彩音乐汇合辑","isnew":0,"duration":199,"singername":"刘若英","extname":"mp3","hash":"946cc85a57d0874be593d8898517b41b","othername":"CCTV精彩音乐汇现场"},{"filename":"搞笑歌曲 - 十年 - 单曲版","songname":"十年","m4afilesize":862842,"filesize":3281780,"bitrate":128,"album_name":"网络愚乐排行榜-我只是民工","isnew":0,"duration":205,"singername":"搞笑歌曲","extname":"mp3","hash":"f810a932552d32e4c5d776b5b4d6f625","othername":"单曲版"},{"filename":"陈奕迅 - 十年 - 原版伴奏","songname":"十年","m4afilesize":852411,"filesize":3243733,"bitrate":128,"album_name":"","isnew":0,"duration":202,"singername":"陈奕迅","extname":"mp3","hash":"180f842eeada43cad376a20db1cb5ec6","othername":"原版伴奏"},{"filename":"陈奕迅 - 十年 - CCTV精彩音乐汇现场","songname":"十年","m4afilesize":811014,"filesize":3156889,"bitrate":128,"album_name":"cctv音乐频道精彩音乐汇合辑","isnew":0,"duration":197,"singername":"陈奕迅","extname":"mp3","hash":"53df727308694879cfb2bf2c65fdb578","othername":"CCTV精彩音乐汇现场"},{"filename":"陈奕迅 - 十年 - CCTV精彩音乐汇现场1","songname":"十年","m4afilesize":828235,"filesize":3244744,"bitrate":128,"album_name":"cctv音乐频道精彩音乐汇合辑","isnew":0,"duration":203,"singername":"陈奕迅","extname":"mp3","hash":"548f977f0afe54d043c828f4de70da1a","othername":"CCTV精彩音乐汇现场1"}]}
     */

    private int code;
    private String status;
    private String msg;
    /**
     * current_page : 1
     * keyword : 十年
     * total_rows : 480
     * total_page : 48
     * page_size : 10
     * data : [{"filename":"赵丽颖 - 十年【《我们的十年》电影主题曲】","songname":"十年","m4afilesize":832156,"filesize":3328348,"bitrate":132,"album_name":"我们的十年 电影原声带","isnew":0,"duration":201,"singername":"赵丽颖","extname":"mp3","hash":"bc1bf186645db2dc11cb77b53e3d50fa","othername":""},{"filename":"韩红、陈奕迅 - 十年","songname":"十年","m4afilesize":1259429,"filesize":4911226,"bitrate":128,"album_name":"我是歌手第三季合辑","isnew":0,"duration":307,"singername":"韩红、陈奕迅","extname":"mp3","hash":"7c00d6e8a457ce6ee8a558d37fe37aa3","othername":""},{"filename":"陈翔 - 十年","songname":"十年","m4afilesize":835583,"filesize":3251866,"bitrate":128,"album_name":"","isnew":0,"duration":203,"singername":"陈翔","extname":"mp3","hash":"2397a8932678d932242de9e8853c2d20","othername":""},{"filename":"网络歌手 - 十年 - 英雄联盟版","songname":"十年","m4afilesize":833981,"filesize":3246707,"bitrate":127,"album_name":"","isnew":0,"duration":202,"singername":"网络歌手","extname":"mp3","hash":"352389726a226ba0d43930fb6f645590","othername":"英雄联盟版"},{"filename":"TFBOYS - 十年","songname":"十年","m4afilesize":938719,"filesize":3670644,"bitrate":128,"album_name":"","isnew":0,"duration":229,"singername":"TFBOYS","extname":"mp3","hash":"b30f03df9b3247118eeef5796e25551a","othername":""},{"filename":"刘德华 - 十年 - 香港Unforgettable Concert","songname":"十年","m4afilesize":1175158,"filesize":4511694,"bitrate":128,"album_name":"Unforgettable Concert 2010","isnew":0,"duration":279,"singername":"刘德华","extname":"mp3","hash":"2e0714a12dc974035c9fca28d57352b8","othername":"香港Unforgettable Concert"},{"filename":"刘若英 - 十年 - CCTV精彩音乐汇现场","songname":"十年","m4afilesize":819021,"filesize":3183304,"bitrate":128,"album_name":"cctv音乐频道精彩音乐汇合辑","isnew":0,"duration":199,"singername":"刘若英","extname":"mp3","hash":"946cc85a57d0874be593d8898517b41b","othername":"CCTV精彩音乐汇现场"},{"filename":"搞笑歌曲 - 十年 - 单曲版","songname":"十年","m4afilesize":862842,"filesize":3281780,"bitrate":128,"album_name":"网络愚乐排行榜-我只是民工","isnew":0,"duration":205,"singername":"搞笑歌曲","extname":"mp3","hash":"f810a932552d32e4c5d776b5b4d6f625","othername":"单曲版"},{"filename":"陈奕迅 - 十年 - 原版伴奏","songname":"十年","m4afilesize":852411,"filesize":3243733,"bitrate":128,"album_name":"","isnew":0,"duration":202,"singername":"陈奕迅","extname":"mp3","hash":"180f842eeada43cad376a20db1cb5ec6","othername":"原版伴奏"},{"filename":"陈奕迅 - 十年 - CCTV精彩音乐汇现场","songname":"十年","m4afilesize":811014,"filesize":3156889,"bitrate":128,"album_name":"cctv音乐频道精彩音乐汇合辑","isnew":0,"duration":197,"singername":"陈奕迅","extname":"mp3","hash":"53df727308694879cfb2bf2c65fdb578","othername":"CCTV精彩音乐汇现场"},{"filename":"陈奕迅 - 十年 - CCTV精彩音乐汇现场1","songname":"十年","m4afilesize":828235,"filesize":3244744,"bitrate":128,"album_name":"cctv音乐频道精彩音乐汇合辑","isnew":0,"duration":203,"singername":"陈奕迅","extname":"mp3","hash":"548f977f0afe54d043c828f4de70da1a","othername":"CCTV精彩音乐汇现场1"}]
     */

    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String current_page;
        private String keyword;
        private int total_rows;
        private int total_page;
        private String page_size;
        /**
         * filename : 赵丽颖 - 十年【《我们的十年》电影主题曲】
         * songname : 十年
         * m4afilesize : 832156
         * filesize : 3328348
         * bitrate : 132
         * album_name : 我们的十年 电影原声带
         * isnew : 0
         * duration : 201
         * singername : 赵丽颖
         * extname : mp3
         * hash : bc1bf186645db2dc11cb77b53e3d50fa
         * othername :
         */

        private List<DataBean2> data;

        public String getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(String current_page) {
            this.current_page = current_page;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public int getTotal_rows() {
            return total_rows;
        }

        public void setTotal_rows(int total_rows) {
            this.total_rows = total_rows;
        }

        public int getTotal_page() {
            return total_page;
        }

        public void setTotal_page(int total_page) {
            this.total_page = total_page;
        }

        public String getPage_size() {
            return page_size;
        }

        public void setPage_size(String page_size) {
            this.page_size = page_size;
        }

        public List<DataBean2> getData2() {
            return data;
        }

        public void setData2(List<DataBean2> data) {
            this.data = data;
        }

        public static class DataBean2 implements Serializable {
            private String filename;
            private String songname;
            private String time;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            private int m4afilesize;
            private int filesize;
            private int bitrate;
            private String album_name;
            private int isnew;
            private int duration;
            private String singername;
            private String extname;
            private String hash;
            private String othername;

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public String getSongname() {
                return songname;
            }

            public void setSongname(String songname) {
                this.songname = songname;
            }

            public int getM4afilesize() {
                return m4afilesize;
            }

            public void setM4afilesize(int m4afilesize) {
                this.m4afilesize = m4afilesize;
            }

            public int getFilesize() {
                return filesize;
            }

            public void setFilesize(int filesize) {
                this.filesize = filesize;
            }

            public int getBitrate() {
                return bitrate;
            }

            public void setBitrate(int bitrate) {
                this.bitrate = bitrate;
            }

            public String getAlbum_name() {
                return album_name;
            }

            public void setAlbum_name(String album_name) {
                this.album_name = album_name;
            }

            public int getIsnew() {
                return isnew;
            }

            public void setIsnew(int isnew) {
                this.isnew = isnew;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public String getSingername() {
                return singername;
            }

            public void setSingername(String singername) {
                this.singername = singername;
            }

            public String getExtname() {
                return extname;
            }

            public void setExtname(String extname) {
                this.extname = extname;
            }

            public String getHash() {
                return hash;
            }

            public void setHash(String hash) {
                this.hash = hash;
            }

            public String getOthername() {
                return othername;
            }

            public void setOthername(String othername) {
                this.othername = othername;
            }
        }
    }
}
