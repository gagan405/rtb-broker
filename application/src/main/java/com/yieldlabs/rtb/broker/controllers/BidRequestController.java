package com.yieldlabs.rtb.broker.controllers;

import com.google.inject.Inject;
import com.yieldlabs.rtb.broker.dto.BidRequest;
import com.yieldlabs.rtb.broker.service.BiddingProcessor;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by gbm on 20/04/16.
 */
@Path("/")
public class BidRequestController {
  private final BiddingProcessor biddingProcessor;

  @Inject
  public BidRequestController(BiddingProcessor biddingProcessor){
    this.biddingProcessor = biddingProcessor;
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getBids(@Context UriInfo uriInfo, @PathParam("id") Long id) throws InterruptedException, ExecutionException, TimeoutException, IOException {
    MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    BidRequest bidRequest = new BidRequest();
    bidRequest.setId(id);
    bidRequest.setAttributes(queryParams);

    Optional<String> response = biddingProcessor.fetchResponse(bidRequest);
    if(response.isPresent()) {
      return Response.ok(response.get()).build();
    }
    throw new WebApplicationException("Unable to fetch any bids!");
  }
}
