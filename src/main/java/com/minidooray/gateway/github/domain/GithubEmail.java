package com.minidooray.gateway.github.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubEmail {

    private String email;

    private boolean primary;

    private boolean verified;

    private String visibility;
}
