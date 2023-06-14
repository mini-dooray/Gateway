package com.minidooray.gateway.task.service.impl;

import com.minidooray.gateway.task.adapter.TaskAdapter;
import com.minidooray.gateway.task.domain.response.ResponsePriorityDto;
import com.minidooray.gateway.task.service.PriorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriorityServiceImpl implements PriorityService {

    private final TaskAdapter taskAdapter;

    @Override
    public List<ResponsePriorityDto> getPrioritys(Long seq) {

        List<ResponsePriorityDto> list = taskAdapter.getPrioritys(seq);
        if (list == null) {
            return null;
        }
        return list;
    }
}
