package com.example.register.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.register.dto.EmployeeDto;

public interface EmployeeRegisterService {

	List<EmployeeDto> getRegisterList();

	EmployeeDto registerEmployeeDetails(EmployeeDto employeeDetail);

	EmployeeDto updateEmployeeDetails(EmployeeDto employeeDetail);

	String deleteEmployeeDetails(String employeeId);

	EmployeeDto getPerticularEmployeeDetail(String employeeId);
}
