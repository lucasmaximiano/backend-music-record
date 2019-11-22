package com.music.record.model;

import java.io.Serializable;
import java.math.BigDecimal;


import com.music.record.enums.Gender;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class Disc implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private Gender gender;

	private BigDecimal price;
}
