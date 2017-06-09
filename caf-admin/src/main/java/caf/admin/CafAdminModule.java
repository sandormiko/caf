package caf.admin;

import com.google.inject.AbstractModule;

import caf.admin.employees.control.EmployeeService;
import caf.foundation.config.ApplicationConfiguration;
import caf.foundation.mongo.MongoModule;

public class CafAdminModule extends AbstractModule {

	private final ApplicationConfiguration config;

	public CafAdminModule(ApplicationConfiguration config) {
		this.config = config;

	}

	@Override
	protected void configure() {
		bind(ApplicationConfiguration.class).toInstance(config);
		install(new MongoModule());
		bind(EmployeeService.class);

	}

}
