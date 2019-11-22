package com.music.record.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.music.record.business.DiscBusiness;
import com.music.record.model.Disc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API Music Records")
@CrossOrigin(origins = "*")
public class DiscController {

	@Autowired
	private DiscBusiness discBusiness;

	@GetMapping(params = { "page", "pageSize", "gender" })
	@ResponseBody
	@ApiOperation(value = "Get Filter", response = Disc.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<List<Disc>> read(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("gender") String gender) {
		return ResponseEntity.ok().body(discBusiness.read(page, pageSize, gender));
	}
}
