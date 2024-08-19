package com.proto.protobufclient;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.proto.file.MemberRequest;

@Component
public class ProtobufApi {

    private final RestTemplate restTemplate;

    public ProtobufApi(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public MemberRequest.Member getMember(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(ProtobufHttpMessageConverter.PROTOBUF));
        headers.setContentType(ProtobufHttpMessageConverter.PROTOBUF);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<MemberRequest.Member> response = restTemplate.exchange(
                "http://localhost:8080",
                HttpMethod.GET,
                entity,
                MemberRequest.Member.class
        );

        return response.getBody();
    }
}
