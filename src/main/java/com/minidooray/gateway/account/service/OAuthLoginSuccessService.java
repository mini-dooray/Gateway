package com.minidooray.gateway.account.service;

import com.minidooray.gateway.account.adapter.AccountAdapter;
import com.minidooray.gateway.account.domain.Account;
import com.minidooray.gateway.account.domain.AccountDetails;
import com.minidooray.gateway.github.service.GithubService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class OAuthLoginSuccessService extends DefaultOAuth2UserService {

    private final AccountAdapter accountAdapter;

    private final GithubService githubService;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        String email = githubService.getGithubEmail(oAuth2UserRequest.getAccessToken().getTokenValue());
        Account account = accountAdapter.getAccountByEmail(email);
        return new AccountDetails(account.getAccountId(), account.getPassword(),
                Collections.singletonList((GrantedAuthority) Collections.emptyList())
                ,oAuth2User.getAttributes());
    }
}
