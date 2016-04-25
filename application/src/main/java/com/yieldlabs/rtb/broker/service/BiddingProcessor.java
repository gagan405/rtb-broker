package com.yieldlabs.rtb.broker.service;

import com.google.inject.Inject;
import com.yieldlabs.rtb.broker.dto.BidRequest;
import com.yieldlabs.rtb.broker.dto.BidResponse;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * Created by gbm on 20/04/16.
 */

public class BiddingProcessor {
  private final BidRequester bidRequester;
  private final BidResponseProcessor bidResponseProcessor;

  @Inject
  public BiddingProcessor(BidRequester bidRequester,
                          BidResponseProcessor bidResponseProcessor){
    this.bidRequester = bidRequester;
    this.bidResponseProcessor = bidResponseProcessor;
  }

  public Optional<String> fetchResponse(BidRequest request) throws InterruptedException, ExecutionException {
    return bidResponseProcessor.getAuctionResult(bidRequester.getBids(request));
  }
}
