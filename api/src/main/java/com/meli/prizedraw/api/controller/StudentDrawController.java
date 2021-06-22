package com.meli.prizedraw.api.controller;

import com.meli.prizedraw.api.model.ErrorResponseModel;
import com.meli.prizedraw.api.model.StudentModel;
import com.meli.prizedraw.api.service.StudentDrawService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Tag(name = "Student Draws")
@Validated
@RequestMapping("/draws")
@RestController
public class StudentDrawController {
  @Autowired
  private StudentDrawService service;

  @Operation(summary = "Draw students", responses = {
    @ApiResponse(
      responseCode = "200",
      content = @Content(
        array = @ArraySchema(schema = @Schema(implementation = StudentModel.class)),
        mediaType = "application/json")),
    @ApiResponse(
      responseCode = "400",
      content = @Content(
        schema = @Schema(implementation = ErrorResponseModel.class),
        mediaType = "application/json")),
    @ApiResponse(
      responseCode = "500",
      content = @Content(
        schema = @Schema(implementation = ErrorResponseModel.class),
        mediaType = "application/json"))
  })
  @GetMapping("/")
  public Flux<StudentModel> draw(
    @Valid @Min(value = 0, message = "The count parameter must have a value greater than zero.")
    @RequestParam(value = "count", defaultValue = "10") Integer count) {
    return this.service.draw(count);
  }
}
