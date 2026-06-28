package com.touroperator.application.mapper;

import com.touroperator.application.dto.response.PageResponse;
import org.springframework.data.domain.Page;

public class PageMapper {

    private PageMapper() {
    }

    public static <T> PageResponse<T> toResponse(Page<T> page) {
        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}