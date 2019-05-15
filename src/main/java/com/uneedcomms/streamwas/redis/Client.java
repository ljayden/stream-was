package com.uneedcomms.streamwas.redis;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data @Builder
@ToString
@RedisHash("client")
@EqualsAndHashCode(of = "key")
public class Client {

    @Id
    // dw_permissions Key 컬럼, 업체별 고유값
    private String key;
    // dw_permissions app_id 컬럼, 업체명
    private String app_id;
    // ims_domains status 컬럼, ims_domains.ims_app_id = dw_permissions.app_id
    private String status;
    // 업체별 config 정보 object
    private MetaInfo metainfo;
    // 업체별 Meta 정보 json string
    private String metajson;

}
