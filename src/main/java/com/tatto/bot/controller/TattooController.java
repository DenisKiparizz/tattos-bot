package com.tatto.bot.controller;

import com.tatto.bot.annotations.CreateTattooApi;
import com.tatto.bot.annotations.DeleteTattooApi;
import com.tatto.bot.annotations.GetAllTattoosApi;
import com.tatto.bot.annotations.UpdateTattooApi;
import com.tatto.bot.dto.TattooDto;
import com.tatto.bot.dto.request.TattooRequest;
import com.tatto.bot.service.TattooService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tattoo")
public class TattooController {

    private final TattooService tattooService;

    @GetMapping
    @GetAllTattoosApi
    public List<TattooDto> getAll() {
        return tattooService.getAll();
    }

    @PostMapping
    @CreateTattooApi
    public TattooDto create(TattooDto tattoo) {
        return tattooService.create(tattoo);
    }

    @PutMapping("{id}")
    @UpdateTattooApi
    public TattooDto update(@ApiParam(value = "Tattoo ID. Make sure that value is positive", required = true, example = "1")
                            @PathVariable Long id,
                            TattooRequest tattooRequest) {
        return tattooService.update(id, tattooRequest);
    }

    @DeleteMapping("{id}")
    @DeleteTattooApi
    public void delete(@ApiParam(value = "Tattoo ID. Make sure that value is positive", required = true, example = "1")
                      @PathVariable Long id) {
        tattooService.delete(id);
    }
}
