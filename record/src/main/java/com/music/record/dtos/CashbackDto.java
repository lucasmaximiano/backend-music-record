package com.music.record.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.music.record.enums.Day;
import com.music.record.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CashbackDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Valid
	private Integer id;

	@Valid
	@NotNull
	private Gender gender;

	@Valid
	@NotNull
	private Day day;

	@Valid
	@NotNull
	private BigDecimal percentCashBack;

}