package com.music.record.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SaleItemDto  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Valid
	private Integer id;

	@Valid
	@NotNull
	private Integer discId;

	@Valid
	@NotNull
	private String name;

	@Valid
	@NotNull
	private Integer quantity;

	@Valid
	@NotNull
	private BigDecimal price;

}
