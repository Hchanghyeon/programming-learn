package com.document.swagger;

import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberController {

    @Operation(summary = "회원 조회", description = "회원 조회 API입니다.")
    @ApiResponse(description = "설명", responseCode = "200"
    )
    @GetMapping
    public Response<List<MemberResponse>> getMembers(){
        MemberResponse member1 = MemberResponse.builder()
                .uuid(UUID.randomUUID())
                .name("changhyeon")
                .age(20)
                .memberType(MemberType.BLACK)
                .married(true)
                .build();

        MemberResponse member2 = MemberResponse.builder()
                .uuid(UUID.randomUUID())
                .name("test")
                .age(20)
                .memberType(MemberType.VIP)
                .married(false)
                .build();

        return new Response<>(List.of(member1, member2));
    }
    @Operation(summary = "회원 생성", description = "회원 생성 API입니다.")
    @PostMapping
    public void saveMember(@RequestBody MemberRequest memberRequest){
        log.info(memberRequest.toString());
        MemberType memberType = memberRequest.getMemberType();

        System.out.println(memberType);
    }
}
