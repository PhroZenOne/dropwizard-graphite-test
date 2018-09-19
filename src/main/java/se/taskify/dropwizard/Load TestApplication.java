package se.taskify.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Load TestApplication extends Application<Load TestConfiguration> {

    public static void main(final String[] args) throws Exception {
        new Load TestApplication().run(args);
    }

    @Override
    public String getName() {
        return "Load Test";
    }

    @Override
    public void initialize(final Bootstrap<Load TestConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final Load TestConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
