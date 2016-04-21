package com.yieldlabs.rtb.broker;


import com.yieldlabs.rtb.broker.config.AppConfiguration;
import com.yieldlabs.rtb.broker.config.RtbBrokerModule;
import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.TimeZone;
import java.util.concurrent.ExecutorService;


public class BrokerApplication extends Application<AppConfiguration> {

    public static void main(String[] args) throws Exception {
        new BrokerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {

        GuiceBundle<AppConfiguration> guiceBundle = new GuiceBundle.Builder<AppConfiguration>()
                .setConfigClass(AppConfiguration.class)
                .addModule(new RtbBrokerModule())
                .enableAutoConfig("com.yieldlabs.rtb.broker")
                .build(Stage.DEVELOPMENT);

        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(AppConfiguration config,
                    Environment environment) throws ClassNotFoundException {
      environment.healthChecks().register("app", new AppHealthCheck());
    }
}