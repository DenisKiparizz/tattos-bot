package com.tatto.bot.controller;

import com.tatto.bot.dto.TattooDto;
import com.tatto.bot.service.TattoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tattooP")
public class TattooController {
    @Autowired
    TattoServiceImpl tattooService;

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

}
