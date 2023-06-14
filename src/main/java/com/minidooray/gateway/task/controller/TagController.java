package com.minidooray.gateway.task.controller;

import com.minidooray.gateway.task.domain.request.RequestTagCreateDto;
import com.minidooray.gateway.task.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    @PostMapping("/tagCreate/{seq}")
    public String createTag(@PathVariable Long seq, @Valid RequestTagCreateDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
            return "redirect:/project/projects/"+seq+"/dashboard";
        }
        tagService.createTag(dto, seq);
        return "redirect:/project/projects/" + seq + "/dashboard";
    }

    @PostMapping("/delete/{tagSeq}/{seq}")
    public String deleteTag(@PathVariable Long tagSeq, @PathVariable Long seq) {
        tagService.deleteTag(tagSeq);
        return "redirect:/project/projects/" + seq + "/dashboard";
    }
}
