package com.minidooray.gateway.github.service;

import com.minidooray.gateway.github.adapter.GithubAdapter;
import com.minidooray.gateway.github.domain.GithubEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubService {

    private final GithubAdapter githubAdapter;

    public String getGithubEmail(String token) {
        List<GithubEmail> emailList = githubAdapter.getGithubEmails(token);

        for(GithubEmail email : emailList) {
            if(email.isPrimary()) {
                return email.getEmail();
            }
        }
        return null;
    }
}
