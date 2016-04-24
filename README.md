# rtb-broker
A sample real time bidding broker
================================

Tools used :

Dropwizard container
Guice injection
Jersey
Jackson
Lombok
....

To build :

./gradlew build

To run : 

./gradlew runService

Server configurations, bidder urls are stored in the config file : development.yaml

*Implementation Details*

biddingConfiguration has the bidder urls and timeout values.
The bids are requested in parallel to all the bidders with a certain timeout value, and within that time period, whatever bids
are received are then processed. Slow bidders are automatically cut-off from bidding.

ExecutorService from dropwizard is being used. This is a singleton and is created in RtbBrokerModule. This executor is used to send the bid requests to all clients in parallel.

SimpleBidFetcher uses javax ws clients to send bid requests. If we need to have a different client, it needs to implement BidRequester interface. We can also use async http clients to make parallel requests, but async clients come with some additional overhead and also, they are generally used for situations where we need a callback after some long processing at the endpoint. But RTB is usually a low latency application. So better to use sync clients with some timeout.

We can have multiple implementations and bind them using named annotations. The bindings are done in Module classes. For e.g., RtbBrokerModule

Likewise, the BidResponseProcessor takes all the bid responses received and returns a final accepted bid after some procesing. The deciding logic on how to choose a bid is with the implementation classes.

Presently only HighestBidderResponseProcessor is implemented that simply returns the highest bidder.
If a different bidder selection logic is to be added, another implementation of the same interface needs to be added.




