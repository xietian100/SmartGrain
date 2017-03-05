package smartgrain.com.news.bean;

import java.util.List;

/**
 * Created by pc on 2017/3/2.
 */

public class BannersBean {
    private String code;
    private String state;
    private List<ListsBean.Content> content;
    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getState() {
        return state;
    }

    public void setContent(List<ListsBean.Content> content) {
        this.content = content;
    }
    public List<ListsBean.Content> getContent() {
        return content;
    }

    public class Content {

        private int n0;
        private String n1;
        private String n2;

        public void setN0(int n0) {
            this.n0 = n0;
        }

        public int getN0() {
            return n0;
        }

        public void setN1(String n1) {
            this.n1 = n1;
        }

        public String getN1() {
            return n1;
        }

        public void setN2(String n2) {
            this.n2 = n2;
        }

        public String getN2() {
            return n2;
        }
    }
}
