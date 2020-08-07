package com.tatto.bot.annotations;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@ApiOperation(value = "View a list of available styles")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful")
})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @ interface GetAllStylesApi {
}
