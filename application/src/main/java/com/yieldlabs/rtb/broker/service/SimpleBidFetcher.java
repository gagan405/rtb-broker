package com.yieldlabs.rtb.broker.service;

import com.google.inject.Inject;
import com.yieldlabs.rtb.broker.config.BiddingConfiguration;
import com.yieldlabs.rtb.broker.dto.BidRequest;
import com.yieldlabs.rtb.broker.dto.BidResponse;

import lombok.extern.slf4j.Slf4j;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;


import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by gbm on 20/04/16.
 */
@Slf4j
public class SimpleBidFetcher implements BidRequester {

  private final BiddingConfiguration biddingConfiguration;
  private final ExecutorService executorService;

  @Inject
  public SimpleBidFetcher(BiddingConfiguration biddingConfiguration,
                          ExecutorService executorService){
    this.biddingConfiguration = biddingConfiguration;
    this.executorService = executorService;
  }

  public List<BidResponse> getBids(BidRequest bidRequest) throws InterruptedException, ExecutionException {
    List<BidResponse> responses = new ArrayList<>();

    CompletionService<BidResponse> completionService =
        new ExecutorCompletionService<>(executorService);

    for(String url : biddingConfiguration.getBidderUrls()) {
      BidFetcherClient fetcherClient = new BidFetcherClient(bidRequest, url);
      completionService.submit(fetcherClient);
    }

    for(int count = 0;count <  biddingConfiguration.getBidderUrls().size(); count++){
      Future<BidResponse> bidResponseFuture = completionService.take();
      BidResponse response = bidResponseFuture.get();
      if(response != null) {
        responses.add(response);
      }
    }

    return responses;
  }

  private Client getNewClient() {
    ClientConfig configuration = new ClientConfig();
    configuration = configuration.property(ClientProperties.CONNECT_TIMEOUT, biddingConfiguration.getBiddingTimeoutInMillis());
    configuration = configuration.property(ClientProperties.READ_TIMEOUT, biddingConfiguration.getBiddingTimeoutInMillis());
    Client client = ClientBuilder.newClient(configuration);

    return client;
  }

  class BidFetcherClient implements Callable<BidResponse> {

    private BidRequest bidRequest;
    private String url;

    public BidFetcherClient(BidRequest bidRequest, String url){
      this.bidRequest = bidRequest;
      this.url = url;
    }

    @Override
    public BidResponse call() throws Exception {
      Client client = getNewClient();
      try {
        return client.target(url)
            .request()
            .post(Entity.entity(bidRequest, MediaType.APPLICATION_JSON_TYPE), BidResponse.class);
      }catch (ProcessingException e){
        log.warn("Exception in fetching bid from {}", url);
        e.printStackTrace();
        return null;
      }
    }
  }
}
