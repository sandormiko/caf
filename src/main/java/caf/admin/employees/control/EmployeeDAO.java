package caf.admin.employees.control;

import javax.inject.Inject;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import caf.admin.employees.entity.Employee;

public class EmployeeDAO extends BasicDAO<Employee, String>{

	@Inject
	public EmployeeDAO(Datastore ds) {
		super(Employee.class, ds);
		
	}
	
	public Employee findById(String id){
		ObjectId oid = new ObjectId(id);
	    return getDatastore().find(Employee.class).field("objectId").equal(oid).get();
	}

}
