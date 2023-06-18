package com.minidooray.gateway.task.adapter;

import com.minidooray.gateway.task.domain.request.RequestProjectCreateDto;
import com.minidooray.gateway.task.domain.request.RequestTagCreateDto;
import com.minidooray.gateway.task.domain.response.*;
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

    private final String URL = "http://localhost:8000";

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

    //태그 중복 조회
    public boolean isTag(String name) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(name, httpHeaders);
        ResponseEntity<Boolean> exchange = restTemplate.exchange(URL + "/tag/name?name=" + name,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Boolean>() {
                });
        return exchange.getBody();


    }

    //태그 추가
    public void createTag(RequestTagCreateDto dto, Long seq) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<RequestTagCreateDto> requestEntity = new HttpEntity<>(dto, httpHeaders);
        ResponseEntity<HttpStatus> exchange = restTemplate.exchange(URL + "/tag?projectSeq=" + seq,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
    }

    //태그 전체 조회
    public List<ResponseTagDto> getTags(Long seq) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Long> requestEntity = new HttpEntity<>(seq, httpHeaders);
        ResponseEntity<List<ResponseTagDto>> exchange = restTemplate.exchange(URL + "/tag/tags?projectSeq=" + seq,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<ResponseTagDto>>() {
                });
        return exchange.getBody();
    }

    //프로젝트 이름 중복 체크
    public boolean checkDuplicateProjectName(String name) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(name, httpHeaders);
        ResponseEntity<Boolean> exchange = restTemplate.exchange(URL + "/project/name?name=" + name,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Boolean>() {
                });
        return exchange.getBody();
    }

    //태그 삭제
    public void deleteTag(Long seq) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Long> requestEntity = new HttpEntity<>(seq, httpHeaders);
        ResponseEntity<Void> exchange = restTemplate.exchange(URL + "/tag/" + seq,
                HttpMethod.DELETE,
                requestEntity,
                new ParameterizedTypeReference<Void>() {
                });
    }

    //taskStatus 전체 조회
    public List<ResponsePriorityDto> getPrioritys(Long seq) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Long> requestEntity = new HttpEntity<>(seq, httpHeaders);
        ResponseEntity<List<ResponsePriorityDto>> exchange = restTemplate.exchange(URL + "/task/tasksStatus?projectSeq=" + seq,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        if (exchange.getBody() == null) {
            return null;
        }
        return exchange.getBody();
    }

    //task 전체 조회
    public List<ResponseTaskListDto> getTaskList(Long seq) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Long> requestEntity = new HttpEntity<>(seq, httpHeaders);
        ResponseEntity<List<ResponseTaskListDto>> exchange = restTemplate.exchange(URL + "/task/tasks?projectSeq=" + seq,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<ResponseTaskListDto>>() {
                });
        return exchange.getBody();
    }

    //task 단일 조회
    public ResponseTaskDto getTask(Long seq) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Long> requestEntity = new HttpEntity<>(seq, httpHeaders);
        ResponseEntity<ResponseTaskDto> exchange = restTemplate.exchange(URL + "/task/" + seq,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<ResponseTaskDto>() {
                });
        return exchange.getBody();
    }
}
