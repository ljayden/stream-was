package com.uneedcomms.streamwas.stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class StreamController {

    private StreamService streamService;
    private ObjectMapper objectMapper;
    private final static String FLAG = "flag";

    public StreamController(StreamService streamService, ObjectMapper objectMapper) {
        this.streamService = streamService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity createEvent(@RequestBody @NotNull Map<String, Object> contents, Errors errors) throws JsonProcessingException {
        if(errors.hasErrors()){ return ResponseEntity.badRequest().body(objectMapper.writeValueAsString(errors)); }

        Map<String, Object> eventsMap = (Map<String, Object>) contents.get("events");
//        System.out.println(eventsMap.size());

        // business logic
        Map<String, Object> map = streamService.validation(contents);
        map.get("contents");

        // return
        if((boolean) map.get(FLAG)) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(map));
        } else {
            return ResponseEntity.badRequest().body(objectMapper.writeValueAsString(map));
        }
    }

}
