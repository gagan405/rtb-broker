package com.yieldlabs.rtb.broker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by gbm on 20/04/16.
 */
@Getter @Setter
//@JsonIgnoreProperties(ignoreUnknown = true)
public class BidResponse {
  private String id;
  private Long bid;
  private String content;
}
