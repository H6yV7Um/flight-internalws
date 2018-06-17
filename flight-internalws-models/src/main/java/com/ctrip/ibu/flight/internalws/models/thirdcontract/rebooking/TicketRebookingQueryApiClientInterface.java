/**
 * Autogenerated by soa-sdk-toolkit
 *
 * DO NOT EDIT DIRECTLY
 */
package com.ctrip.ibu.flight.internalws.models.thirdcontract.rebooking;

import com.ctriposs.baiji.rpc.common.types.CheckHealthRequestType;
import com.ctriposs.baiji.rpc.common.types.CheckHealthResponseType;
import com.google.common.util.concurrent.ListenableFuture;
import com.ctrip.soa.caravan.common.delegate.Func;

@SuppressWarnings("all")
public interface TicketRebookingQueryApiClientInterface {

   
   CheckHealthResponseType checkHealth(CheckHealthRequestType request) throws Exception;

   CheckHealthResponseType checkHealth(CheckHealthRequestType request, Func<CheckHealthResponseType> fallbackProvider) throws Exception;

   ListenableFuture<CheckHealthResponseType> checkHealthAsync(CheckHealthRequestType request) throws Exception;

   
   QueryOrderRebookingInfoResponseType queryOrderRebookingInfo(QueryOrderRebookingInfoRequestType request) throws Exception;

   QueryOrderRebookingInfoResponseType queryOrderRebookingInfo(QueryOrderRebookingInfoRequestType request, Func<QueryOrderRebookingInfoResponseType> fallbackProvider) throws Exception;

   ListenableFuture<QueryOrderRebookingInfoResponseType> queryOrderRebookingInfoAsync(QueryOrderRebookingInfoRequestType request) throws Exception;
}