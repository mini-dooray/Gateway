package com.minidooray.gateway.task.domain.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class RequestTagCreateDto {

    @NotBlank
    @Size(min = 2, max = 50, message = "태그는 최소 2자 이상 50자 이하 입니다.")
    private String name;
}
