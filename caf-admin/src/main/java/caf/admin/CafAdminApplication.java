package caf.admin;

import com.google.inject.Guice;
import com.google.inject.Injector;

import caf.admin.CafAdminConfiguration;
import caf.admin.employees.boundary.EmployeeResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CafAdminApplication extends Application<CafAdminConfiguration> {

	public static void main(final String[] args) throws Exception {
		new CafAdminApplication().run(args);
	}

	@Override
	public String getName() {
		return "CafAdmin";
	}

	@Override
	public void initialize(final Bootstrap<CafAdminConfiguration> bootstrap) {
			
	}

	@Override
	public void run(final CafAdminConfiguration configuration, final Environment environment) {
		Injector injector = Guice.createInjector(new CafAdminModule(configuration));
		environment.jersey().register(injector.getInstance(EmployeeResource.class));
		environment.healthChecks().register("mongo",injector.getInstance(DatabaseHealthCheck.class));
	}

}
