package caf.admin.employees.control;



import java.util.List;

import javax.inject.Inject;

import caf.admin.employees.entity.Employee;

public class EmployeeService {

	private final EmployeeDAO employeeDAO;
	
	
	@Inject
	public EmployeeService(EmployeeDAO employeeDAO){
		this.employeeDAO = employeeDAO;
	}
	
	public Employee saveEmployee(Employee emp){
		employeeDAO.save(emp);
		return emp;
	}
	
	public List<Employee> getEmployees(){
		return employeeDAO.find().asList();
		
	}
	
	public Employee findById(String objectId){
		return employeeDAO.findById(objectId);
		
	}
}
