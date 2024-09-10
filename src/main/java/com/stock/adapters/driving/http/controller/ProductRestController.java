package com.stock.adapters.driving.http.controller;

import com.stock.adapters.driving.http.adapter.InProductAdapter;
import com.stock.adapters.driving.http.dto.request.AddProductRequest;
import com.stock.configuration.Constants;
import com.stock.domain.exception.InvalidCategoryListException;
import com.stock.domain.exception.ProductAlreadyExistsException;
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
@RequestMapping("/product")
@RequiredArgsConstructor
@Tag(name = Constants.PRODUCT, description = Constants.PRODUCT_CONTROLLER_DESCRIPTION)
public class ProductRestController {

    private final InProductAdapter inProductAdapter;

    @Operation(summary = Constants.ADD_PRODUCT)
    @ApiResponse(
            responseCode = com.stock.configuration.Constants.CODE_CREATED,
            description = Constants.PRODUCT_CREATED_SUCCESS
    )
    @ApiResponse(
            responseCode = Constants.CODE_BAD_REQUEST,
            description = Constants.INVALID_REQUEST_ERROR,
            content = @Content(mediaType = Constants.MEDIA_TYPE, schema = @Schema(implementation = MethodArgumentNotValidException.class))
    )
    @ApiResponse(
            responseCode = Constants.CODE_BAD_REQUEST,
            description = Constants.INVALID_REQUEST_ERROR,
            content = @Content(mediaType = Constants.MEDIA_TYPE, schema = @Schema(implementation = InvalidCategoryListException.class))
    )
    @ApiResponse(
            responseCode = Constants.CODE_BAD_REQUEST,
            description = Constants.INVALID_REQUEST_ERROR,
            content = @Content(mediaType = Constants.MEDIA_TYPE, schema = @Schema(implementation = ProductAlreadyExistsException.class))
    )
    @PostMapping("/")
    public ResponseEntity<Void> addProduct(@Validated @RequestBody @Parameter(description = Constants.SCHEMAS_CREATED_PRODUCT_REQUEST_PARAMETER) AddProductRequest productRequest) {
        inProductAdapter.addProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
