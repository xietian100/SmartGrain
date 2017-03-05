package smartgrain.com.news.bean;

import java.util.List;

/**
 * Created by pc on 2017/3/2.
 */

public class SNBEAN {
    private List<Focusimg> focusimg;
    private String recnums;
    private List<News> news;
    public void setFocusimg(List<Focusimg> focusimg) {
        this.focusimg = focusimg;
    }
    public List<Focusimg> getFocusimg() {
        return focusimg;
    }

    public void setRecnums(String recnums) {
        this.recnums = recnums;
    }
    public String getRecnums() {
        return recnums;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
    public List<News> getNews() {
        return news;
    }
    public class Focusimg {

        private String title;
        private String imgsrc;
        private String srcurl;
        private String vid;

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setSrcurl(String srcurl) {
            this.srcurl = srcurl;
        }

        public String getSrcurl() {
            return srcurl;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }

        public String getVid() {
            return vid;
        }


    }
    public class News {

        private String digest;
        private String title;
        private String imgsrc;
        private String vid;
        private String srcurl;
        private String video;
        private String type;

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getDigest() {
            return digest;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }

        public String getVid() {
            return vid;
        }

        public void setSrcurl(String srcurl) {
            this.srcurl = srcurl;
        }

        public String getSrcurl() {
            return srcurl;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getVideo() {
            return video;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

    }
    }
