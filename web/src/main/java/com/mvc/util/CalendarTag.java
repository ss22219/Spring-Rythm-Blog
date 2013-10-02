package com.mvc.util;

import com.mvc.model.Article;
import com.mvc.service.ArticleService;
import com.mvc.service.SettingService;
import org.rythmengine.template.JavaTagBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class CalendarTag extends JavaTagBase {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private SettingService settingService;

    @Override
    protected void call(__ParameterList parameters, __Body body) {

        int weekStart = Integer.parseInt(settingService.getSetting("weekStart"));

        StringBuffer sb = new StringBuffer();

        ///中国一周中的天
        String[] cnDay = new String[]{
                "日",
                "一",
                "二",
                "三",
                "四",
                "五",
                "六"
        };
        Date dateNow = new Date();
        ///构造表头
        sb.append("<table id=\"wp-calendar\"><caption>" + (dateNow.getYear() + 1900) + " 年" + dateNow.getMonth() + "月" + "</caption><thead><tr>");

        ///一个月中第几天
        int monthDay = 0;
        ///一周中的第几天
        int weekDay = 0;

        ///构造星期表头
        for (monthDay = 0; monthDay < 7; monthDay++) {
            weekDay = monthDay + weekStart > 6 ? monthDay + weekStart - 7 : monthDay + weekStart;
            sb.append("<th scope=\"col\" title=\"星期" + cnDay[weekDay] + "\">" + cnDay[weekDay] + "</th>");
        }
        sb.append("</tr></thead>");
        sb.append("<tbody><tr>");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);

        ///一个月的开始日期
        Date startDay = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 1);
        calendar.add(Calendar.DATE, -1);

        ///一个月的结束日期
        Date endDay = calendar.getTime();

        ///本月文章列表
        Page<Article> articles = articleService.getArticleByMonth(startDay, 1);
        monthDay = 0;
        weekDay = 0;

        ///构造空表格
        while (true) {
            if ((int) startDay.getDay() == (weekStart + monthDay > 6 ? monthDay + weekStart - 7 : monthDay + weekStart)) {
                break;
            }
            sb.append("<td></td>");
            monthDay++;
            weekDay++;
        }
        ///构造日期表格
        for (monthDay = 1; monthDay <= endDay.getDate(); monthDay++) {
            ///当天文章
            String data = "";
            List<Article> list = new ArrayList<Article>();
            for (Article article : articles) {
                if (article.getCreateDate().getDate() == monthDay) {
                    list.add(article);
                }
            }
            if (list.size() > 0) {
                String title = "";
                for (Article item : list) {
                    title += item.getTitle() + " ";
                }

                data = "<a href=\"/category/day/" + (startDay.getYear() + 1900) + "-" + (startDay.getMonth() + 1) + "-" + monthDay + "\" title=\"" + title + "\" >" + monthDay + "</a>";

            } else {
                data = monthDay + "";
            }


            ///今天高亮
            if (monthDay == dateNow.getDate())
                sb.append("<td id=\"today\">" + data + "</td>");
            else
                sb.append("<td>" + data + "</td>");
            weekDay++;


            ///一周换横
            if (weekDay > 6) {
                sb.append("</tr><tr>");
                weekDay = 0;
            }
        }
        sb.append("</tr></tbody></table>");
        this.p(sb.toString());
    }

    private void buildDayArticle(int day, List<Article> list) {
    }


    @Override
    public String __getName() {
        return "calendar";
    }
}
