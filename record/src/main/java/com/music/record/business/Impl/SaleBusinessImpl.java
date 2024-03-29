package com.music.record.business.Impl;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.el.PropertyNotFoundException;
import javax.persistence.Convert;

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
import com.music.record.repository.SaleRepository;

import lombok.NonNull;
import lombok.var;

@Service
public class SaleBusinessImpl implements SaleBusiness {

	private static final String LocalDate = null;

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private DiscRepository discRepository;

	@Autowired
	private CashbackRepository cashbackRepository;

	@Override
	public Optional<Sale> create(@NonNull Sale sale) {

		String day = ZonedDateTime.now().getDayOfWeek().toString();
		BigDecimal totalPrice = new BigDecimal(0);
		BigDecimal cashBackTotalValue = new BigDecimal(0);
		
		for(final var e : sale.getItens())
		{
			Disc disc = getGenderById(e.getDiscId());

			BigDecimal percentage = getPercentageByGender(disc.getGender().toString(), day);

			e.setTotalPrice(e.getPrice().multiply(new BigDecimal(e.getQuantity())));
			e.setCashBackValue(calculateCashback(e.getTotalPrice(), percentage));
			
			e.setSale(sale);
			
			totalPrice = totalPrice.add(e.getCashBackValue());
			cashBackTotalValue = cashBackTotalValue.add(e.getCashBackValue());
		}
		
		sale.setTotalPrice(totalPrice);
		sale.setCashBackTotalValue(cashBackTotalValue);
		sale.setCreatedDate(new Date());

		saleRepository.saveAndFlush(sale);

		return Optional.of(sale);
	}

	@Override
	public Optional<List<Sale>> read(@NonNull final Integer page, @NonNull final Integer pageSize,
			@NonNull final Date startDate, @NonNull final Date endDate) {

		Pageable pageable = PageRequest.of(page, pageSize);

		return saleRepository.findByCreatedDateBetweenOrderByCreatedDateDesc(startDate, endDate, pageable);
	}


	@Override
	public Optional<Sale> findById(@NonNull Integer id) {
		return Optional.of(saleRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale Not Found")));
	}

	private Disc getGenderById(@NonNull final Integer id) {
		final var disc = discRepository.findById(id);
		return disc.get();
	}

	private BigDecimal getPercentageByGender(@NonNull final String gender, @NonNull final String day) {

		Gender genderEnum = Gender.valueOf(gender.toUpperCase());
		Day dayEnum = Day.valueOf(day.toUpperCase());

		final var chackback = cashbackRepository.findByGenderAndDay(genderEnum, dayEnum);
		return chackback.get().getPercentCashBack();
	}

	private BigDecimal calculateCashback(@NonNull final BigDecimal totalPrice, @NonNull final BigDecimal percentage) {
		return totalPrice.divide(new BigDecimal(100)).multiply(percentage);
	}
}
