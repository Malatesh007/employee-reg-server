package com.example.register.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.register.dto.EmployeeDto;
import com.example.register.service.EmployeeRegisterService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class EmployeeRegisterController {

	@Autowired
	EmployeeRegisterService employeeRegisterService;

	@GetMapping("/get-employee-info")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved employee list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public List<EmployeeDto> getEmployeeRegisterDetails() {
		return employeeRegisterService.getRegisterList();
	}

	@PostMapping(value = "/save-employee-details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully registered employee details"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public EmployeeDto saveEmployeeDeatils(@RequestBody @Valid EmployeeDto employeeDto) {
		return employeeRegisterService.registerEmployeeDetails(employeeDto);
	}

	@PutMapping(value = "/update-employee-details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated employee details"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public EmployeeDto updateEmployeeDeatils(@RequestBody @Valid EmployeeDto employeeDto) {
		return employeeRegisterService.updateEmployeeDetails(employeeDto);
	}

	@DeleteMapping("/delete-employee-detail")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted employee details"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<String> deleteEmployeeDetail(@RequestParam("employee_id") String employeeDetailId) {
		String message = employeeRegisterService.deleteEmployeeDetails(employeeDetailId);
		if (message != null) {
			return new ResponseEntity<>(employeeRegisterService.deleteEmployeeDetails(employeeDetailId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("enployee cannot be deleted", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
