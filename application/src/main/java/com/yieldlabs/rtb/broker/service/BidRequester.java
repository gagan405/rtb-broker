package com.yieldlabs.rtb.broker.service;

import com.yieldlabs.rtb.broker.dto.BidRequest;
import com.yieldlabs.rtb.broker.dto.BidResponse;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by gbm on 21/04/16.
 */
public interface BidRequester {
  List<BidResponse> getBids(BidRequest bidRequest) throws InterruptedException, ExecutionException;
}
