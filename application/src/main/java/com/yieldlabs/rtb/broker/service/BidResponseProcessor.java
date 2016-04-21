package com.yieldlabs.rtb.broker.service;

import com.yieldlabs.rtb.broker.dto.BidResponse;

import java.util.List;

/**
 * Created by gbm on 21/04/16.
 */
public interface BidResponseProcessor {
  String getAuctionResult(List<BidResponse> bidResponses);
}
