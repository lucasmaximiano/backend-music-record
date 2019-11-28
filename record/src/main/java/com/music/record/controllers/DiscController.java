package com.music.record.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.music.record.business.DiscBusiness;
import com.music.record.dtos.DiscDto;
import com.music.record.mapper.DiscMapper;
import com.music.record.model.Disc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/disc")
@Api(value = "API Music Records")
@CrossOrigin(origins = "*")
public class DiscController {

	@Autowired
	private DiscBusiness discBusiness;

	@Autowired
	private DiscMapper discMapper;

	@PostMapping("/spotify")
	@ResponseBody
	@ApiOperation(value = "Create All Spotify Discs")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 409, message = "Conflict"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Void> create() {
		discBusiness.createAllSpotifyDiscs();
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@PostMapping
	@ResponseBody
	@ApiOperation(value = "Create Disc", response = Disc.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 409, message = "Conflict"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Optional<Disc>> create(@RequestBody @Valid final DiscDto discRequest) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(discBusiness.create(discMapper.serializeToModel(discRequest)));
	}

	@GetMapping(params = { "page", "pageSize", "gender" })
	@ResponseBody
	@ApiOperation(value = "Get Filter", response = Disc.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Optional<List<Disc>>> read(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("gender") String gender) {
		return ResponseEntity.ok().body(discBusiness.read(page, pageSize, gender));
	}

	@GetMapping("/{id}")
	@ResponseBody
	@ApiOperation(value = "Get Disc By ID", response = Disc.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Optional<Disc>> findById(@PathVariable final Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(discBusiness.findById(id));
	}

	@PutMapping
	@ResponseBody
	@ApiOperation(value = "Update Disc", response = Disc.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Optional<Disc>> update(@RequestBody @Valid final DiscDto discRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(discBusiness.update(discMapper.serializeToModel(discRequest)));
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	@ApiOperation(value = "Delete Payment By ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Void> delete(@PathVariable final Integer id) {
		discBusiness.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
