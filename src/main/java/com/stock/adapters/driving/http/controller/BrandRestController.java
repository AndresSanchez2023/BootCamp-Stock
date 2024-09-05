package com.stock.adapters.driving.http.controller;

import com.stock.adapters.driving.http.adapter.InBrandAdapter;
import com.stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.stock.adapters.driving.http.dto.response.BrandResponse;
import com.stock.adapters.driving.http.dto.response.PaginatedResponse;
import com.stock.configuration.Constants;
import com.stock.domain.exception.BrandAlreadyExistsException;
import com.stock.domain.model.Pagination;
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
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
@Tag(name = Constants.BRAND, description = Constants.BRAND_CONTROLLER_DESCRIPTION)
public class BrandRestController {
    private final InBrandAdapter inBrandAdapter;
    @Operation(summary = Constants.ADD_BRAND)
    @ApiResponse(
            responseCode = com.stock.configuration.Constants.CODE_CREATED,
            description = Constants.BRAND_CREATED_SUCCESS
    )
    @ApiResponse(
            responseCode = Constants.CODE_BAD_REQUEST,
            description = Constants.INVALID_REQUEST_ERROR,
            content = @Content(mediaType = Constants.MEDIA_TYPE, schema = @Schema(implementation = MethodArgumentNotValidException.class))
    )
    @ApiResponse(
            responseCode = Constants.CODE_BAD_REQUEST,
            description = Constants.INVALID_REQUEST_ERROR,
            content = @Content(mediaType = Constants.MEDIA_TYPE, schema = @Schema(implementation = BrandAlreadyExistsException.class))
    )
    @PostMapping("/")
    public ResponseEntity<Void> addBrand (@Validated @RequestBody @Parameter(description = Constants.SCHEMAS_CREATED_BRAND_REQUEST_PARAMETER)AddBrandRequest brandRequest){
        inBrandAdapter.addBrand(brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = Constants.LIST_BRAND)
    @ApiResponse(
            responseCode = Constants.CODE_SUCCESS,
            description = Constants.BRAND_LIST_SUCCESS
    )
    @ApiResponse(
            responseCode = Constants.CODE_BAD_REQUEST,
            description = Constants.INVALID_REQUEST_ERROR,
            content = @Content(mediaType = Constants.MEDIA_TYPE, schema = @Schema(implementation = MethodArgumentNotValidException.class))
    )
    @GetMapping("/")
    public ResponseEntity<PaginatedResponse<BrandResponse>> getAllBrandsPaginated(@RequestParam(value = com.stock.configuration.Constants.PAGE_STRING_VALUE_CONTROLLER, defaultValue = com.stock.configuration.Constants.PAGE_DEFAULT_VALUE_CONTROLLER) Integer page,
                                                                                  @RequestParam(value = com.stock.configuration.Constants.SIZE_STRING_VALUE_CONTROLLER, defaultValue = com.stock.configuration.Constants.SIZE_DEFAULT_VALUE_CONTROLLER) Integer size,
                                                                                  @RequestParam(value = com.stock.configuration.Constants.SORT_FIELD_STRING_VALUE_CONTROLLER, defaultValue = com.stock.configuration.Constants.SORT_FIELD_DEFAULT_VALUE_CONTROLLER) String sort,
                                                                                  @RequestParam(value = com.stock.configuration.Constants.SORT_DIRECTION_STRING_VALUE_CONTROLLER, defaultValue = com.stock.configuration.Constants.SORT_DIRECTION_DEFAULT_VALUE_CONTROLLER) String sortDirection) {
        Pagination pagination = new Pagination(page, size, sort, sortDirection);
        return ResponseEntity.status(HttpStatus.OK).body(inBrandAdapter.getAllBrandsPaginated(pagination));
    }
}

