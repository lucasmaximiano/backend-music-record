package com.music.record.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.music.record.business.SaleBusiness;
import com.music.record.dtos.SaleDto;
import com.music.record.mapper.SaleMapper;
import com.music.record.model.Sale;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/sale")
@Api(value = "API Music Records")
@CrossOrigin(origins = "*")
public class SaleController {

	@Autowired
	private SaleBusiness saleBusiness;

	@Autowired
	private SaleMapper saleMapper;

	@PostMapping
	@ResponseBody
	@ApiOperation(value = "Create Sale", response = Sale.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 409, message = "Conflict"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Optional<Sale>> create(@RequestBody @Valid final SaleDto saleRequest) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(saleBusiness.create(saleMapper.serializeToModel(saleRequest)));
	}

	@GetMapping(params = { "page", "pageSize", "startDate", "endDate" })
	@ResponseBody
	@ApiOperation(value = "Get Filter", response = Sale.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Optional<List<Sale>>> read(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("startDate") Date startDate,
			@RequestParam("endDate") Date endDate) {
		return ResponseEntity.ok().body(saleBusiness.read(page, pageSize, startDate, endDate));
	}

	@GetMapping("/{id}")
	@ResponseBody
	@ApiOperation(value = "Get Sale By ID", response = Sale.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Optional<Sale>> findById(@PathVariable final Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(saleBusiness.findById(id));
	}
}
