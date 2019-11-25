package indi.tudan.spider.processor;

import cn.hutool.core.lang.Console;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * @author 亦枫
 * @created_time 2016年1月12日
 * @file_user_todo Java测试类
 * @blog http://www.jianshu.com/users/1c40186e3248/latest_articles
 */
public class JavaTest {

    /**
     *
     */
    public static void main(String[] args) throws ParseException {

        try {
            WebClient webClient = new WebClient(BrowserVersion.CHROME);
            HtmlPage htmlPage = webClient.getPage("https://blog.csdn.net/WTUDAN");
            DomNodeList domNodeList = htmlPage.getElementsByTagName("a");

            // 发现文章链接
            HashSet<String> articleUrlSet = (HashSet<String>) domNodeList.stream()
                    .filter(p -> {
                        String href = ((DomElement) p).getAttribute("href");
                        return /*href.contains("WTUDAN") &&*/ href.contains("article/details");
                    })
                    .map(q -> ((DomElement) q).getAttribute("href"))
                    .collect(Collectors.toSet());

            Console.log(articleUrlSet);

            webClient.close();
        } catch (FailingHttpStatusCodeException | IOException e) {
            e.printStackTrace();
        }

    }

}
