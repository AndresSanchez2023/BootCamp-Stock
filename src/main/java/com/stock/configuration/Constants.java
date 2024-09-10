package com.stock.configuration;


public class Constants {




    private Constants() {
        throw new IllegalArgumentException("Utility Class");
    }

    // OPENAPI / SWAGGER
    public static final String TITLE_OPENAPI = "Emazon Stock Management";
    public static final String VERSION_OPENAPI = "v1";
    public static final String DESCRIPTION_OPENAPI = "Provides specific features for managing categories, brands and products";

    //Category -----
    public static final String CATEGORY = "Category";
    public static final String ADD_CATEGORY = "Allows you to create a new category";
    public static final String CATEGORY_CREATED_SUCCESS = "Successful response, category created";
    public static final String INVALID_REQUEST_ERROR = "Request error";
    public static final String CATEGORY_CONTROLLER_DESCRIPTION = "Creation of product categories and paginated lists";
    public static final String CODE_CREATED = "201";
    public static final String CODE_BAD_REQUEST = "400";
    public static final String MEDIA_TYPE = "application/json";

    //Brand --------
    public static final String BRAND = "BRAND";
    public static final String ADD_BRAND = "Allows you to create a new brand";
    public static final String BRAND_CREATED_SUCCESS = "Successful response, brand created";
    public static final String BRAND_CONTROLLER_DESCRIPTION = "Branding for products, pagination and brand sorting";
    public static final String SCHEMAS_CREATED_BRAND_REQUEST_PARAMETER = "Created Brand request object containing Id, name, description";
    public static final String BRAND_RESPONSE_CLASS_DESCRIPTION = "Response Object, Of brand";

    //---------------------------List

    public static final String CATEGORY_LIST_SUCCESS = "Successful response, category listed";
    public static final String LIST_CATEGORY = "Allows you to list all categories paginated and ordered";
    public static final String CODE_SUCCESS = "200";

    //Schemas ------ PAGINATED-RESPONSE

    public static final String PAGINATED_RESPONSE_DESCRIPTION = "Response Object, Paginated and Sorted List";
    public static final String PAGINATED_RESPONSE_ENTITIES_DESCRIPTION = "List of Entities.";
    public static final String PAGINATED_RESPONSE_ENTITIES_EXAMPLE = "[{\\\"id\\\": 1, \\\"name\\\": \\\"Category1\\\"}, {\\\"id\\\": 2, \\\"name\\\": \\\"Category2\\\"}]\"";
    public static final String PAGINATED_RESPONSE_CURRENT_PAGE_DESCRIPTION = "Current page number in the pagination.";
    public static final String PAGINATED_RESPONSE_CURRENT_PAGE_EXAMPLE = "1";
    public static final String PAGINATED_RESPONSE_TOTAL_ITEMS_DESCRIPTION = "Total number of items across all pages.";
    public static final String PAGINATED_RESPONSE_TOTAL_ITEMS_EXAMPLE = "100";
    public static final String PAGINATED_RESPONSE_TOTAL_PAGES_DESCRIPTION = "Total number of pages available.";
    public static final String PAGINATED_RESPONSE_MESSAGE = "Response message to pagination request";
    public static final String PAGINATED_RESPONSE_TOTAL_PAGES_EXAMPLE = "10";
    public static final String PAGINATION_SUCCESSFUL = "Pagination successful";

    //Schemas ------ CATEGORY-RESPONSE

    public static final String SCHEMAS_CREATED_CATEGORY_REQUEST_PARAMETER = "Created Category request object containing Id, name, description";
    public static final String CATEGORY_RESPONSE_CLASS_DESCRIPTION = "Response Object, Of category";
    public static final String CATEGORY_RESPONSE_ID = "Identifier Of category";
    public static final String CATEGORY_RESPONSE_NAME = "Name Of category";
    public static final String CATEGORY_RESPONSE_DESCRIPTION = "Description Of category";

    public static final String CATEGORY_RESPONSE_ID_EXAMPLE = "1";
    public static final String CATEGORY_RESPONSE_NAME_EXAMPLE = "CLOTHES";
    public static final String CATEGORY_RESPONSE_DESCRIPTION_EXAMPLE = "Everything related to clothing for all types of public";

    public static final String PAGE_STRING_VALUE_CONTROLLER = "page";
    public static final String SIZE_STRING_VALUE_CONTROLLER = "size";
    public static final String SORT_FIELD_STRING_VALUE_CONTROLLER = "sort";
    public static final String SORT_DIRECTION_STRING_VALUE_CONTROLLER = "sortDirection";
    public static final String PAGE_DEFAULT_VALUE_CONTROLLER = "0";
    public static final String SIZE_DEFAULT_VALUE_CONTROLLER = "10";
    public static final String SORT_FIELD_DEFAULT_VALUE_CONTROLLER = "name";
    public static final String SORT_DIRECTION_DEFAULT_VALUE_CONTROLLER = "asc";

    //Brand ----------

    public static final String LIST_BRAND = "Allows you to list all brands paginated and ordered";
    public static final String BRAND_LIST_SUCCESS = "Successful response, brands listed";
    public static final String BRAND_RESPONSE_ID = "Identifier Of brand";
    public static final String BRAND_RESPONSE_NAME = "Name Of brand";
    public static final String BRAND_RESPONSE_DESCRIPTION = "Description Of brand";

    public static final String BRAND_ENDPOINT = "/brand/";

    public static final String BRAND_RESPONSE_ID_EXAMPLE = "1";
    public static final String BRAND_RESPONSE_NAME_EXAMPLE = "Lenovo";
    public static final String BRAND_RESPONSE_DESCRIPTION_EXAMPLE = "We offer a full range of PCs and tablets, monitors, accessories, smartphones, smart home and smart collaboration solutions, virtual and augmented reality";

    //Product

    public static final String PRODUCT = "PRODUCT";
    public static final String ADD_PRODUCT = "Allows you to create a new product";
    public static final String PRODUCT_CREATED_SUCCESS = "Successful response, product created";
    public static final String PRODUCT_CONTROLLER_DESCRIPTION = "Product management including creation, and listing of products with pagination and sorting options";
    public static final String SCHEMAS_CREATED_PRODUCT_REQUEST_PARAMETER = "Created Product request object containing name, description, quantity, price, brand ID, and category IDs";
}
