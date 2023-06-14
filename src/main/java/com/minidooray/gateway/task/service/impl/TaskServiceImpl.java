package com.minidooray.gateway.task.service.impl;

import com.minidooray.gateway.task.adapter.TaskAdapter;
import com.minidooray.gateway.task.domain.response.ResponseTaskDto;
import com.minidooray.gateway.task.domain.response.ResponseTaskListDto;
import com.minidooray.gateway.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskAdapter taskAdapter;

    @Override
    public List<ResponseTaskListDto> getTasks(Long seq) {
        return taskAdapter.getTaskList(seq);
    }

    @Override
    public ResponseTaskDto getTask(Long seq) {
        return taskAdapter.getTask(seq);
    }
}
