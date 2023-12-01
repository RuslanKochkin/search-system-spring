package news.crawler.controller;

import news.crawler.controller.dto.EventDTO;
import news.crawler.service.EventService;
import news.crawler.service.SourceConfigService;
import news.crawler.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.events.Event;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ParseTestController {

    @Autowired
    private EventService eventService;

    @GetMapping("/parse/{rootUrl}/{newSuffix}/{className}")
    public List<EventDTO> parseTest(@PathVariable String rootUrl,
                                    @PathVariable String newSuffix,
                                    @PathVariable String className) throws Exception {
        return eventService.parseTest(rootUrl, newSuffix, className);
    }



}
