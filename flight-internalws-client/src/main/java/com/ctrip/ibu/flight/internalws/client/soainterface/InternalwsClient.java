package com.ctrip.ibu.flight.internalws.client.soainterface;

import com.google.common.util.concurrent.ListenableFuture;
import com.ctrip.soa.caravan.common.delegate.Func;
import com.ctriposs.baiji.rpc.client.*;
import com.ctrip.ibu.flight.internalws.client.contract.sendemailservice.SendEmailResponseType;
import com.ctrip.ibu.flight.internalws.client.contract.sendemailservice.SendEmailRequestType;
import com.ctrip.ibu.flight.internalws.client.contract.getemailstatusservice.GetEmailStatusResponseType;
import com.ctrip.ibu.flight.internalws.client.contract.getemailstatusservice.GetEmailStatusRequestType;
import com.ctriposs.baiji.rpc.common.types.CheckHealthResponseType;
import com.ctriposs.baiji.rpc.common.types.CheckHealthRequestType;

public class InternalwsClient extends ServiceClientBase<InternalwsClient> {
    public static final String ORIGINAL_SERVICE_NAME = "internalws";

    public static final String ORIGINAL_SERVICE_NAMESPACE = "http://soa.ctrip.com/international/flight/internalws/v1";

    public static final String CODE_GENERATOR_VERSION = "BJCG-2.4.5.9";

    private InternalwsClient(String baseUri) {
        super(InternalwsClient.class, baseUri);
    }

    private InternalwsClient(String serviceName, String serviceNamespace, String subEnv) throws ServiceLookupException {
        super(InternalwsClient.class, serviceName, serviceNamespace, subEnv);
    }

    public static InternalwsClient getInstance() {
        return ServiceClientBase.getInstance(InternalwsClient.class);
    }

    public static InternalwsClient getInstance(String baseUrl) {
        return ServiceClientBase.getInstance(InternalwsClient.class, baseUrl);
    }

    public SendEmailResponseType sendEmail(SendEmailRequestType request)
                                    throws Exception {
        return super.invoke("sendEmail", request, SendEmailResponseType.class);
    }

    public SendEmailResponseType sendEmail(SendEmailRequestType request, Func<SendEmailResponseType> fallbackProvider)
                                    throws Exception {
        return super.invoke("sendEmail", request, fallbackProvider, SendEmailResponseType.class);
    }

    public ListenableFuture<SendEmailResponseType> sendEmailAsync(SendEmailRequestType request)
                                    throws Exception {
        return super.invokeAsync("sendEmail", request, SendEmailResponseType.class);
    }

    public GetEmailStatusResponseType getEmailStatus(GetEmailStatusRequestType request)
                                    throws Exception {
        return super.invoke("getEmailStatus", request, GetEmailStatusResponseType.class);
    }

    public GetEmailStatusResponseType getEmailStatus(GetEmailStatusRequestType request, Func<GetEmailStatusResponseType> fallbackProvider)
                                    throws Exception {
        return super.invoke("getEmailStatus", request, fallbackProvider, GetEmailStatusResponseType.class);
    }

    public ListenableFuture<GetEmailStatusResponseType> getEmailStatusAsync(GetEmailStatusRequestType request)
                                    throws Exception {
        return super.invokeAsync("getEmailStatus", request, GetEmailStatusResponseType.class);
    }

    public CheckHealthResponseType checkHealth(CheckHealthRequestType request)
                                    throws Exception {
        return super.invoke("checkHealth", request, CheckHealthResponseType.class);
    }

    public CheckHealthResponseType checkHealth(CheckHealthRequestType request, Func<CheckHealthResponseType> fallbackProvider)
                                    throws Exception {
        return super.invoke("checkHealth", request, fallbackProvider, CheckHealthResponseType.class);
    }

    public ListenableFuture<CheckHealthResponseType> checkHealthAsync(CheckHealthRequestType request)
                                    throws Exception {
        return super.invokeAsync("checkHealth", request, CheckHealthResponseType.class);
    }
}