package ru.allbets.smart_bet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import ru.allbets.smart_bet.model.MergedEvent;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventsController {
    private final String URL = "http://localhost:8080/api/events";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{count}")
    public String getEvents(@PathVariable int count, Model model) {
        ResponseEntity<List<MergedEvent>> responseEntity = restTemplate.exchange(URL + "/" + count, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<MergedEvent>>() {});
        List<MergedEvent> mergedEvents = responseEntity.getBody();
        model.addAttribute("events", mergedEvents);
        return "index";
    }
}
