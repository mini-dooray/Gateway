package com.minidooray.gateway.task.domain.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class RequestProjectCreateDto {

    @NotBlank
    @Size(min = 2, max = 50, message = "프로젝트 이름은 2자 이상 50자 이하 입니다.")
    private String name;

    @NotBlank
    @Size(min = 1, message = "내용을 입력해주세요.")
    private String content;

    private Integer status;
}
