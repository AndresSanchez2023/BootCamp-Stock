package com.stock.domain.util;

import java.util.regex.Pattern;

public final class DomainConstants {
    private DomainConstants(){
        throw new IllegalArgumentException("Utility Class");
    }

    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";

    public static final String FIELD_NAME_VALIDATE_LENGTH_EXCEPTION_MESSAGE = "The field 'name' cannot be less than 4 characters or more than 50 characters";
    public static final String FIELD_DESCRIPTION_VALIDATE_LENGTH_EXCEPTION_MESSAGE = "The field 'description' cannot be less than 4 characters or more than exceed 90 characters";


    public static final String FIELD_NAME_INVALID_MESSAGE = "The field 'name' only accepts letters and spaces";
    public static final String FIELD_DESCRIPTION_INVALID_MESSAGE = "The field 'description' only accepts letters and spaces";

    public static final String CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "Category Already Exists EXCEPTION";
    public static final Byte FIELD_NAME_MAX_MESSAGE = 50;
    public static final Byte FIELD_NAME_MIN_MESSAGE = 4;
    public static final Byte FIELD_DESCRIPTION_MAX_MESSAGE = 90;
    public static final Byte FIELD_DESCRIPTION_MIN_MESSAGE = 17;

    public static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$");
    public static final Pattern DESCRIPTION_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$");


    //Test

    public static final Long DEFAULT_ID = 1L;
    public static final String DEFAULT_NAME = "Valid Name";
    public static final String DEFAULT_DESCRIPTION = "Valid description";

    public static final String INVALID_NAME = "Invalid@Name";

    public static final String INVALID_DESCRIPTION = "Invalid@Description";
}
