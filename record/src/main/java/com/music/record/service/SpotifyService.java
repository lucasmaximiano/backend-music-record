package com.music.record.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.*;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchAlbumsRequest;

import lombok.NonNull;

@Component
public class SpotifyService {
	
	 @Value("${spotify.client-id}")
	  private String clientId;

	  @Value("${spotify.client-secret}")
	  private String clientSecret;

	  private static SpotifyApi spotifyApi;

	  public void spotifyAuthenticate() throws SpotifyWebApiException, IOException{

	      spotifyApi = new SpotifyApi.Builder()
	          .setClientId(this.clientId)
	          .setClientSecret(this.clientSecret)
	          .build();

	      final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
	          .build();

	      final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

	      spotifyApi.setAccessToken(clientCredentials.getAccessToken());

	  }

	  private Optional<List<AlbumSimplified>> getAlbuns(@NonNull final String genrer) throws SpotifyWebApiException, IOException {

	    final SearchAlbumsRequest getAlbumRequest = spotifyApi.searchAlbums(genrer)
	        .market(CountryCode.BR)
	        .limit(50)
	        .offset(50)
	        .build();

	    final Paging<AlbumSimplified> album = getAlbumRequest.execute();

	    return Optional.of(Arrays.asList(album.getItems()));

	  }

	  public Optional<List<AlbumSimplified>> getRockAlbuns() throws IOException, SpotifyWebApiException {
	    return this.getAlbuns("rock");
	  }

	  public Optional<List<AlbumSimplified>> getPopAlbuns() throws IOException, SpotifyWebApiException {
	    return this.getAlbuns("pop");
	  }

	  public Optional<List<AlbumSimplified>> getMpbAlbuns() throws IOException, SpotifyWebApiException {
	    return this.getAlbuns("mpb");
	  }

	  public Optional<List<AlbumSimplified>> getClassicAlbuns() throws IOException, SpotifyWebApiException {
	    return this.getAlbuns("classic");
	  }

}
