package com.stock.adapters.driving.http.dto.response;


import com.stock.configuration.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
@Schema(description = Constants.PAGINATED_RESPONSE_DESCRIPTION)
public class PaginatedResponse<T> {
    @Schema(description = Constants.PAGINATED_RESPONSE_ENTITIES_DESCRIPTION,
            example = Constants.PAGINATED_RESPONSE_ENTITIES_EXAMPLE)
    private List<T> entities;
    @Schema(description = Constants.PAGINATED_RESPONSE_CURRENT_PAGE_DESCRIPTION,
            example = Constants.PAGINATED_RESPONSE_CURRENT_PAGE_EXAMPLE)
    private int currentPage;
    @Schema(description = Constants.PAGINATED_RESPONSE_TOTAL_ITEMS_DESCRIPTION,
            example = Constants.PAGINATED_RESPONSE_TOTAL_ITEMS_EXAMPLE)
    private Long totalItems;
    @Schema(description = Constants.PAGINATED_RESPONSE_TOTAL_PAGES_DESCRIPTION,
            example = Constants.PAGINATED_RESPONSE_TOTAL_PAGES_EXAMPLE)
    private int totalPages;
    @Schema(description = Constants.PAGINATED_RESPONSE_MESSAGE,
            example = Constants.PAGINATION_SUCCESSFUL)
    private String message;
}
