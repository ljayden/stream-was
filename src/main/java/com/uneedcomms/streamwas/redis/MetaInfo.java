package com.uneedcomms.streamwas.redis;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MetaInfo {

    List<String> event;
    int event_count;
    String topic;

}
