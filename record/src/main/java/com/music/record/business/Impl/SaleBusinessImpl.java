package com.music.record.business.Impl;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.el.PropertyNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.music.record.business.SaleBusiness;
import com.music.record.enums.Day;
import com.music.record.enums.Gender;
import com.music.record.model.Disc;
import com.music.record.model.Sale;
import com.music.record.model.SaleItem;
import com.music.record.repository.CashbackRepository;
import com.music.record.repository.DiscRepository;
import com.music.record.repository.SaleItemRepository;
import com.music.record.repository.SaleRepository;

import lombok.NonNull;
import lombok.var;

@Service
public class SaleBusinessImpl implements SaleBusiness {

	private static final String LocalDate = null;

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private SaleItemRepository saleItemRepository;

	@Autowired
	private DiscRepository discRepository;

	@Autowired
	private CashbackRepository cashbackRepository;

	@Override
	public Optional<Sale> create(@NonNull Sale sale) {

		final var calculatedSave = getCalculatedSale(sale);

		calculatedSave.setCreatedDate(new Date());
		saleRepository.save(calculatedSave);

		if (calculatedSave.getItens() != null) {
			for (SaleItem item : calculatedSave.getItens()) {
				saleItemRepository.save(item);
			}
		}

		return Optional.of(calculatedSave);
	}

	@Override
	public Optional<List<Sale>> read(@NonNull final Integer page, @NonNull final Integer pageSize,
			@NonNull final Date startDate, @NonNull final Date endDate) {
		
		Pageable pageable = PageRequest.of(page, pageSize);
		
		return saleRepository.findByCreatedDateBetweenOrderByCreatedDateDesc(startDate, endDate, pageable);
	}

	@Override
	public Optional<Sale> update(@NonNull Sale sale) {

		saleRepository.findById(sale.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale Not Found"));

		final var calculatedSave = getCalculatedSale(sale);

		saleRepository.save(calculatedSave);

		if (calculatedSave.getItens() != null) {
			for (SaleItem item : calculatedSave.getItens()) {

				saleItemRepository.save(item);
			}
		}

		return Optional.of(calculatedSave);
	}

	@Override
	public Optional<Sale> findById(@NonNull Integer id) {
		return Optional.of(saleRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale Not Found")));
	}

	private Sale getCalculatedSale(@NonNull Sale sale) {

		String day = ZonedDateTime.now().getDayOfWeek().toString();
		
		BigDecimal cashBackTotalValue = new BigDecimal(0);
		BigDecimal totalPrice= new BigDecimal(0);

		if (sale.getItens() != null) {
			for (SaleItem item : sale.getItens()) {

				Disc disc = getGenderById(item.getDiscId());

				BigDecimal percentage = getPercentageByGender(disc.getGender().toString(), day);

				item.setCashBackValue(calculateCashback(disc.getPrice(), percentage));

				cashBackTotalValue = cashBackTotalValue.add(item.getCashBackValue());
				
				totalPrice = totalPrice.add(item.getPrice());
			}
			sale.setCashBackTotalValue(cashBackTotalValue);
			sale.setTotalPrice(totalPrice);
		}
		return sale;
	}

	private Disc getGenderById(Integer id) {
		final var disc = discRepository.findById(id);
		return disc.get();
	}

	private BigDecimal getPercentageByGender(String gender, String day) {
		
		Gender genderEnum = Gender.valueOf(gender);
		Day dayEnum = Day.valueOf(day);
		
		final var chackback = cashbackRepository.findByGenderAndDay(genderEnum, dayEnum);
		return chackback.get().getPercentCashBack();
	}

	private BigDecimal calculateCashback(BigDecimal discValor, BigDecimal percentage) {
		return (percentage.multiply(discValor)).divide(new BigDecimal(100));
	}
}
