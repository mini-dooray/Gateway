package com.minidooray.gateway.task.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ResponseCustomTaskListDto {

    private Long seq;

    private String title;

    private String register;
}
