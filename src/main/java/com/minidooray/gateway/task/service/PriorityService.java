package com.minidooray.gateway.task.service;

import com.minidooray.gateway.task.domain.response.ResponsePriorityDto;

import java.util.List;

public interface PriorityService {
    List<ResponsePriorityDto> getPrioritys(Long seq);
}
