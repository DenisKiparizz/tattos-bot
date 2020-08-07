package com.tatto.bot.annotations;

import com.tatto.bot.dto.StyleDto;
import io.swagger.annotations.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ApiOperation(value = "Add new Tattoo")
@ApiResponses(value = {
        @ApiResponse(code = 201, message = "Successfully created", response = StyleDto.class),
        @ApiResponse(code = 400, message = "Bag request. Make sure the data is correct", response = StyleDto.class)
})
@ApiImplicitParams(value = {
        @ApiImplicitParam(name = "description", value = "Description of tattoo"),
        @ApiImplicitParam(name = "picture", value = "Tattoo picture"),
        @ApiImplicitParam(name = "style.id", value = "Id of Style(Only number)"),
        @ApiImplicitParam(name = "url", value = "Picture URL"),
})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CreateTattooApi {
}
