package com.uneedcomms.streamwas.runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uneedcomms.streamwas.redis.Client;
import com.uneedcomms.streamwas.redis.ClientRepository;
import com.uneedcomms.streamwas.redis.MetaInfo;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RedisRunner implements ApplicationRunner {

    private ClientRepository clientRepository;
    private ObjectMapper objectMapper;

    public RedisRunner(ClientRepository clientRepository, ObjectMapper objectMapper) {
        this.clientRepository = clientRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<String> events = new ArrayList<>();
        events.add("purchase");
        events.add("pageView");
        events.add("n_store_zzim");
        MetaInfo metaInfo = MetaInfo.builder()
                .event(events)
                .event_count(events.size())
                .build();

        String jsonStr = objectMapper.writeValueAsString(metaInfo);


        Client client = Client.builder()
                .key("d3d16d94-e633-11e7-80c1-9a214cf093ae")
                .app_id("uneedcommscom")
                .status("active")
                .metainfo(metaInfo)
                .metajson(jsonStr)
                .build();
        Client savedClient01 = clientRepository.save(client);
        System.out.println(savedClient01);

        client = Client.builder()
                .key("01816281-4171-457a-8428-701b97ee4401")
                .app_id("justone")
                .status("inactive")
                .metainfo(metaInfo)
                .metajson(jsonStr)
                .build();
        Client savedClient2 = clientRepository.save(client);
        System.out.println(savedClient2);

        client = Client.builder()
                .key("db9e3a7c-fe9b-4107-b61c-bba47553f05c")
                .app_id("roompacker")
                .status("active")
                .metainfo(metaInfo)
                .metajson(jsonStr)
                .build();
        Client savedClient03 = clientRepository.save(client);
        System.out.println(savedClient03);

        Optional<Client> optionalPermission = clientRepository.findById("d3d16d94-e633-11e7-80c1-9a214cf093ae");

        optionalPermission.orElseThrow(Exception::new).getMetainfo().getEvent().forEach(e->{
            System.out.println("client:metainfo:event = "+e);
        });

    }
}
