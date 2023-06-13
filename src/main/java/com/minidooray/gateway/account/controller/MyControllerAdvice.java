package com.minidooray.gateway.account.controller;

import com.minidooray.gateway.task.domain.response.ResponseProjectListDto;
import com.minidooray.gateway.task.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
@RequiredArgsConstructor
public class MyControllerAdvice {

    private final ProjectService projectService;

    private final RedisTemplate redisTemplate;

    @ModelAttribute
    public void test(@CookieValue(name = "SESSION", required = false) String sessionId, HttpServletRequest request, Model model) {
        if (Objects.nonNull(sessionId)) {
            Integer seq = (Integer) redisTemplate.opsForHash().get(sessionId, "seq");
            if (seq != null) {
                List<ResponseProjectListDto> projectListDtoList = projectService.getProjects(Long.valueOf(seq));

                model.addAttribute("projectlist", projectListDtoList);
            }
        }

    }
}
