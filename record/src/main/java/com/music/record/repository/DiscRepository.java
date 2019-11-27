package com.music.record.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.music.record.enums.Gender;
import com.music.record.model.Disc;

import lombok.NonNull;


public interface DiscRepository extends JpaRepository<Disc, Integer>{
	Optional<List<Disc>> findByGender(@NonNull final Gender gender, final Pageable pageable);
}
