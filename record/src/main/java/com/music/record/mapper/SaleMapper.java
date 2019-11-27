package com.music.record.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.music.record.dtos.SaleDto;
import com.music.record.dtos.SaleItemDto;
import com.music.record.model.Sale;
import com.music.record.model.SaleItem;

import lombok.NonNull;
import lombok.var;

@Component
public class SaleMapper {

	public Sale serializeToModel(@NonNull final SaleDto saleRequest) {

		return Sale.builder().id(saleRequest.getId()).customerName(saleRequest.getCustomerName())
				.customerEmail(saleRequest.getCustomerEmail()).itens(serializeItemToModel(saleRequest.getItens()))
				.build();
	}

	private List<SaleItem> serializeItemToModel(@NonNull final List<SaleItemDto> saleItemRequest) {
		final var list = new ArrayList<SaleItem>();

		for (SaleItemDto item : saleItemRequest) {

			list.add(SaleItem.builder().id(item.getId()).discId(item.getDiscId()).name(item.getName())
					.quantity(item.getQuantity()).price(item.getPrice()).build());
		}

		return list;
	}

}
