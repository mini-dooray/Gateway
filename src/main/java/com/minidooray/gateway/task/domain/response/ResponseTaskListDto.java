package com.minidooray.gateway.task.domain.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ResponseTaskListDto {

    private Long seq;

    private String title;

    List<MemberTaskDto> memberTasks;

    ResponseMemberDto registrant;

    @Getter
    public static class MemberTaskDto {
        private String type;

        private ResponseMemberDto member;


    }
}
