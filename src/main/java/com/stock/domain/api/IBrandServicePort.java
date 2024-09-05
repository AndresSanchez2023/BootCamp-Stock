package com.stock.domain.api;

import com.stock.domain.model.Brand;
import com.stock.domain.model.Paginated;
import com.stock.domain.model.Pagination;

public interface IBrandServicePort {

    void saveBrand (Brand brand);

    Paginated<Brand> paginate(Pagination pagination);
    Long getTotalItems();
}
