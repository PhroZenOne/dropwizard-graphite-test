package se.taskify.loadtest;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import se.taskify.loadtest.resources.PingResource;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class LoadTestApplication extends Application<LoadTestConfiguration> {

    public static void main(final String[] args) throws Exception {
        if (args.length == 0) {
            new LoadTestApplication().run("server");
        } else {
            new LoadTestApplication().run(args);
        }
    }

    @Override
    public String getName() {
        return "LoadTest";
    }

    @Override
    public void initialize(final Bootstrap<LoadTestConfiguration> bootstrap) {

    }

    @Override
    public void run(final LoadTestConfiguration configuration,
                    final Environment environment) throws IOException {


        System.out.println("STARTING GRAPHITE");

        final Graphite graphite = new Graphite(new InetSocketAddress("graphite", 2003));
        final GraphiteReporter reporter = GraphiteReporter.forRegistry(environment.metrics())
            .prefixedWith("taskify.se")
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS)
            .filter(MetricFilter.ALL)
            .build(graphite);
        reporter.start(1, TimeUnit.MINUTES);

        if (!graphite.isConnected()) {
            graphite.connect();
        }


        if (!graphite.isConnected()) {
            System.err.println("FAILED TO CONNECT");
        }

        System.out.println("GRAPHITE CONNECTED");

        final PingResource resource = new PingResource();
        environment.jersey().register(resource);
    }
}
