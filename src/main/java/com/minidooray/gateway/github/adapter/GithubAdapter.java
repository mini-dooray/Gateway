package com.minidooray.gateway.github.adapter;

import com.minidooray.gateway.account.domain.Account;
import com.minidooray.gateway.github.domain.GithubEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GithubAdapter {

    private final RestTemplate restTemplate;

    public List<GithubEmail> getGithubEmails(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
//        httpHeaders.setBearerAuth(token);
        httpHeaders.add("Accept", "application/vnd.github+json");
        httpHeaders.add("Authorization", "Bearer " + token);
        httpHeaders.add("X-GitHub-Api-Version", "2022-11-28");

        HttpEntity<String> requestEntity = new HttpEntity<>(token, httpHeaders);
        ResponseEntity<List<GithubEmail>> exchange = restTemplate.exchange("https://api.github.com/user/emails",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<GithubEmail>>() {
                });
        return exchange.getBody();
    }
}
