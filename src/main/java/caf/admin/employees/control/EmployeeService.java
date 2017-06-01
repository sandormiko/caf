package caf.admin.employees.control;



import java.util.List;

import javax.inject.Inject;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import caf.admin.employees.entity.Employee;

public class EmployeeService {

	private final Datastore datastore;
	
	@Inject
	public EmployeeService(Datastore datastore){
		this.datastore = datastore;
	}
	
	public Employee saveEmployee(Employee emp){
		datastore.save(emp);
		return emp;
	}
	
	public List<Employee> getEmployees(){
		return datastore.createQuery(Employee.class).asList();
		
	}
}
