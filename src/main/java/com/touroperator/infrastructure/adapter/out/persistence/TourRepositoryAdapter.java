package com.touroperator.infrastructure.adapter.out.persistence;

import com.touroperator.application.dto.filter.TourFilter;
import com.touroperator.domain.model.Tour;
import com.touroperator.domain.port.out.TourRepository;
import com.touroperator.infrastructure.adapter.out.persistence.entity.TourEntity;
import com.touroperator.infrastructure.adapter.out.persistence.jpa.TourJpaRepository;
import com.touroperator.infrastructure.adapter.out.persistence.mapper.TourMapper;
import com.touroperator.infrastructure.adapter.out.persistence.specification.TourSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TourRepositoryAdapter implements TourRepository {

    private final TourJpaRepository tourJpaRepository;

    public TourRepositoryAdapter(TourJpaRepository tourJpaRepository) {
        this.tourJpaRepository = tourJpaRepository;
    }

    @Override
    public Tour save(Tour tour) {
        TourEntity entity = TourMapper.toEntityForCreate(tour);
        entity = tourJpaRepository.save(entity);
        return TourMapper.toDomain(entity);
    }

    @Override
    public Optional<Tour> findById(Long id) {
        return tourJpaRepository.findById(id)
                .map(TourMapper::toDomain);
    }

    @Override
    public Page<Tour> findAll(TourFilter filter, Pageable pageable) {

        Specification<TourEntity> spec = Specification.where(TourSpecification.tourNameContains(filter.getTourName()))
                .and(TourSpecification.priceGreaterOrEqual(filter.getMinPrice()))
                .and(TourSpecification.priceLessOrEqual(filter.getMaxPrice()));

        Pageable finalPageable = pageable;
        if (pageable.getSort().isUnsorted() || pageable.getSort().toString().contains("[]")) {
            finalPageable = PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.by("id").ascending()
            );
        }

        Page<TourEntity> result = tourJpaRepository.findAll(spec, finalPageable);

        return result.map(TourMapper::toDomain);
    }



    @Override
    public void deleteById(Long id) {
        tourJpaRepository.deleteById(id);
    }
}
