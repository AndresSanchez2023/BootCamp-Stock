package com.stock.domain.model;

import com.stock.domain.exception.EmptyFieldException;
import com.stock.domain.exception.InvalidArgumentsInFieldException;
import com.stock.domain.exception.ValidateSizeFieldException;
import com.stock.domain.util.DomainConstants;

import java.util.regex.Matcher;

import static java.util.Objects.requireNonNull;

public class Category {

    private final Long id;
    private final String name;
    private final String description;

    public Category(Long id, String name, String description) {
        validateName(name);
        validateDescription(description);
        this.id = id;
        this.name = requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        this.description = requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
    }

    private void validateName (String name){
        if(name.trim().isEmpty()){
            throw new EmptyFieldException(DomainConstants.FIELD_NAME_NULL_MESSAGE);
        }
        if(name.length() > DomainConstants.FIELD_NAME_MAX_MESSAGE || name.length() < DomainConstants.FIELD_NAME_MIN_MESSAGE){
            throw new ValidateSizeFieldException(DomainConstants.FIELD_NAME_VALIDATE_LENGTH_EXCEPTION_MESSAGE);
        }
        Matcher matcher = DomainConstants.NAME_PATTERN.matcher(name);
        if(!matcher.matches()){
            throw new InvalidArgumentsInFieldException(DomainConstants.FIELD_NAME_INVALID_MESSAGE);
        }
    }

    private void validateDescription(String description){
        if(description.trim().isEmpty()){
            throw new EmptyFieldException(DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
        }
        if(description.length() > DomainConstants.FIELD_DESCRIPTION_MAX_MESSAGE || description.length() < DomainConstants.FIELD_DESCRIPTION_MIN_MESSAGE){
            throw new ValidateSizeFieldException(DomainConstants.FIELD_DESCRIPTION_VALIDATE_LENGTH_EXCEPTION_MESSAGE);
        }
        Matcher matcher = DomainConstants.DESCRIPTION_PATTERN.matcher(description);
        if(!matcher.matches()){
            throw new InvalidArgumentsInFieldException(DomainConstants.FIELD_DESCRIPTION_INVALID_MESSAGE);
        }
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
