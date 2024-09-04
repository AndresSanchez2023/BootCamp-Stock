package com.stock.adapters.driven.jpa.mysql.adapter;

import com.stock.configuration.Constants;
import com.stock.domain.model.Paginated;
import com.stock.domain.model.Pagination;
import com.stock.domain.spi.IPaginationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class JpaPaginationAdapter<T, E> implements IPaginationPort<T> {

    private final JpaRepository<E, Long> repository;
    private final Function<E, T> entityToModelMapper;

    @Override
    public Paginated<T> paginate(Pagination pagination) {
        Sort sort = Sort.by(Sort.Direction.fromString(pagination.getSortDirection()), pagination.getSort().toLowerCase());
        Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getSize(), sort);
        Page<E> page = repository.findAll(pageable);
        List<T> models = page.getContent().stream()
                .map(entityToModelMapper).toList();

        return new Paginated<>(models, page.getNumber(), page.getTotalElements(), page.getTotalPages(), Constants.PAGINATION_SUCCESSFUL);
    }
}
