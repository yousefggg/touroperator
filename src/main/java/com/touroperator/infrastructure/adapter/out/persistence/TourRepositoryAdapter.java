package com.touroperator.infrastructure.adapter.out.persistence;

import com.touroperator.application.dto.filter.TourFilter;
import com.touroperator.domain.model.Tour;
import com.touroperator.domain.port.out.TourRepository;
import com.touroperator.infrastructure.adapter.out.persistence.entity.TourEntity;
import com.touroperator.infrastructure.adapter.out.persistence.jpa.TourJpaRepository;
import com.touroperator.infrastructure.adapter.out.persistence.mapper.TourMapper;
import com.touroperator.infrastructure.adapter.out.persistence.specification.TourSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        Specification<TourEntity> spec = Specification.where((Specification<TourEntity>) null);

        if (filter.getTourName() != null && !filter.getTourName().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(
                            cb.lower(root.get("tourName")),
                            "%" + filter.getTourName().toLowerCase() + "%"
                    )
            );
        }

        if (filter.getMinPrice() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("tourPrice"), filter.getMinPrice())
            );
        }

        if (filter.getMaxPrice() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("tourPrice"), filter.getMaxPrice())
            );
        }

        Page<TourEntity> result =
                tourJpaRepository.findAll(spec, pageable);

        return result.map(TourMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        tourJpaRepository.deleteById(id);
    }
}
