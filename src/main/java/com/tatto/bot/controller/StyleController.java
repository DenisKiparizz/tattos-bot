package com.tatto.bot.controller;

import com.tatto.bot.annotations.CreateStyleApi;
import com.tatto.bot.dto.StyleDto;
import com.tatto.bot.dto.StyleRequest;
import com.tatto.bot.service.StyleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("style")
public class StyleController {

    public final StyleServiceImpl styleService;

    @GetMapping
    public List<StyleDto> getAll() {
        return styleService.getAll();
    }

    @PostMapping
    @CreateStyleApi
    public StyleDto create(
            @Valid StyleRequest styleDto) {
        return styleService.create(styleDto);
    }

    @PutMapping("{id}")
    public StyleDto id(@PathVariable Long id,
                       @Valid StyleRequest style) {
        return styleService.update(id, style);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        styleService.delete(id);
    }
}
