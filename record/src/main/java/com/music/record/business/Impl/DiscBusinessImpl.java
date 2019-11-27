package com.music.record.business.Impl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.music.record.business.DiscBusiness;
import com.music.record.enums.Gender;
import com.music.record.model.Disc;
import com.music.record.repository.CashbackRepository;
import com.music.record.repository.DiscRepository;

import lombok.NonNull;

@Service
public class DiscBusinessImpl implements DiscBusiness {

	@Autowired
	private DiscRepository discRepository;

	@Override
	public Optional<Disc> create(@NonNull Disc disc) {
		return Optional.of(discRepository.save(disc));
	}

	@Override
	public Optional<List<Disc>> read(@NonNull Integer page, @NonNull Integer pageSize, @NonNull String gender) {
		Pageable pageable = PageRequest.of(page, pageSize);
		
		Gender genderEnum = Gender.valueOf(gender);
		
		return discRepository.findByGender(genderEnum, pageable);
	}

	@Override
	public Optional<Disc> findById(@NonNull Integer id) {
		return Optional.of(discRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Disc Not Found")));
	}

	@Override
	public Optional<Disc> update(@NonNull Disc disc) {
		discRepository.findById(disc.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Disc Not Found"));

		return Optional.of(discRepository.saveAndFlush(disc));
	}

	@Override
	public void delete(@NonNull Integer id) {
		discRepository.deleteById(id);
	}
}
