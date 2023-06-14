package com.minidooray.gateway.task.controller;

import com.minidooray.gateway.task.adapter.TaskAdapter;
import com.minidooray.gateway.task.domain.request.RequestProjectCreateDto;
import com.minidooray.gateway.task.domain.request.RequestTagCreateDto;
import com.minidooray.gateway.task.domain.response.ResponsePriorityDto;
import com.minidooray.gateway.task.service.PriorityService;
import com.minidooray.gateway.task.service.ProjectService;
import com.minidooray.gateway.task.service.TagService;
import com.minidooray.gateway.task.validator.CheckProjectNameValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final TaskAdapter taskAdapter;

    private final ProjectService projectService;

    private final TagService tagService;

    private final PriorityService priorityService;

    private final RedisTemplate redisTemplate;

    private final CheckProjectNameValidator checkProjectNameValidator;

    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkProjectNameValidator);
    }

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

        return "redirect:/project/projects/" + seq + "/dashboard";
    }

    @GetMapping("/projects/{seq}/dashboard")
    public String dashBoard(Model model, @PathVariable Long seq) {
        model.addAttribute("project", projectService.getProject(seq));
        model.addAttribute("members", projectService.getProjectMembers(seq));
        model.addAttribute("tags", tagService.getTags(seq));
        model.addAttribute("projectSeq", seq);
        model.addAttribute("tag", new RequestTagCreateDto());

        int allStatus = 0;
        List<ResponsePriorityDto> priorityDtoList = priorityService.getPrioritys(seq);
        if(priorityDtoList != null) {
            for (ResponsePriorityDto dto : priorityDtoList) {
                allStatus += dto.getCount();
                model.addAttribute(dto.getPriorityStatus(), dto.getCount());
            }
            model.addAttribute("allStatus", allStatus);
        }

        return "dashboard";
    }

}
