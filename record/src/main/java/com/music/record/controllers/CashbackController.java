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

import com.music.record.business.CashbackBusiness;
import com.music.record.dtos.CashbackDto;
import com.music.record.mapper.CashbackMapper;
import com.music.record.model.Cashback;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/cashback")
@Api(value = "API Music Records")
@CrossOrigin(origins = "*")
public class CashbackController {

	@Autowired
	private CashbackBusiness cashbackBusiness;
	
	@Autowired
	private CashbackMapper cashbackMapper;

	@PostMapping
	@ResponseBody
	@ApiOperation(value = "Create Cashback", response = Cashback.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 409, message = "Conflict"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Optional<Cashback>> create(@RequestBody @Valid final CashbackDto cashbackResquet) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(cashbackBusiness.create(cashbackMapper.serializeToModel(cashbackResquet)));
	}

	@GetMapping(params = { "page", "pageSize", "gender" })
	@ResponseBody
	@ApiOperation(value = "Get Filter", response = Cashback.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Optional<List<Cashback>>> read(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("gender") String gender) {
		return ResponseEntity.ok().body(cashbackBusiness.read(page, pageSize, gender));
	}

	@GetMapping("/{id}")
	@ResponseBody
	@ApiOperation(value = "Get Cashback By ID", response = Cashback.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Optional<Cashback>> findById(@PathVariable final Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(cashbackBusiness.findById(id));
	}

	@PutMapping
	@ResponseBody
	@ApiOperation(value = "Update Cashback", response = Cashback.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Optional<Cashback>> update(@RequestBody @Valid final CashbackDto cashbackResquet) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(cashbackBusiness.update(cashbackMapper.serializeToModel(cashbackResquet)));
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	@ApiOperation(value = "Delete Cashback By ID", response = Cashback.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Void> delete(@PathVariable final Integer id) {
		cashbackBusiness.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
