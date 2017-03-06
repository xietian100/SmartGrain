package smartgrain.com.date.bean;

/**
 * Created by pc on 2017/3/6.
 */

public class Transaction {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getQingdan_url() {
        return qingdan_url;
    }

    public void setQingdan_url(String qingdan_url) {
        this.qingdan_url = qingdan_url;
    }

    public String title;
    public String data;
    public String url;
    public String content;
    public String qingdan_url;

}
