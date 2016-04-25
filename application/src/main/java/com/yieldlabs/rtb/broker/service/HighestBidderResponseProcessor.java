package com.yieldlabs.rtb.broker.service;

import com.yieldlabs.rtb.broker.dto.BidResponse;
import com.yieldlabs.rtb.broker.dto.BidResponseTags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by gbm on 21/04/16.
 */
public class HighestBidderResponseProcessor implements BidResponseProcessor {
  @Override
  public Optional<String> getAuctionResult(List<BidResponse> bidResponses) {
    List<BidResponse> bidResponseList = new ArrayList<>(bidResponses);
    Collections.sort(bidResponseList, (o1, o2) -> o2.getBid().compareTo(o1.getBid()));

    if(bidResponseList.isEmpty()){
      return Optional.empty();
    }
    Long bid =  bidResponseList.get(0).getBid();
    return Optional.of(bidResponseList.get(0).getContent().replaceAll(BidResponseTags.PRICE.getTagString(), String.valueOf(bid)));
  }
}
