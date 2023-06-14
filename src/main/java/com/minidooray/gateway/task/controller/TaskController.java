package com.minidooray.gateway.task.controller;

import com.minidooray.gateway.task.domain.response.ResponseCustomTaskListDto;
import com.minidooray.gateway.task.domain.response.ResponseMemberDto;
import com.minidooray.gateway.task.domain.response.ResponseTaskListDto;
import com.minidooray.gateway.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/project/projects")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{seq}/taskList")
    public String getTaskList(Model model, @PathVariable Long seq) {
        model.addAttribute("projectSeq", seq);
        model.addAttribute("taskList", taskService.getTasks(seq));

        return "taskList";
    }

    @GetMapping("/{seq}/MANAGERList")
    public String getManagerList(Model model, @PathVariable Long seq) {
        return "";
    }

    @GetMapping("/{seq}/task/{taskSeq}")
    public String getTask(@PathVariable Long seq, @PathVariable Long taskSeq, Model model) {
        model.addAttribute("task", taskService.getTask(taskSeq));
        model.addAttribute("projectSeq", seq);

        return "task";
    }
}
