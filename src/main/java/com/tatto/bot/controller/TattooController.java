package com.tatto.bot.controller;

import com.tatto.bot.dto.TattooDto;
import com.tatto.bot.dto.request.TattooRequest;
import com.tatto.bot.service.TattooService;
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
@RequestMapping("tattoo")
public class TattooController {

    private final TattooService tattooService;

    @PostMapping
    public TattooDto create(TattooDto tattoo) {
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
            @Valid TattooRequest tattooRequest) {
        return tattooService.update(id, tattooRequest);
    }
}
