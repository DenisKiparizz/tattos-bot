package com.tatto.bot.controller;

import com.tatto.bot.dto.TattooDto;
import com.tatto.bot.service.TattooServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tattoo")
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
            @Valid TattooDto tattooDto) {
        return tattooService.updateTattoo(id, tattooDto);
    }

    @GetMapping("{style}")
    public List<TattooDto> findByStyle(@PathVariable
                                       @NotNull
                                       @Size(min = 2, message = "Style has to be 2+ char")
                                               String style) {
        return tattooService.findByStyle(style);
    }
}
