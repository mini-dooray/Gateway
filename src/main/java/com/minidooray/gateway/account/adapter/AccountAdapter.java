package com.minidooray.gateway.account.adapter;

import com.minidooray.gateway.account.domain.Account;
import com.minidooray.gateway.account.domain.RequestSignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountAdapter {

    private final RestTemplate restTemplate;

    private final String URL = "http://localhost:8002";

//    public String getId() {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
//
//        HttpEntity<>
//    }

    public Account getAccountByEmail(String email) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(email, httpHeaders);
        ResponseEntity<Account> exchange = restTemplate.exchange(URL + "/email/found/"+email,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Account>() {
                });
        return exchange.getBody();
    }

    public void signUpAccount(RequestSignUpDto dto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<RequestSignUpDto> requestEntity = new HttpEntity<>(dto, httpHeaders);
        ResponseEntity<HttpStatus> exchange = restTemplate.exchange(URL,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<HttpStatus>() {
                });

    }

    public boolean isId(String id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(id, httpHeaders);
        ResponseEntity<Boolean> exchange = restTemplate.exchange(URL,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    public boolean isEmail(String email) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(email, httpHeaders);
        ResponseEntity<Boolean> exchange = restTemplate.exchange(URL,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Boolean>() {
                });
        return exchange.getBody();
    }
}
