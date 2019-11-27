package com.music.record.business;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.music.record.model.Sale;

import lombok.NonNull;

public interface SaleBusiness {

	Optional<Sale> create(@NonNull final Sale sale);

	Optional<List<Sale>> read(@NonNull final Integer page, @NonNull final Integer pageSize,
			@NonNull final Date startDate, @NonNull final Date endDate);

	Optional<Sale> update(@NonNull final Sale Sale);

	Optional<Sale> findById(@NonNull final Integer id);
}
