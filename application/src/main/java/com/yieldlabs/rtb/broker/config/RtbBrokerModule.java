package com.yieldlabs.rtb.broker.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.yieldlabs.rtb.broker.service.BidRequester;
import com.yieldlabs.rtb.broker.service.BidResponseProcessor;
import com.yieldlabs.rtb.broker.service.HighestBidderResponseProcessor;
import com.yieldlabs.rtb.broker.service.SimpleBidFetcher;
import io.dropwizard.setup.Environment;

import java.util.concurrent.ExecutorService;

public class RtbBrokerModule extends AbstractModule {

    @Override
    protected void configure() {
      bind(BidRequester.class).to(SimpleBidFetcher.class);
      bind(BidResponseProcessor.class).to(HighestBidderResponseProcessor.class);
    }

  @Provides
  public BiddingConfiguration providesBiddingConfiguration(Provider<AppConfiguration> appConfigurationProvider){
    return appConfigurationProvider.get().getBiddingConfiguration();
  }

  @Provides
  @Singleton
  public ExecutorService executorServiceProvider(Provider<Environment> environmentProvider){
    return environmentProvider.get().lifecycle().executorService("rtb-executor-service").build();
  }
}
