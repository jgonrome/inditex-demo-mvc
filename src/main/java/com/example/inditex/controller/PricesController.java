package com.example.inditex.controller;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import com.example.inditex.controller.dto.PricesDto;
import com.example.inditex.controller.exception.PricesNotFoundException;
import com.example.inditex.controller.mapper.PricesDtoMapper;
import com.example.inditex.service.PricesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

/**
 * @author Juan Francisco Gonzalez
 * 
 */
@RestController
@RequestMapping("/api/v1")
@Validated
@RequiredArgsConstructor
public class PricesController {

	private final PricesService pricesService;

	private final PricesDtoMapper mapper;

    /**
     * Gets Prices in an specific time.
     *
     * @param appTime
     * @param brandId
     * @param productId
     * @return ResponseEntity<PricesDto>
     */
    @Operation(summary = "Gets Prices in an specific time", description = "", tags = {"PRICES"})
    @GetMapping(value = "/prices",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Getted Prices",
                 content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PricesDto.class))})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<PricesDto> getPrices(
            @Parameter(name = "appTime", required = true, description = "Date of inquiry in format yyyy-MM-dd'T'HH:mm:ss.SSSXXX (2000-10-31T01:30:00.000-05:00)" )
            @RequestParam(value = "appTime", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime appTime,
	        @Parameter(name = "brandId", required = true, description = "Brand Id" )
	        @RequestParam(value = "brandId", required = true) @Min(value = 1, message = "Must be a positive Integer") long brandId,
            @Parameter(name = "productId", required = true, description = "Product Id")
            @RequestParam(value = "productId", required = true) @Min(value = 1, message = "Must be a positive Integer") long productId)  {
        
    	return pricesService.getPrices(appTime, brandId, productId)
        		.map( pricesEntity -> ResponseEntity.ok().body(mapper.from(pricesEntity)) )  
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Price Not Found", new PricesNotFoundException("Price Not Found")));
    }
	
}
