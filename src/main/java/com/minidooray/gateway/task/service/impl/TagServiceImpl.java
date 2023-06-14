package com.minidooray.gateway.task.service.impl;

import com.minidooray.gateway.task.adapter.TaskAdapter;
import com.minidooray.gateway.task.domain.request.RequestTagCreateDto;
import com.minidooray.gateway.task.domain.response.ResponseTagDto;
import com.minidooray.gateway.task.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TaskAdapter taskAdapter;

    @Override
    public boolean isTagExist(String name) {
        return taskAdapter.isTag(name);
    }

    @Override
    public void createTag(RequestTagCreateDto dto, Long seq) {
        taskAdapter.createTag(dto, seq);
    }

    @Override
    public List<ResponseTagDto> getTags(Long seq) {
        return taskAdapter.getTags(seq);
    }

    @Override
    public void deleteTag(Long seq) {
        taskAdapter.deleteTag(seq);
    }
}
