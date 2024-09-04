package com.stock.adapters.driving.http.controller;

import com.stock.configuration.Constants;
import com.stock.domain.exception.InvalidArgumentsInFieldException;
import com.stock.domain.model.Pagination;
import com.stock.adapters.driving.http.dto.request.AddCategoryRequest;
import com.stock.adapters.driving.http.dto.response.CategoryResponse;
import com.stock.adapters.driving.http.dto.response.PaginatedResponse;
import com.stock.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.stock.domain.api.ICategoryServicePort;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Tag(name = Constants.CATEGORY, description = Constants.CATEGORY_CONTROLLER_DESCRIPTION)
public class CategoryRestControllerAdapter {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;

    @Operation(summary = Constants.ADD_CATEGORY)
    @ApiResponse(
            responseCode = Constants.CODE_CREATED,
            description = Constants.CATEGORY_CREATED_SUCCESS
    )
    @ApiResponse(
            responseCode = Constants.CODE_BAD_REQUEST,
            description = Constants.INVALID_REQUEST_ERROR,
            content = @Content(mediaType = Constants.MEDIA_TYPE, schema = @Schema(implementation = InvalidArgumentsInFieldException.class))
    )
    @PostMapping("/")
    public ResponseEntity<Void> addCategory(@Validated @RequestBody @Parameter(description = Constants.SCHEMAS_CREATED_CATEGORY_REQUEST_PARAMETER) AddCategoryRequest request) {
        categoryServicePort.saveCategory(categoryRequestMapper.addRequestToCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = Constants.LIST_CATEGORY)
    @ApiResponse(
            responseCode = Constants.CODE_SUCCESS,
            description = Constants.CATEGORY_LIST_SUCCESS,
            content = @Content(mediaType = Constants.MEDIA_TYPE, schema = @Schema(implementation = PaginatedResponse.class))
    )
    @GetMapping("/")
    public ResponseEntity<PaginatedResponse<CategoryResponse>> getAllCategoriesPaginated(@RequestParam(value = Constants.PAGE_STRING_VALUE_CONTROLLER, defaultValue = Constants.PAGE_DEFAULT_VALUE_CONTROLLER) Integer page,
                                                                                         @RequestParam(value = Constants.SIZE_STRING_VALUE_CONTROLLER, defaultValue = Constants.SIZE_DEFAULT_VALUE_CONTROLLER) Integer size,
                                                                                         @RequestParam(value = Constants.SORT_FIELD_STRING_VALUE_CONTROLLER, defaultValue = Constants.SORT_FIELD_DEFAULT_VALUE_CONTROLLER) String sort,
                                                                                         @RequestParam(value = Constants.SORT_DIRECTION_STRING_VALUE_CONTROLLER, defaultValue = Constants.SORT_DIRECTION_DEFAULT_VALUE_CONTROLLER) String sortDirection) {
        Pagination pagination = new Pagination(page, size, sort, sortDirection);
        PaginatedResponse<CategoryResponse> pageResponse = categoryRequestMapper.addPaginatedToResponse
                (categoryServicePort.paginate(pagination));
        return ResponseEntity.status(HttpStatus.OK).body(pageResponse);
    }
}
