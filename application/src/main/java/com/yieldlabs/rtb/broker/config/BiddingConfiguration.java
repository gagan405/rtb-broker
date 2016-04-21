package com.yieldlabs.rtb.broker.config;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gbm on 21/04/16.
 */
@Getter @Setter
public class BiddingConfiguration {
  private Integer biddingTimeoutInMillis = 100;
  private List<String> bidderUrls = new ArrayList<>();
}
