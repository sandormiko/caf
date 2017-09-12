package caf.admin;

import caf.foundation.kafka.KafkaModule;
import caf.foundation.mongo.MongoModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import caf.admin.employee.boundary.EmployeeResource;
import caf.foundation.config.ApplicationConfiguration;
import caf.foundation.kafka.ManagedKafkaProducer;
import caf.foundation.mongo.ManagedMongoClient;
import caf.foundation.mongo.healthcheck.DatabaseHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CafAdminApplication extends Application<ApplicationConfiguration> {

	public static void main(final String[] args) throws Exception {
		new CafAdminApplication().run(args);
	}

	@Override
	public String getName() {
		return "CafAdmin";
	}

	@Override
	public void initialize(final Bootstrap<ApplicationConfiguration> bootstrap) {
			
	}

	@Override
	public void run(final ApplicationConfiguration configuration, final Environment environment) {
		Injector injector = Guice.createInjector(new CafAdminModule(configuration), new MongoModule(), new KafkaModule());
		environment.jersey().register(injector.getInstance(EmployeeResource.class));
		environment.healthChecks().register("mongo",injector.getInstance(DatabaseHealthCheck.class));
		environment.lifecycle().manage(injector.getInstance(ManagedMongoClient.class));
		environment.lifecycle().manage(injector.getInstance(ManagedKafkaProducer.class));
	}

}
