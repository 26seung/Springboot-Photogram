package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscribeDto {

    private int id;
    private String username;
    private String profileImageUrl;
    private Integer subscribeState;         //  Maria DB 에서는 int 사용시 스칼라조인의 true 값을 리턴을 받지 못하여서 Integer 를 사용
    private Integer equalUserState;
}
