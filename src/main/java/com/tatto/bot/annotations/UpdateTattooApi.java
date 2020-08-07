package com.tatto.bot.annotations;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ApiOperation(value = "Update Tattoo")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully updated"),
        @ApiResponse(code = 400, message = "Bag request. Make sure the ID for update is correct"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
})
@ApiImplicitParams(value = {
        @ApiImplicitParam(name = "description", value = "Description of tattoo"),
        @ApiImplicitParam(name = "picture", value = "Tattoo picture"),
        @ApiImplicitParam(name = "style.id", value = "Id of Style"),
        @ApiImplicitParam(name = "url", value = "Picture URL"),
})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UpdateTattooApi {
}
