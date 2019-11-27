package com.music.record.mapper;

import org.springframework.stereotype.Component;

import com.music.record.dtos.DiscDto;
import com.music.record.model.Disc;

import lombok.NonNull;

@Component
public class DiscMapper {

	public Disc serializeToModel(@NonNull final DiscDto discRequest) {

		return Disc.builder().id(discRequest.getId()).name(discRequest.getName()).gender(discRequest.getGender())
				.price(discRequest.getPrice()).build();
	}
}
