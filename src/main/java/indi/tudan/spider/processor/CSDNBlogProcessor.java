package indi.tudan.spider.processor;

import indi.tudan.spider.model.CSDNBlog;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * CSDNBlog 处理器
 *
 * @author wangtan
 * @date 2019-11-23 20:57:40
 * @since 1.0
 */
public class CSDNBlogProcessor implements PageProcessor {

    /**
     * CSDN 用户名
     */
    private static String username = "WTUDAN";

    /**
     * 共抓取到的文章数量
     */
    private static int size = 0;

    /**
     * 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
     */
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        if (!page.getUrl().regex("http://blog.csdn.net/" + username + "/article/details/\\d+").match()) {

            System.out.println(page.getHtml());

            // 获取当前页码
            String number = page.getHtml().xpath("//li[@class='page-item active']//a[@class='page-link']/text()").toString();

            // 匹配当前页码+1的页码也就是下一页，加入爬取列表中
            String targetUrls = page.getHtml().links()
                    .regex("http://blog.csdn.net/" + username + "/article/list/" + (Integer.parseInt(number) + 1)).get();
            page.addTargetRequest(targetUrls);

            List<String> detailUrls = page.getHtml().xpath("//li[@class='blog-unit']//a/@href").all();
            for (String list : detailUrls) {
                System.out.println(list);
            }
            page.addTargetRequests(detailUrls);
        } else {

            // 文章数量加 1
            size++;
            CSDNBlog csdnBlog = new CSDNBlog();
            String path = page.getUrl().get();
            int id = Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));
            String title = page.getHtml().xpath("//h1[@class='csdn_top']/text()").get();
            String date = page.getHtml().xpath("//div[@class='artical_tag']//span[@class='time']/text()").get();
            String copyright = page.getHtml().xpath("//div[@class='artical_tag']//span[@class='original']/text()").get();
            int view = Integer.parseInt(page.getHtml().xpath("//button[@class='btn-noborder']//span[@class='txt']/text()").get());
            csdnBlog.id(id).title(title).date(date).copyright(copyright).view(view);
            System.out.println(csdnBlog);
        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        // 从用户博客首页开始抓，开启5个线程，启动爬虫
        Spider.create(new CSDNBlogProcessor())
                .addUrl("http://blog.csdn.net/" + username)
                .thread(5).run();
        System.out.println("文章总数为" + size);

    }

}
