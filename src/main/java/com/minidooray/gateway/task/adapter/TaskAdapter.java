package com.minidooray.gateway.task.adapter;

import com.minidooray.gateway.task.domain.request.RequestProjectCreateDto;
import com.minidooray.gateway.task.domain.response.ResponseMemberDto;
import com.minidooray.gateway.task.domain.response.ResponseProjectDto;
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

    //프로젝트 등록
    public Long createProject(Integer seq, RequestProjectCreateDto dto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<RequestProjectCreateDto> requestEntity = new HttpEntity<>(dto, httpHeaders);
        ResponseEntity<ResponseProjectDto> exchange = restTemplate.exchange(URL + "/project?memberSeq=" + seq.longValue(),
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<ResponseProjectDto>() {
                });
        return exchange.getBody().getSeq();
    }

    //프로젝트 조회
    public ResponseProjectDto getProjectBySeq(Long seq) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Long> requestEntity = new HttpEntity<>(seq, httpHeaders);
        ResponseEntity<ResponseProjectDto> exchange = restTemplate.exchange(URL + "/project/" + seq,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<ResponseProjectDto>() {
                });
        return exchange.getBody();
    }

    //프로젝트 멤버 전체 조회
    public List<ResponseMemberDto> getProjectMembers(Long seq) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Long> requestEntity = new HttpEntity<>(seq, httpHeaders);
        ResponseEntity<List<ResponseMemberDto>> exchange = restTemplate.exchange(URL + "/project/" + seq + "/projectMember/members",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<ResponseMemberDto>>() {
                });
        return exchange.getBody();
    }
}
