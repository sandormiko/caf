package hu.sm.caf.admin;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CafAdminApplication extends Application<CafAdminConfiguration> {

    public static void main(final String[] args) throws Exception {
        new CafAdminApplication().run(args);
    }

    @Override
    public String getName() {
        return "caf";
    }

    @Override
    public void initialize(final Bootstrap<CafAdminConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final CafAdminConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
