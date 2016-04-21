package com.yieldlabs.rtb.broker.dto;

import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.core.MultivaluedMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gbm on 20/04/16.
 */
@Getter @Setter
public class BidRequest {
  private Long id;
  private Map<String, Object> attributes;

  public void setAttributes(MultivaluedMap<String, String> attributes){
    this.attributes = new HashMap<>();
    for(Map.Entry<String, List<String>> e : attributes.entrySet()){
      if(e.getValue().size() == 1){
        this.attributes.put(e.getKey(), e.getValue().get(0));
      } else {
        this.attributes.put(e.getKey(), e.getValue());
      }
    }
  }
}
