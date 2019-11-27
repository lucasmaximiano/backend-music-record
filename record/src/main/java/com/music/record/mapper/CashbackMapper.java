package com.music.record.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.music.record.dtos.CashbackDto;
import com.music.record.model.Cashback;

import lombok.NonNull;

@Component
public class CashbackMapper {

	public Cashback serializeToModel(@NonNull final CashbackDto cashbackRequest) {

		return Cashback.builder().id(cashbackRequest.getId()).gender(cashbackRequest.getGender())
				.day(cashbackRequest.getDay()).percentCashBack(cashbackRequest.getPercentCashBack()).build();
	}
}
