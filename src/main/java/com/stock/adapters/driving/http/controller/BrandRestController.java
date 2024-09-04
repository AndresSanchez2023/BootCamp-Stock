package com.stock.adapters.driving.http.controller;

import com.stock.adapters.driving.http.adapter.InBrandAdapter;
import com.stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.stock.domain.exception.BrandAlreadyExistsException;
import com.stock.domain.util.DomainConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
@Tag(name = DomainConstants.BRAND, description = DomainConstants.BRAND_CONTROLLER_DESCRIPTION)
public class BrandRestController {
    private final InBrandAdapter inBrandAdapter;
    @Operation(summary = DomainConstants.ADD_BRAND)
    @ApiResponse(
            responseCode = DomainConstants.CODE_CREATED,
            description = DomainConstants.BRAND_CREATED_SUCCESS
    )
    @ApiResponse(
            responseCode = DomainConstants.CODE_BAD_REQUEST,
            description = DomainConstants.INVALID_REQUEST_ERROR,
            content = @Content(mediaType = DomainConstants.MEDIA_TYPE, schema = @Schema(implementation = MethodArgumentNotValidException.class))
    )
    @ApiResponse(
            responseCode = DomainConstants.CODE_BAD_REQUEST,
            description = DomainConstants.INVALID_REQUEST_ERROR,
            content = @Content(mediaType = DomainConstants.MEDIA_TYPE, schema = @Schema(implementation = BrandAlreadyExistsException.class))
    )
    @PostMapping("/")
    public ResponseEntity<Void> addBrand (@Validated @RequestBody @Parameter(description = DomainConstants.SCHEMAS_CREATED_BRAND_REQUEST_PARAMETER)AddBrandRequest brandRequest){
        inBrandAdapter.addBrand(brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
