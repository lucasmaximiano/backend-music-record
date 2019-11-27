package com.music.record.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.music.record.enums.Gender;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DiscDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Valid
	private Integer id;

	@Valid
	@NotNull
	private String name;
	
	@Valid
	@NotNull
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Valid
	@NotNull
	private BigDecimal price;
}
