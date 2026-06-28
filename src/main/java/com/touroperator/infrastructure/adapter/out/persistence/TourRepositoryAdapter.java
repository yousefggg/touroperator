package com.touroperator.infrastructure.adapter.out.persistence;

import com.touroperator.domain.model.Tour;
import com.touroperator.domain.port.out.TourRepository;
import com.touroperator.infrastructure.adapter.out.persistence.entity.TourEntity;
import com.touroperator.infrastructure.adapter.out.persistence.jpa.TourJpaRepository;
import com.touroperator.infrastructure.adapter.out.persistence.mapper.TourMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TourRepositoryAdapter implements TourRepository{
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
        Optional<TourEntity> entityList = tourJpaRepository.findById(id);
        return entityList.map(TourMapper::toDomain);
    }

    @Override
    public Page<Tour> findAll(Pageable pageable) {

        Page<TourEntity> entityPage =
                tourJpaRepository.findAll(pageable);

        return entityPage.map(TourMapper::toDomain);
    }
    @Override
    public void deleteById(Long id) {
        tourJpaRepository.deleteById(id);
    }
}
