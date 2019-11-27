package com.music.record.business;

import java.util.List;
import java.util.Optional;

import com.music.record.model.Disc;

import lombok.NonNull;

public interface DiscBusiness {

	Optional<Disc> create(@NonNull final Disc disc);

	Optional<List<Disc>> read(@NonNull final Integer page, @NonNull final Integer pageSize,
			@NonNull final String gender);

	Optional<Disc> update(@NonNull final Disc disc);

	void delete(@NonNull final Integer id);

	Optional<Disc> findById(@NonNull final Integer id);
}
