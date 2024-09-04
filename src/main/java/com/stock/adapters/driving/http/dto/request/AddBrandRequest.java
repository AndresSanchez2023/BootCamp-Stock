package com.stock.adapters.driving.http.dto.request;

import com.stock.domain.util.DomainConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddBrandRequest {
    @NotBlank(message = DomainConstants.FIELD_NAME_NULL_MESSAGE)
    @Size(min = 4, max = 50, message = DomainConstants.FIELD_NAME_VALIDATE_LENGTH_EXCEPTION_MESSAGE)
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = DomainConstants.FIELD_NAME_INVALID_MESSAGE)
    private final String name;
    @NotBlank(message = DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
    @Size(min = 17, max = 120, message = DomainConstants.FIELD_DESCRIPTION_BRAND_VALIDATE_LENGTH_EXCEPTION_MESSAGE)
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = DomainConstants.FIELD_DESCRIPTION_INVALID_MESSAGE)
    private final String description;
}
