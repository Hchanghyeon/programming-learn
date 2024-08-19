package com.proto.protobufclient;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.proto.file.MemberRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCode {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void API요청(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(ProtobufHttpMessageConverter.PROTOBUF));
        headers.setContentType(ProtobufHttpMessageConverter.PROTOBUF);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<MemberRequest> response = restTemplate.exchange(
                "http://localhost:8080",
                HttpMethod.GET,
                entity,
                MemberRequest.class
        );

        response.getBody();
    }
}
