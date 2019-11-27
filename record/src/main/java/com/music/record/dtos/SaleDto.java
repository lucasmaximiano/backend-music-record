package com.music.record.dtos;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SaleDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Valid
	private Integer id;

	@Valid
	@NotNull
	private String customerName;

	@Valid
	@NotNull
	private String customerEmail;

	@Valid
	@NotNull
	private List<SaleItemDto> itens;
}
