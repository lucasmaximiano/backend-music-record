package com.music.record.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.music.record.model.Sale;

import lombok.NonNull;



public interface SaleRepository extends JpaRepository<Sale, Integer>{
	
	 Optional<List<Sale>> findByCreatedDateBetweenOrderByCreatedDateDesc(@NonNull final Date startDate,
             @NonNull final Date endDate,
             @NonNull final Pageable pageable);
}
