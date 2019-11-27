package com.music.record.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.music.record.enums.Day;
import com.music.record.enums.Gender;
import com.music.record.model.Cashback;

import lombok.NonNull;

public interface CashbackRepository extends JpaRepository<Cashback, Integer> {

	Optional<List<Cashback>> findByGender(@NonNull final Gender gender, final Pageable pageable);

	Optional<Cashback> findByGenderAndDay(@NonNull final Gender gender, @NonNull final Day day);
}
