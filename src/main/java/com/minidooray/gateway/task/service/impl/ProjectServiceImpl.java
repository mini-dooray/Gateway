package com.minidooray.gateway.task.service.impl;

import com.minidooray.gateway.task.adapter.TaskAdapter;
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
}
