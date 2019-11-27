package com.example.register.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.register.domain.Employee;
import com.example.register.dto.EmployeeDto;
import com.example.register.exception.EmployeeRegisterException;
import com.example.register.repository.EmployeeRegisterRepository;

@Service
public class EmployeeRegisterServiceImpl implements EmployeeRegisterService {

	private static final String EMPLOYEE_WITH_GIVEN_REGISTER_ID_NOT_FOUND = "employee with given register id not found";

	private static final Logger log = LoggerFactory.getLogger(EmployeeRegisterServiceImpl.class);

	@Autowired
	EmployeeRegisterRepository employeeRegisterRepository;

	@Autowired
	ModelMapper modelMapper;

	/**
	 * @author Malatesh.R.A.
	 * @since November 24th 2019. this method is to get Employee registered details.
	 *        this method fetch the information from collection "employee-details".
	 *        if no details founds throws EmployeeRegisterException.
	 */

	@Override
	public List<EmployeeDto> getRegisterList() {
		try {
			log.info("Inside method getRegisterList");
			List<Employee> listOfEmployees = employeeRegisterRepository.findAll();
			Assert.notEmpty(listOfEmployees, "registered employees details not found");
			List<EmployeeDto> employeeDtos = listOfEmployees.stream()
					.map(eachEmployee -> modelMapper.map(eachEmployee, EmployeeDto.class)).collect(Collectors.toList());
			Assert.notEmpty(employeeDtos, "registered employees details dto not found");
			log.info("Outside method getRegisterList:{}", employeeDtos);
			log.info("Outside method getRegisterList");
			return employeeDtos.stream().filter(empDto -> Objects.nonNull(empDto.getFirstName()))
					.sorted((a, b) -> a.getFirstName().compareTo(b.getFirstName())).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			throw new EmployeeRegisterException(e.getMessage());
		}
	}

	/**
	 * @author Malatesh.R.A
	 * @since November 24th 2019. this method is used to save employee register
	 *        details in collection "employee-details".
	 * @return EmployeeDto
	 */

	@Override
	public EmployeeDto registerEmployeeDetails(EmployeeDto employeeDetail) {
		try {
			log.info("Inside method registerEmployeeDetails:{}", employeeDetail);
			Assert.notNull(employeeDetail, "employee dto details cannot be null");
			Employee employee = modelMapper.map(employeeDetail, Employee.class);
			Assert.notNull(employee, "employee details cannot be null");
			employee = employeeRegisterRepository.save(employee);
			Assert.notNull(employee, "saved emploeyee details cannot be null");
			employeeDetail = modelMapper.map(employee, EmployeeDto.class);
			log.info("Outside method registerEmployeeDetails");
			return employeeDetail;
		} catch (Exception e) {
			throw new EmployeeRegisterException(e.getMessage());
		}
	}

	/**
	 * @author Malatesh.R.A
	 * @since November 24th 2019. this method is used to update employee register
	 *        details in collection "employee-details".
	 * @return EmployeeDto
	 */

	@Override
	public EmployeeDto updateEmployeeDetails(EmployeeDto employeeDetail) {
		try {
			log.info("Inside method updateEmployeeDetails:{}", employeeDetail);
			Assert.notNull(employeeDetail, "employee dto details cannot be null");
			Assert.hasText(employeeDetail.getId(), "employee registered is not found");
			Optional<Employee> employee = employeeRegisterRepository.findById(employeeDetail.getId());
			log.info("saved data:{}", employee);
			if (employee.isPresent()) {
				BeanUtils.copyProperties(employeeDetail, employee.get(), "id");
			} else {
				throw new EmployeeRegisterException(EMPLOYEE_WITH_GIVEN_REGISTER_ID_NOT_FOUND);
			}

			Employee updatedemployee = employeeRegisterRepository.save(employee.get());
			log.info("outside method updateEmployeeDetails");
			return modelMapper.map(updatedemployee, EmployeeDto.class);
		} catch (Exception e) {
			throw new EmployeeRegisterException(e.getMessage());
		}
	}

	/**
	 * @author Malatesh.R.A
	 * @since November 24th 2019. this method is used to delete employee register
	 *        details in collection "employee-details".
	 * @return String with success message.
	 */

	@Override
	public String deleteEmployeeDetails(String employeeId) {
		try {
			log.info("inside method deleteEmployeeDetails:{}", employeeId);
			Assert.hasText(employeeId, "employeeId is not found");
			Optional<Employee> employee = employeeRegisterRepository.findById(employeeId);
			if (!employee.isPresent()) {
				throw new EmployeeRegisterException(EMPLOYEE_WITH_GIVEN_REGISTER_ID_NOT_FOUND);
			}
			employeeRegisterRepository.deleteById(employeeId);
			log.info("outside method deleteEmployeeDetails");
			return "employee register deleted successfully";
		} catch (Exception e) {
			throw new EmployeeRegisterException(e.getMessage());
		}
	}

	@Override
	public EmployeeDto getPerticularEmployeeDetail(String employeeId) {
		try {
			log.info("inside method getPerticularEmployeeDetail");
			Assert.hasText(employeeId, "employeeId is not found");
			Optional<Employee> employee = employeeRegisterRepository.findById(employeeId);
			if (!employee.isPresent()) {
				throw new EmployeeRegisterException(EMPLOYEE_WITH_GIVEN_REGISTER_ID_NOT_FOUND);
			}
			log.info("outside method getPerticularEmployeeDetail");
			return modelMapper.map(employee.get(), EmployeeDto.class);
		} catch (Exception e) {
			throw new EmployeeRegisterException(e.getMessage());
		}
	}

}
