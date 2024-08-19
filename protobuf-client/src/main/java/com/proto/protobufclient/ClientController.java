package com.proto.protobufclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proto.file.MemberRequest;

@RestController
public class ClientController {

    private final ProtobufApi protobufApi;

    public ClientController(final ProtobufApi protobufApi) {
        this.protobufApi = protobufApi;
    }

    @GetMapping
    public void getMember(){
        MemberRequest.Member memberRequest = protobufApi.getMember();
        System.out.println(memberRequest);
    }
}
