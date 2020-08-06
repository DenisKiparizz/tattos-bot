package com.tatto.bot.controller;

import com.tatto.bot.annotations.CreateStyleApi;
import com.tatto.bot.dto.StyleDto;
import com.tatto.bot.dto.request.StyleRequest;
import com.tatto.bot.service.StyleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("style")
public class StyleController {

    private final StyleService styleService;

    @GetMapping
    public List<StyleDto> getAll() {
        return styleService.getAll();
    }

    @PostMapping
    @CreateStyleApi
    public StyleDto create(
            @Valid StyleDto styleDto) {
        return styleService.create(styleDto);
    }

    @PutMapping("{id}")
    public StyleDto update(@PathVariable Long id,
                           @Valid StyleRequest style) {
        return styleService.update(id, style);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        styleService.delete(id);
    }
}
