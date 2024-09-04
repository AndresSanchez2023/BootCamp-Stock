package com.stock.domain.model;

import java.util.List;

public class Paginated<T> {

    private final List<T> entities;
    private final int currentPage;
    private final Long totalItems;
    private final int totalPages;

    private final String message;

    public Paginated(List<T> entities, int currentPage, Long totalItems, int totalPages, String message) {
        this.entities = entities;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public List<T> getEntities() {
        return entities;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
