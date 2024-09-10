package com.stock.domain.util;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public final class DomainConstants {


    private DomainConstants() {
        throw new IllegalArgumentException("Utility Class");
    }

    // Category Create -----------------------------

    //Name Field
    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_NAME_VALIDATE_LENGTH_EXCEPTION_MESSAGE = "The field 'name' cannot be less than 4 characters or more than 50 characters";
    public static final String FIELD_DESCRIPTION_VALIDATE_LENGTH_EXCEPTION_MESSAGE = "The field 'description' cannot be less than 17 characters or more than exceed 90 characters";

    public static final String FIELD_DESCRIPTION_BRAND_VALIDATE_LENGTH_EXCEPTION_MESSAGE = "The field 'description' cannot be less than 17 characters or more than exceed 120 characters";
    public static final String FIELD_NAME_INVALID_MESSAGE = "The field 'name' only accepts letters and spaces";
    public static final String FIELD_NAME_PRODUCT_INVALID_MESSAGE = "The field 'name' only accepts letters, spaces and numbers";
    public static final Byte FIELD_NAME_MAX_MESSAGE = 50;
    public static final Byte FIELD_NAME_MIN_MESSAGE = 4;
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";
    public static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$");

    //DESCRIPTION
    public static final String FIELD_DESCRIPTION_INVALID_MESSAGE = "The field 'description' only accepts letters and spaces";
    public static final String FIELD_DESCRIPTION_PRODUCT_INVALID_MESSAGE = "The field 'description' only accepts letters, numbers, spaces and some punctuation marks";
    public static final String CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "Category Already Exists EXCEPTION";
    public static final Byte FIELD_DESCRIPTION_MAX_MESSAGE = 90;
    public static final Byte FIELD_DESCRIPTION_MIN_MESSAGE = 17;
    public static final Pattern DESCRIPTION_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$");

    // Category List --------------------------------

    public static final String CATEGORIES_NOT_FOUND = "One or more categories do not exist.";
    public static final String CATEGORY_LIST_CONTAINS_DUPLICATE_IDS = "The category list contains duplicate IDs.";
    public static final String LIST_CATEGORY_IDS_NOT_EMPTY = "The list of IDs must contain at least one associative id";
    //PAGE
    public static final String FIELD_PAGE_VALIDATE_LENGTH_EXCEPTION_MESSAGE = "The field 'page' cannot be null, less than 0 or more than 50";
    public static final Integer FIELD_PAGE_OUT_RANGE = 40;
    //size
    public static final String FIELD_SIZE_VALIDATE_LENGTH_EXCEPTION_MESSAGE = "The field 'size' cannot be null, less than 1 or more than 20";

    //sortDirection

    public static final String VALUE_INVALID_FOR_SORT_DIRECTION = "Invalid value for sort direction field, only 'asc' or 'desc' allowed";
    public static final String VALUE_ASC = "asc";
    public static final String VALUE_DESC = "desc";
    //Sort field
    public static final String VALUE_INVALID_FOR_SORT_FIELD = "the 'sort' field can only be 'name'";
    public static final String VALUE_NAME_SORT = "name";

    //Message - PaginatedResponse
    public static final String PAGINATION_SUCCESSFUL = "Pagination successful";
    public static final String MESSAGE_NO_CATEGORIES_REGISTERED = "No categories registered in the system were found";
    public static final String MESSAGE_PAGE_OUT_OF_RANGE = "No results were found on the requested page. Please try a page number less than: ";
    public static final String LIST_TO_NOT_BE_EMPTY = "Expected items list to not be empty";
    public static final String LIST_TO_BE_EMPTY = "Expected items list to be empty";

    public static final Long LIST_COUNT_IS_EMPTY_LONG = 0L;
    public static final Long LIST_COUNT_NOT_EMPTY_LONG = 5L;
    public static final Long TOTAL_ITEMS_PAGINATED_LONG = 10L;
    //Test ----- Category
    public static final String INVALID_NAME = "Invalid@Name";
    public static final String INVALID_DESCRIPTION = "Invalid@Description";
    public static final Long DEFAULT_ID = 1L;
    public static final String DEFAULT_NAME = "ValidName";
    public static final String DEFAULT_DESCRIPTION = "Valid description";
    public static final String NAME_BLANK = "   ";
    public static final String DESCRIPTION_BLANK = "   ";

    public static final Integer PAGE_NUMBER_DEFAULT = 0;
    public static final Integer PAGE_SIZE_DEFAULT = 10;
    public static final Long PAGINATED_TOTAL_ITEMS_DEFAULT = 1L;
    public static final Integer PAGINATED_CURRENT_PAGE_DEFAULT = 0;
    public static final Integer PAGINATED_TOTAL_PAGE_DEFAULT = 1;

    // Brand
    public static final String BRAND_ALREADY_EXISTS_EXCEPTION_MESSAGE = "Brand Already Exists EXCEPTION";
    public static final String INVALID_SORT_FIELD = "InvalidSort";
    public static final String INVALID_SORT_DIRECTION = "InvalidSortDirection";
    public static final String BRAND_NOT_FOUND_EXCEPTION_MESSAGE = "Brand not found";
    public static final String BRAND_ID_NOTNULL_MESSAGE = "Identifier of Brand cannot be null";

    //Product

    public static final String PRODUCT_ALREADY_EXISTS_EXCEPTION = "Product Already Exists Exception";
    public static final String LIST_CATEGORY_IDS_IS_EMPTY = "List IDs of Categories cannot be null";
    public static final String VALIDATE_LIST_ID_OF_CATEGORIES = "The list of category IDs cannot be greater than 3";

    //Quantity
    public static final String FIELD_QUANTITY_POSITIVE_MESSAGE = "Quantity field must be greater than 0";
    public static final String FIELD_QUANTITY_NOTNULL_MESSAGE = "Quantity field must not be null";
    public static final Long DEFAULT_QUANTITY = 10L;

    //Price
    public static final String FIELD_PRICE_POSITIVE_MESSAGE = "Price field must be greater than 0";
    public static final String FIELD_PRICE_NOTNULL_MESSAGE = "Price field must not be null";
    public static final BigDecimal DEFAULT_PRICE = BigDecimal.valueOf(100);
    public static final Long DEFAULT_2L = 2L;
    public static final Long DEFAULT_0L = 0L;
    public static final Long DEFAULT_3L = 3L;
    public static final Long DEFAULT_4L = 4L;
}
