package com.stock.adapters.driven.jpa.mysql.adapter.factories;

import com.stock.domain.util.DomainConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PageFactory {

    public static <T> Page<T> createPageWithEntities(List<T> listEntities) {
        Pageable pageable = PageRequest.of(DomainConstants.PAGE_NUMBER_DEFAULT, DomainConstants.PAGE_SIZE_DEFAULT);
        return new PageImpl<>(listEntities, pageable, listEntities.size());
    }
}
