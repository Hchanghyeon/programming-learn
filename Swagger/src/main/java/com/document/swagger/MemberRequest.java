package com.document.swagger;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class MemberRequest {

     private MemberType memberType;
     private String name;
     private Integer age;
     private Boolean married;
}
