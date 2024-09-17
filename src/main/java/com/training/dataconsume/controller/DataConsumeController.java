package com.training.dataconsume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.dataconsume.model.Users;
import com.training.dataconsume.service.DataConsumeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/consume")
@Tag(name = "Data Consume Controller", description = "Consume Redis data and store in PostgreSQL")
public class DataConsumeController {

    @Autowired
    private DataConsumeService dataConsumeService;

    @Operation(summary = "Data consuming")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data consumed successfully", content = @Content(schema = @Schema(implementation = Users.class)))
    })
    public ResponseEntity<Users> consumeData() {
        dataConsumeService.consumeData();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
