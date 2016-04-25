package com.yieldlabs.rtb.broker.service;

import com.yieldlabs.rtb.broker.dto.BidResponse;

import java.util.List;
import java.util.Optional;

/**
 * Created by gbm on 21/04/16.
 */
public interface BidResponseProcessor {
  Optional<String> getAuctionResult(List<BidResponse> bidResponses);
}
