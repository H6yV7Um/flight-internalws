package com.ctrip.ibu.flight.internalws.repository.ws.flight.mergeorderdetail;

import com.ctrip.ibu.flight.afterbooking.contract.*;
import com.ctrip.ibu.flight.internalws.common.utils.DateUtil;
import com.ctrip.ibu.flight.internalws.common.businesshelper.BusinessHelper;
import com.ctrip.ibu.flight.internalws.models.flight.FlightColumn;
import com.ctrip.ibu.flight.internalws.models.flight.LatestDraftTimeEntity;
import com.ctrip.ibu.flight.internalws.models.flight.Passenger;
import com.ctrip.ibu.flight.internalws.models.flight.afterbooking.mergeorderdetail.*;
import org.mapstruct.*;

import java.util.Date;

/**
 * 订单基础信息Mapper接口
 * author : kyxie
 * date : 2018/5/4 15:26
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN,componentModel = "jsr330",nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION,imports = {
        Date.class,DateUtil.class,BusinessHelper.class
})
public interface EntityConvertMapper {

    OrderBasicInfoEntity convertWSOrderBasicInfo(OrderBasicInfoType wsOrderBasicInfo);

    LatestDraftTimeEntity convertWSLatestDraftTime(LatestDraftTimeType wsLatestDraftTime);

    @Mappings({
            @Mapping(source = "paymentDetailDescList",target = "paymentDetailEntityList")
    })
    PaymentInfoEntity convertWSPaymentInfo(OrderPaymentInfoType wsPaymentInfo);

    @Mappings({
            @Mapping(source = "mergeFlightInfo",target = "orderIdFlightInfoMapList")
    })
    MergePassengerFlightInfoMapEntity convertWSPassengerFlightInfoMap(MergePassengerFlightInfoType wsMergePassengerFlightInfoMap);

    @Mappings({
            @Mapping(source = "oriAcutalOrderStatus",target = "oriActualOrderStatus")
    })
    MergeOrderInfoEntity convertWSMergeOrderInfo(MergeOrderInfoType wsMergeOrderInfo);

    OrderIdFlightInfoMapEntity convertWSOrderIdFlightInfoMap(MergeFlightInfoType wsMergeFlightInfo);

    @Mappings({
            @Mapping(source = "flightInfo",target = "flightColumnInfo")
    })
    MergeFlightInfoMapEntity convertWSMergeFlightInfo(MergeFlightInfoItemType wsFlightInfo);

    @Mappings({
            @Mapping(source = "segNo",target = "segmentNo"),
            @Mapping(source = "sequence",target = "sequenceNo"),
            @Mapping(source = "classGrade",target = "className"),
            @Mapping(source = "craftTypeInfo",target = "craftType"),
            @Mapping(target = "dDate",expression = "java(new Date(wsFlightSequence.dDate))"),
            @Mapping(target = "aDate",expression = "java(new Date(wsFlightSequence.aDate))")
    })
    FlightColumn convertWSFlightSequence(FlightSequence wsFlightSequence);

    @Mappings({
            @Mapping(source = "nationality",target = "nationalityName"),
            @Mapping(source = "iDName",target = "idName"),
            @Mapping(source = "iDNumber",target = "idNo"),
            @Mapping(target = "birthday",expression = "java(DateUtil.longToCalendar(wsPassengerInfo.birth))"),
            @Mapping(target = "idCardType",expression = "java(BusinessHelper.parseIdType(wsPassengerInfo.iDType))"),
            @Mapping(target = "passengerType",expression = "java(BusinessHelper.parsePassengerType(wsPassengerInfo.passengerType))")
    })
    Passenger convertWSPassengerInfo(PassengerInfoType wsPassengerInfo);
}
