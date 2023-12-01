package news.crawler.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import news.crawler.domain.Event;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class EventDTO {
    private String title;
    private String newsUrl;
    private LocalDateTime dateTime;
    private String text;
    private String imageUrl;

    public static EventDTO getInstance(Event event) {
        return new EventDTO(event.getTitle(), event.getNewsUrl(), event.getDateTime(),
                event.getText(), event.getImageUrl());
    }
}

