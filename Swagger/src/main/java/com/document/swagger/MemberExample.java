package com.document.swagger;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MemberExample {
    public static final String MEMBER_SUCCESS =
            "{\n"
            + "  \"errorResponse\": null,\n"
            + "  \"body\": [\n"
            + "    {\n"
            + "      \"uuid\": \"9f8c9e66-9294-45c9-8381-cc2466dfc318\",\n"
            + "      \"memberType\": \"memberBLACK\",\n"
            + "      \"name\": \"changhyeon\",\n"
            + "      \"age\": 20,\n"
            + "      \"married\": true\n"
            + "    },\n"
            + "    {\n"
            + "      \"uuid\": \"c4943f73-ca55-4f5a-9a3d-c17f940530fb\",\n"
            + "      \"memberType\": \"memberVIP\",\n"
            + "      \"name\": \"test\",\n"
            + "      \"age\": 20,\n"
            + "      \"married\": false\n"
            + "    }\n"
            + "  ]\n"
            + "}";

    public static final String MEMBER_FAILED =
             "{\n"
            + "  \"errorResponse\": {\n"
            + "    \"errorCode\": 3000,\n"
            + "    \"errorMessage\": \"비즈니스 예외입니다.\"\n"
            + "  },\n"
            + "  \"body\": null\n"
            + "}";

/*
    static {
        MEMBER_SUCCESS = createMemberSuccessExample();
        MEMBER_FAILED = createMemberFailedExample();
    }

    public static String createMemberSuccessExample() {
        List<MemberResponse> members = List.of(
                MemberResponse.builder()
                        .uuid(UUID.fromString("9f8c9e66-9294-45c9-8381-cc2466dfc318"))
                        .name("changhyeon")
                        .age(20)
                        .memberType(MemberType.BLACK)
                        .married(true)
                        .build(),
                MemberResponse.builder()
                        .uuid(UUID.fromString("c4943f73-ca55-4f5a-9a3d-c17f940530fb"))
                        .name("test")
                        .age(20)
                        .memberType(MemberType.VIP)
                        .married(false)
                        .build()
        );

        Response<List<MemberResponse>> response = new Response<>(members);

        return getString(response);
    }

    public static String createMemberFailedExample() {
        ErrorResponse errorResponse = new ErrorResponse(3000, "비즈니스 예외입니다.");
        Response<List<MemberResponse>> response = new Response<>(errorResponse);

        return getString(response);
    }

    private static String getString(final Response<List<MemberResponse>> response) {
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to create JSON example", e);
        }
    }*/
}
