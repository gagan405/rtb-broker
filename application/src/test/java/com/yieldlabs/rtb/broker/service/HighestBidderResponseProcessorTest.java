package com.yieldlabs.rtb.broker.service;

import com.yieldlabs.rtb.broker.dto.BidResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by gbm on 25/04/16.
 */
public class HighestBidderResponseProcessorTest {

  @Before
  public void setup(){
  }

  @After
  public void tearDown(){
  }


  @Test
  public void testHighestBidderEmptyList(){
    List<BidResponse> bidResponses = new ArrayList<>();
    HighestBidderResponseProcessor highestBidderResponseProcessor = new HighestBidderResponseProcessor();
    Optional<String> bidResponse = highestBidderResponseProcessor.getAuctionResult(bidResponses);
    Assert.assertFalse(bidResponse.isPresent());
  }

  @Test
  public void testHighestBidder(){
    List<BidResponse> bidResponses = getBidResponses();
    HighestBidderResponseProcessor highestBidderResponseProcessor = new HighestBidderResponseProcessor();
    Optional<String> bidResponse = highestBidderResponseProcessor.getAuctionResult(bidResponses);
    Assert.assertTrue(bidResponse.isPresent());
    String result = bidResponse.get();
    Assert.assertEquals("b:150",result);
  }


  private List<BidResponse> getBidResponses() {
    List<BidResponse> bidResponses = new ArrayList<>();
    BidResponse bidResponse1 = new BidResponse();
    bidResponse1.setBid(100l);
    bidResponse1.setContent("a:price");
    bidResponse1.setId("1");

    BidResponse bidResponse2 = new BidResponse();
    bidResponse2.setBid(150l);
    bidResponse2.setContent("b:$price$");
    bidResponse2.setId("2");

    BidResponse bidResponse3 = new BidResponse();
    bidResponse3.setBid(120l);
    bidResponse3.setContent("c:$price$");
    bidResponse3.setId("3");

    bidResponses.add(bidResponse1);
    bidResponses.add(bidResponse2);
    bidResponses.add(bidResponse3);

    return bidResponses;
  }


}
