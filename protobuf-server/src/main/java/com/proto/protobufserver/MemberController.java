package com.proto.protobufserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proto.file.MemberRequest;

@RestController
public class MemberController {

    @GetMapping(produces = "application/x-protobuf")
    public MemberRequest.Member getMember() {
        return MemberRequest.Member.newBuilder()
                .setId("1")
                .setNickname("changhyeon")
                .setMemberType(MemberRequest.MemberType.VIP)
                .build();
    }
}
