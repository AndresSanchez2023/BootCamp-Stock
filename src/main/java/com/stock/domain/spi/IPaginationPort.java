package com.stock.domain.spi;

import com.stock.domain.model.Paginated;
import com.stock.domain.model.Pagination;


public interface IPaginationPort<T> {
    Paginated<T> paginate(Pagination pagination);
}
