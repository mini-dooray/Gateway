package com.minidooray.gateway.account.controller;

import com.minidooray.gateway.account.domain.Account;
import com.minidooray.gateway.account.service.AccountService;
import com.minidooray.gateway.task.domain.response.ResponseProjectListDto;
import com.minidooray.gateway.task.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final RedisTemplate<String, Object> redisTemplate;

    private final ProjectService projectService;

    private final AccountService accountService;

    @GetMapping("/")
    public String home(@CookieValue(name = "SESSION", required = false) String sessionId, Model model) {

        if (Objects.isNull(sessionId)) {
            return "redirect:/login";
        } else {
            String username = (String) redisTemplate.opsForHash().get(sessionId, "username");
            String authority = (String) redisTemplate.opsForHash().get(sessionId, "authority");
            Integer seq = (Integer) redisTemplate.opsForHash().get(sessionId, "seq");
            Account account = accountService.getAccountById(username);


            List<ResponseProjectListDto> projectListDtoList = projectService.getProjects(Long.valueOf(seq));

            model.addAttribute("username", username);
            model.addAttribute("authority", authority);
            model.addAttribute("projectlist", projectListDtoList);
            model.addAttribute("account", account);

            return "home";
        }
    }
}
