package com.minidooray.gateway.task.controller;

import com.minidooray.gateway.task.adapter.TaskAdapter;
import com.minidooray.gateway.task.domain.request.RequestProjectCreateDto;
import com.minidooray.gateway.task.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final TaskAdapter taskAdapter;

    private final ProjectService projectService;

    private final RedisTemplate redisTemplate;

    @GetMapping("/createProject")
    public String getCreateProjectPage(Model model) {
        model.addAttribute("requestProjectCreateDto", new RequestProjectCreateDto());
        return "createProject";
    }

    @PostMapping("/createProject")
    public String createProject(@CookieValue(name = "SESSION", required = false) String sessionId,
                                @Valid RequestProjectCreateDto requestProjectCreateDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
//            model.addAttribute("RequestProjectCreateDto", dto);
            return "createProject";
        }
        Integer accountSeq = (Integer) redisTemplate.opsForHash().get(sessionId, "seq");
        Long seq = projectService.createProject(accountSeq, requestProjectCreateDto);

        return "/";
    }

    @GetMapping("/projects/{seq}/dashboard")
    public String dashBoard(Model model, @PathVariable Long seq) {
        model.addAttribute("project", projectService.getProject(seq));
        model.addAttribute("members", projectService.getProjectMembers(seq));

        return "dashboard";
    }
}
