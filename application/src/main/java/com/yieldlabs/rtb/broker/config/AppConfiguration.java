package com.yieldlabs.rtb.broker.config;

import io.dropwizard.Configuration;

import io.dropwizard.client.HttpClientConfiguration;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * Created by gbm on 31/03/16.
 */

@Getter
@Setter
public class AppConfiguration extends Configuration {
  @Valid
  @NotNull
  private BiddingConfiguration biddingConfiguration = new BiddingConfiguration();
}
