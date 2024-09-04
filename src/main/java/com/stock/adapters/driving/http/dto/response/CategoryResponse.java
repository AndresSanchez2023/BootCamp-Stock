package com.stock.adapters.driving.http.dto.response;

import com.stock.configuration.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = Constants.CATEGORY_RESPONSE_CLASS_DESCRIPTION)
public class CategoryResponse {

    @Schema(description = Constants.CATEGORY_RESPONSE_ID,
            example = Constants.CATEGORY_RESPONSE_ID_EXAMPLE)
    private final Long id;
    @Schema(description = Constants.CATEGORY_RESPONSE_NAME,
            example = Constants.CATEGORY_RESPONSE_NAME_EXAMPLE)
    private final String name;
    @Schema(description = Constants.CATEGORY_RESPONSE_DESCRIPTION,
            example = Constants.CATEGORY_RESPONSE_DESCRIPTION_EXAMPLE)
    private final String description;
}
