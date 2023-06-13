package com.minidooray.gateway.task.service;

import com.minidooray.gateway.task.domain.request.RequestProjectCreateDto;
import com.minidooray.gateway.task.domain.response.ResponseMemberDto;
import com.minidooray.gateway.task.domain.response.ResponseProjectDto;
import com.minidooray.gateway.task.domain.response.ResponseProjectListDto;

import java.util.List;

public interface ProjectService {
    List<ResponseProjectListDto> getProjects(Long seq);

    Long createProject(Integer seq, RequestProjectCreateDto dto);

    ResponseProjectDto getProject(Long seq);

    List<ResponseMemberDto> getProjectMembers(Long seq);
}
