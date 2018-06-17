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
import com.ctriposs.baiji.rpc.client.ServiceClientBase;
import com.ctriposs.baiji.rpc.client.ServiceLookupException;

@SuppressWarnings("all")
public class TicketRebookingQueryApiClient extends ServiceClientBase<TicketRebookingQueryApiClient> implements TicketRebookingQueryApiClientInterface {

    public static final String ORIGINAL_SERVICE_NAME = "TicketRebookingQueryApi";

    public static final String ORIGINAL_SERVICE_NAMESPACE = "http://soa.ctrip.com/flight/Ticket/Rebooking/QueryApi/v1";

    public static final String CODE_GENERATOR_VERSION = "WSDL-2.6.7";

    public static final String ORIGINAL_SERVICE_TYPE = "NonSLB";

    private TicketRebookingQueryApiClient(String baseUri) {
        super(TicketRebookingQueryApiClient.class, baseUri);
    }

    private TicketRebookingQueryApiClient(String serviceName, String serviceNamespace, String subEnv) throws ServiceLookupException {
        super(TicketRebookingQueryApiClient.class, serviceName, serviceNamespace, subEnv);
    }

    public static TicketRebookingQueryApiClient getInstance() {
        return ServiceClientBase.getInstance(TicketRebookingQueryApiClient.class);
    }

    public static TicketRebookingQueryApiClient getInstance(String baseUri) {
        return ServiceClientBase.getInstance(TicketRebookingQueryApiClient.class, baseUri);
    }

    @Override
    public CheckHealthResponseType checkHealth(CheckHealthRequestType request)
                                        throws Exception {
        return super.invoke("checkHealth",  request, CheckHealthResponseType.class);
    }

    @Override
    public CheckHealthResponseType checkHealth(CheckHealthRequestType request, Func<CheckHealthResponseType> fallbackProvider)
                                        throws Exception {
        return super.invoke("checkHealth",  request, fallbackProvider, CheckHealthResponseType.class);
    }

    @Override
    public ListenableFuture<CheckHealthResponseType> checkHealthAsync(CheckHealthRequestType request)
                                        throws Exception {
        return super.invokeAsync("checkHealth",  request, CheckHealthResponseType.class);
    }

    @Override
    public QueryOrderRebookingInfoResponseType queryOrderRebookingInfo(QueryOrderRebookingInfoRequestType request)
                                        throws Exception {
        return super.invoke("queryOrderRebookingInfo",  request, QueryOrderRebookingInfoResponseType.class);
    }

    @Override
    public QueryOrderRebookingInfoResponseType queryOrderRebookingInfo(QueryOrderRebookingInfoRequestType request, Func<QueryOrderRebookingInfoResponseType> fallbackProvider)
                                        throws Exception {
        return super.invoke("queryOrderRebookingInfo",  request, fallbackProvider, QueryOrderRebookingInfoResponseType.class);
    }

    @Override
    public ListenableFuture<QueryOrderRebookingInfoResponseType> queryOrderRebookingInfoAsync(QueryOrderRebookingInfoRequestType request)
                                        throws Exception {
        return super.invokeAsync("queryOrderRebookingInfo",  request, QueryOrderRebookingInfoResponseType.class);
    }
}