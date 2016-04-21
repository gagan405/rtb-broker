package com.yieldlabs.rtb.broker.dto;

/**
 * Created by gbm on 21/04/16.
 */
public enum  BidResponseTags {
  PRICE("\\$price\\$");

  private String tagString;

  BidResponseTags(String tagValue){
    this.tagString = tagValue;
  }

  public String getTagString(){
    return this.tagString;
  }

}
