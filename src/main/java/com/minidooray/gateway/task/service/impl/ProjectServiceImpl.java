package com.minidooray.gateway.task.service.impl;

import com.minidooray.gateway.task.adapter.TaskAdapter;
import com.minidooray.gateway.task.domain.request.RequestProjectCreateDto;
import com.minidooray.gateway.task.domain.response.ResponseMemberDto;
import com.minidooray.gateway.task.domain.response.ResponseProjectDto;
import com.minidooray.gateway.task.domain.response.ResponseProjectListDto;
import com.minidooray.gateway.task.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final TaskAdapter taskAdapter;

    @Override
    public List<ResponseProjectListDto> getProjects(Long seq) {
        return taskAdapter.getProjectsBySeq(seq);
    }

    @Override
    public Long createProject(Integer seq, RequestProjectCreateDto dto) {
        return taskAdapter.createProject(seq, dto);
    }

    @Override
    public ResponseProjectDto getProject(Long seq) {
        return taskAdapter.getProjectBySeq(seq);
    }

    @Override
    public List<ResponseMemberDto> getProjectMembers(Long seq) {
        return taskAdapter.getProjectMembers(seq);
    }
}
