package com.uneedcomms.streamwas.stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uneedcomms.streamwas.redis.Client;
import com.uneedcomms.streamwas.redis.ClientRepository;
import com.uneedcomms.streamwas.redis.MetaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StreamService {

    @Autowired
    RedisTemplate redisTemplate;

    private ObjectMapper objectMapper;
    private ClientRepository clientRepository;
//    private KafkaTemplate kafkaTemplate;


    public StreamService(ObjectMapper objectMapper, ClientRepository clientRepository) {
        this.objectMapper = objectMapper;
        this.clientRepository = clientRepository;
    }

//    public StreamService(ObjectMapper objectMapper, ClientRepository clientRepository, KafkaTemplate kafkaTemplate) {
//        this.objectMapper = objectMapper;
//        this.clientRepository = clientRepository;
//        this.kafkaTemplate = kafkaTemplate;
//    }

    public Map<String, Object> validation(Map<String, Object> contents) {

        Map<String, Object> returnMap = new HashMap<>();
        boolean flag = false;
        String message = "";


        Map<String, Object> events = (HashMap<String, Object>)contents.get("events");

        if(contents.containsKey("key")) {
            String key = (String)contents.get("key");


            try {



                // Connect to Redis
                //Client client = clientRepository.findById(key).orElseThrow(Exception::new);
                //MetaInfo metaInfo = client.getMetainfo();

//                metaInfo.getEvent().forEach(e->{
//                    System.out.println("client:metainfo:event::"+e);
//                    // TODO
//                    e.equals("");
//                });


                // TODO kafka producer send
                //kafkaTemplate.send(client.getApp_id(), objectMapper.writeValueAsString(events));

                flag = true;
//                message = "카프카 전송 완료";
                message = "success";


            } catch (Exception e) {
                flag = false;
                message = "키값과 일치하는 고객 없음";
            }

        } else {
            flag = false;
            message = "키값 존재하지 않음.";
        }

        returnMap.put("flag", flag);
        returnMap.put("message", message);
        return returnMap;
    }

    private MetaInfo getMetaInfo(Map<String, Object> contents){

        return null;
    }
}
