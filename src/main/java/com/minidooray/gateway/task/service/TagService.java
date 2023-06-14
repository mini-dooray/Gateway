package com.minidooray.gateway.task.service;

import com.minidooray.gateway.task.domain.request.RequestTagCreateDto;
import com.minidooray.gateway.task.domain.response.ResponseTagDto;

import java.util.List;

public interface TagService {

    boolean isTagExist(String name);

    void createTag(RequestTagCreateDto dto, Long seq);

    List<ResponseTagDto> getTags(Long seq);

    void deleteTag(Long seq);
}
