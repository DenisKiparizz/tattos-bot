package com.tatto.bot.controller;

import com.tatto.bot.dto.TattooDto;
import com.tatto.bot.service.TattooServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tattooP")
public class TattooController {

    public final TattooServiceImpl tattooService;

    @PostMapping
    public TattooDto add(TattooDto tattoo) {
        return tattooService.create(tattoo);
    }

    @GetMapping
    public List<TattooDto> getAll() {
        return tattooService.getAll();
    }

    @DeleteMapping
    public void delete(Long id) {
        tattooService.delete(id);
    }

    @PutMapping("{id}")
    public TattooDto update(
            @PathVariable(name = "id") Long id,
            TattooDto tattooDto) {
        return tattooService.updateTattoo(id, tattooDto);
    }

    @GetMapping("{style}")
    public List<TattooDto> findByStyle(@PathVariable String style) {
        return tattooService.findByStyle(style);
    }
}
