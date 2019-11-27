package com.music.record.business;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.music.record.model.Cashback;

import lombok.NonNull;

public interface CashbackBusiness {

	Optional<Cashback> create(@NonNull final Cashback cashback);

	
	Optional<List<Cashback>> read(@NonNull Integer page, @NonNull Integer pageSize, @NonNull String gender);

	Optional<Cashback> update(@NonNull final Cashback cashback);

	void delete(@NonNull final Integer id);

	Optional<Cashback> findById(@NonNull final Integer id);
}
