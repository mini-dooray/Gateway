package com.minidooray.gateway.task.service;

import com.minidooray.gateway.task.domain.response.ResponseTaskDto;
import com.minidooray.gateway.task.domain.response.ResponseTaskListDto;

import java.util.List;

public interface TaskService {
    List<ResponseTaskListDto> getTasks(Long seq);

    ResponseTaskDto getTask(Long seq);
}
