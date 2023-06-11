package com.minidooray.gateway.task.service;

import com.minidooray.gateway.task.domain.response.ResponseProjectListDto;

import java.util.List;

public interface ProjectService {
    List<ResponseProjectListDto> getProjects(Long seq);
}
