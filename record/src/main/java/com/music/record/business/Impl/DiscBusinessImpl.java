package com.music.record.business.Impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.javafaker.Faker;
import com.music.record.business.DiscBusiness;
import com.music.record.enums.Gender;
import com.music.record.model.Disc;
import com.music.record.repository.DiscRepository;
import com.music.record.service.SpotifyService;
import com.ramon.pereira.albumstore.exception.SpotifyAuthenticateException;

import lombok.NonNull;
import lombok.var;

@Service
public class DiscBusinessImpl implements DiscBusiness {

	private static final Faker faker = new Faker();

	@Autowired
	private DiscRepository discRepository;

	@Autowired
	private SpotifyService spotifyService;

	@Override
	public Optional<Disc> create(@NonNull Disc disc) {
		return Optional.of(discRepository.save(disc));
	}

	@Override
	public Optional<List<Disc>> read(@NonNull Integer page, @NonNull Integer pageSize, @NonNull String gender) {
		Pageable pageable = PageRequest.of(page, pageSize);

		Gender genderEnum = Gender.valueOf(gender);

		return discRepository.findByGender(genderEnum, pageable);
	}

	@Override
	public Optional<Disc> findById(@NonNull Integer id) {
		return Optional.of(discRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Disc Not Found")));
	}

	@Override
	public Optional<Disc> update(@NonNull Disc disc) {
		discRepository.findById(disc.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Disc Not Found"));

		return Optional.of(discRepository.saveAndFlush(disc));
	}

	@Override
	public void delete(@NonNull Integer id) {
		discRepository.deleteById(id);
	}

	public void createAllSpotifyDiscs() {
		try {
			
			authenticateSpotifyService();
			createDiscsMpb();
			createDiscsClassic();
			createDiscsPop();
			createDiscsRock();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	private void authenticateSpotifyService() {
        try {

            this.spotifyService.spotifyAuthenticate();

        } catch (Exception e) {

			e.printStackTrace();
		}
	}


	private void createDiscsRock() throws Exception {
		try {
			final var albuns = spotifyService.getRockAlbuns();

			albuns.ifPresent(albumSimplifieds -> albumSimplifieds.forEach(item -> {
				discRepository.saveAndFlush(Disc.builder().gender(Gender.ROCK).name(item.getName())
						.price(BigDecimal.valueOf(faker.number().numberBetween(1, 1000))).build());
			}));

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void createDiscsMpb() throws Exception {
		try {
			var albuns = spotifyService.getMpbAlbuns();

			albuns.ifPresent(albumSimplifieds -> albumSimplifieds.forEach(item -> {
				discRepository.saveAndFlush(Disc.builder().gender(Gender.MPB).name(item.getName())
						.price(BigDecimal.valueOf(faker.number().numberBetween(1, 1000))).build());
			}));

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	private void createDiscsClassic() throws Exception {
		try {
			var albuns = spotifyService.getClassicAlbuns();

			albuns.ifPresent(albumSimplifieds -> albumSimplifieds.forEach(item -> {
				discRepository.saveAndFlush(Disc.builder().gender(Gender.CLASSIC).name(item.getName())
						.price(BigDecimal.valueOf(faker.number().numberBetween(1, 1000))).build());
			}));

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	private void createDiscsPop() throws Exception {
		try {
			var albuns = spotifyService.getPopAlbuns();

			albuns.ifPresent(albumSimplifieds -> albumSimplifieds.forEach(item -> {
				discRepository.saveAndFlush(Disc.builder().gender(Gender.POP).name(item.getName())
						.price(BigDecimal.valueOf(faker.number().numberBetween(1, 1000))).build());
			}));

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}
