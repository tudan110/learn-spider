package indi.tudan.spider.model;

/**
 * Csdn 博客类
 *
 * @author wangtan
 * @date 2019-11-23 20:56:55
 * @since 1.0
 */
public class CSDNBlog {

    private int id;// 编号
    private String title;// 标题
    private String date;// 日期
    private String category;// 分类
    private int view;// 阅读人数
    private int comments;// 评论人数
    private String copyright;// 是否原创

    public CSDNBlog id(int id) {
        this.id = id;
        return this;
    }

    public CSDNBlog date(String date) {
        this.date = date;
        return this;
    }

    public CSDNBlog title(String title) {
        this.title = title;
        return this;
    }

    public CSDNBlog category(String category) {
        this.category = category;
        return this;
    }

    public CSDNBlog view(int view) {
        this.view = view;
        return this;
    }

    public CSDNBlog comments(int comments) {
        this.comments = comments;
        return this;
    }

    public CSDNBlog copyright(String copyright) {
        this.copyright = copyright;
        return this;
    }

    @Override
    public String toString() {
        return "CSDNBlog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                ", view=" + view +
                ", comments=" + comments +
                ", copyright='" + copyright + '\'' +
                '}';
    }
}
