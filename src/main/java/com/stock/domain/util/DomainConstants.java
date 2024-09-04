package com.stock.domain.util;

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
    public static final Byte FIELD_NAME_MAX_MESSAGE = 50;
    public static final Byte FIELD_NAME_MIN_MESSAGE = 4;
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";
    public static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$");

    //DESCRIPTION
    public static final String FIELD_DESCRIPTION_INVALID_MESSAGE = "The field 'description' only accepts letters and spaces";
    public static final String CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "Category Already Exists EXCEPTION";
    public static final Byte FIELD_DESCRIPTION_MAX_MESSAGE = 90;
    public static final Byte FIELD_DESCRIPTION_MIN_MESSAGE = 17;
    public static final Pattern DESCRIPTION_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$");

    // Category List --------------------------------

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
    //Test ----- Category
    public static final String INVALID_NAME = "Invalid@Name";
    public static final String INVALID_DESCRIPTION = "Invalid@Description";
    public static final Long DEFAULT_ID = 1L;
    public static final String DEFAULT_NAME = "ValidName";
    public static final String DEFAULT_DESCRIPTION = "Valid description";

    public static final Integer PAGE_NUMBER_DEFAULT = 0;
    public static final Integer PAGE_SIZE_DEFAULT = 10;

    public static final Long PAGINATED_TOTAL_ITEMS_DEFAULT = 1L;
    public static final Integer PAGINATED_CURRENT_PAGE_DEFAULT = 0;
    public static final Integer PAGINATED_TOTAL_PAGE_DEFAULT = 1;

    //Brand

    public static final String BRAND_ALREADY_EXISTS_EXCEPTION_MESSAGE = "Brand Already Exists EXCEPTION";
    public static final String BRAND = "BRAND";
    public static final String ADD_BRAND = "Allows you to create a new brand";
    public static final String BRAND_CREATED_SUCCESS = "Successful response, brand created";
    public static final String INVALID_REQUEST_ERROR = "Request error";
    public static final String BRAND_CONTROLLER_DESCRIPTION = "Branding for products, pagination and brand sorting";
    public static final String CODE_CREATED = "201";
    public static final String CODE_BAD_REQUEST = "400";
    public static final String SCHEMAS_CREATED_BRAND_REQUEST_PARAMETER = "Created Brand request object containing Id, name, description";
    public static final String MEDIA_TYPE = "application/json";
}
