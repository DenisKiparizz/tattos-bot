package com.tatto.bot.annotations;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ApiOperation(value = "Delete particular style by id")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully deleted"),
        @ApiResponse(code = 400, message = "Bag request. Make sure the ID for delete is correct"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @ interface DeleteStyleApi {
}
