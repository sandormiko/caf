package caf.admin;

import com.google.inject.AbstractModule;

import caf.admin.employees.control.EmployeeService;
import caf.mongo.MongoModule;

public class CafAdminModule extends AbstractModule {

	private final CafAdminConfiguration config;
	
	public CafAdminModule(CafAdminConfiguration config) {
		this.config = config;

	}
	@Override
	protected void configure() {
		install(new MongoModule(config.getDbName()));
		bind(EmployeeService.class);
		bind(CafAdminConfiguration.class);

	}

	

}
