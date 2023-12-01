package news.crawler.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import news.crawler.domain.Event;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class EventController {
    private String title;
    private String newsUrl;
    private LocalDateTime dateTime;
    private String text;
    private String imageUrl;

    public static EventController getInstance(Event event) {
        return new EventController(event.getTitle(), event.getNewsUrl(), event.getDateTime(),
                event.getText(), event.getImageUrl());
    }
}
