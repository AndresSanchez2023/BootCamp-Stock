package com.stock.adapters.driving.http.dto.request;

import com.stock.domain.util.DomainConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class AddProductRequest {

    @NotBlank(message = DomainConstants.FIELD_NAME_NULL_MESSAGE)
    @Size(min = 4, max = 50, message = DomainConstants.FIELD_NAME_VALIDATE_LENGTH_EXCEPTION_MESSAGE)
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = DomainConstants.FIELD_NAME_PRODUCT_INVALID_MESSAGE)
    private final String name;
    @NotBlank(message = DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
    @Size(min = 17, max = 90, message = DomainConstants.FIELD_DESCRIPTION_VALIDATE_LENGTH_EXCEPTION_MESSAGE)
    @Pattern(regexp = "^[A-Za-z0-9,.!? ]+$", message = DomainConstants.FIELD_DESCRIPTION_PRODUCT_INVALID_MESSAGE)
    private final String description;
    @Positive(message = DomainConstants.FIELD_QUANTITY_POSITIVE_MESSAGE)
    @NotNull(message = DomainConstants.FIELD_QUANTITY_NOTNULL_MESSAGE)
    private final Long quantity;
    @Positive(message = DomainConstants.FIELD_PRICE_POSITIVE_MESSAGE)
    @NotNull(message = DomainConstants.FIELD_PRICE_NOTNULL_MESSAGE)
    private final BigDecimal price;
    @NotNull(message = DomainConstants.BRAND_ID_NOTNULL_MESSAGE)
    private final Long brandId;
    @NotNull(message = DomainConstants.LIST_CATEGORY_IDS_IS_EMPTY)
    @Size(min = 1, message = DomainConstants.LIST_CATEGORY_IDS_NOT_EMPTY)
    private final List<Long> categoryListId;
}
