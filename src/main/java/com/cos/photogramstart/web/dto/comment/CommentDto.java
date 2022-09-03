package com.cos.photogramstart.web.dto.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentDto {

    @NotBlank   // 빈값이거나 null 체크
    private String content;
    @NotNull   //  빈값 체크
    private int imageId;
}
