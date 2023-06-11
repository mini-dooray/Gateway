package com.minidooray.gateway.task.adapter;

import com.minidooray.gateway.task.domain.response.ResponseProjectListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskAdapter {

    private final RestTemplate restTemplate;

    private final String URL = "http://localhost:8002";

    //프로젝트 전체 조회
    public List<ResponseProjectListDto> getProjectsBySeq(Long seq) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Long> requestEntity = new HttpEntity<>(seq, httpHeaders);
        ResponseEntity<List<ResponseProjectListDto>> exchange = restTemplate.exchange(URL + "/project/projects?memberSeq=" + seq,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<ResponseProjectListDto>>() {
                });

        return exchange.getBody();
    }
}
