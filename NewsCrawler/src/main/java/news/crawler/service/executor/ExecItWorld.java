package news.crawler.service.executor;

import lombok.extern.slf4j.Slf4j;
import news.crawler.common.DateTimeUtils;
import news.crawler.controller.dto.EventDTO;
import news.crawler.domain.SourceConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExecItWorld implements Execute {

    @Override
    public List<EventDTO> execute(SourceConfig config) {
        List<EventDTO> events = new ArrayList<>();

        try {
            Document root = Jsoup.connect(config.getRootUrl() + config.getNewsSuffix()).get();
            Elements links = root.getElementsByClass("news-time");
            for (Element element : links) {
                Element href = element.select("a").first();
                String title = href.text();
                String newsUrl = config.getRootUrl() + href.attr("href");
                String time = element.getElementsByClass("news__time").first().text();

                log.info("Reading news from {}...", newsUrl);

                // read news page and extract date-time and text
                Document news = Jsoup.connect(newsUrl).get();
                String imgUrl = news.getElementsByClass("news-detail__picture").select("a").attr("href");
                Element spanDate = news.select(".separator-line span").first();
                if (spanDate == null) {
                    spanDate = news.select(".color-silver span").first();
                }
                String newsDate = spanDate != null? spanDate.text() : "undefined";
                String description = news.select(".article__lid").text();
                String text = description + " " + news.select(".news-detail__content").text();

                LocalDateTime dateTime = DateTimeUtils.convertDateTime(newsDate, time);
                imgUrl =  config.getRootUrl() + imgUrl;

                events.add(new EventDTO(title, newsUrl, dateTime, text, imgUrl));
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return events;
    }
}
