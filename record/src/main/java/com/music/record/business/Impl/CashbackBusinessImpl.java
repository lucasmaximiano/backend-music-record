package com.music.record.business.Impl;

import java.util.List;
import java.util.Optional;

import javax.el.PropertyNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.music.record.business.CashbackBusiness;
import com.music.record.enums.Gender;
import com.music.record.model.Cashback;
import com.music.record.repository.CashbackRepository;

import lombok.NonNull;

@Service
public class CashbackBusinessImpl implements CashbackBusiness {

	@Autowired
	private CashbackRepository cashbackRepository;

	@Override
	public Optional<Cashback> create(@NonNull Cashback cashback) {
		return Optional.of(cashbackRepository.save(cashback));
	}

	@Override
	public Optional<List<Cashback>> read(@NonNull Integer page, @NonNull Integer pageSize, @NonNull String gender) {
		
		Pageable pageable = PageRequest.of(page, pageSize);
		
		Gender genderEnum = Gender.valueOf(gender);
		
		return cashbackRepository.findByGender(genderEnum, pageable);
	}

	@Override
	public Optional<Cashback> update(@NonNull Cashback cashback) {

		cashbackRepository.findById(cashback.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cashback Not Found"));

		return Optional.of(cashbackRepository.saveAndFlush(cashback));
	}

	@Override
	public Optional<Cashback> findById(@NonNull Integer id) {
		return Optional.of(cashbackRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cashback Not Found")));
	}

	@Override
	public void delete(@NonNull Integer id) {
		cashbackRepository.deleteById(id);
	}
}
