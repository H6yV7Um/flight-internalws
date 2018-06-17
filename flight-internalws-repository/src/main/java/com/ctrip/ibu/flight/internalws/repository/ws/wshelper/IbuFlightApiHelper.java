package com.ctrip.ibu.flight.internalws.repository.ws.wshelper;

import com.ctrip.framework.foundation.Foundation;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.comm.head.v1.AllianceInfo;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.comm.head.v1.Head;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.comm.head.v1.Language;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.comm.head.v1.Source;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.flightcommon.v1.*;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.orderdetailsearch.v1.*;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.orderdetailsearch.v1.ContactInfo;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.orderdetailsearch.v1.RebookingInfo;
import com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.searchxorderattached.v1.*;
import com.ctrip.ibu.flight.internalws.common.utils.CommonUtil;
import com.ctrip.ibu.flight.internalws.common.utils.DateUtil;
import com.ctrip.ibu.flight.internalws.models.common.RequestHead;
import com.ctrip.ibu.flight.internalws.models.constant.BusinessConst;
import com.ctrip.ibu.flight.internalws.models.flight.*;
import com.ctrip.ibu.flight.internalws.models.flight.FlightStopInfo;
import com.ctrip.ibu.flight.internalws.models.flight.FormatBaggageInfo;
import com.ctrip.ibu.flight.internalws.models.flight.LatestDraftTimeEntity;
import com.ctrip.ibu.flight.internalws.models.flight.offline.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * IBUFlightAPI服务帮助类
 * author : kyxie
 * date : 2018/5/2 11:48
 */
@Component
public class IbuFlightApiHelper {

    /**
     * 获取请求头
     */
    public Head getHead() {
        Head head = new Head();
        head.setAPIKey(BusinessConst.APPNAME);
        head.setIP(Foundation.net().getHostAddress());
        return head;
    }

    /**
     * 通过RequestHead获取请求头
     * @param requestHead 请求头
     * */
    public Head getHead(RequestHead requestHead){
        Head head = getHead();
        if (requestHead!=null) {
            head.setUID(requestHead.getUid());
            head.setSource(Source.ANDROID);
            head.setVersion("601.000");
            if (requestHead.getLanguage() == null){
                head.setLanguage(Language.ENGLISH);
            } else {
                switch (requestHead.getLanguage()){
                    case en_HK:
                    case en_AU:
                    case en_US: head.setLanguage(Language.ENGLISH);
                        break;
                    case zh_HK: head.setLanguage(Language.TC);
                        break;
                    case zh_TW: head.setLanguage(Language.TC);
                        break;
                    case zh_CN: head.setLanguage(Language.ZH);
                        break;
                    case ja_JP: head.setLanguage(Language.JP);
                        break;
                    case ko_KR: head.setLanguage(Language.KR);
                        break;
                    case fr_FR: head.setLanguage(Language.FR);
                        break;
                    case en_SG: head.setLanguage(Language.SG);
                        break;
                    case ru_RU: head.setLanguage(Language.RU);
                        break;
                    case es_ES: head.setLanguage(Language.ES);
                        break;
                    case ms_MY: head.setLanguage(Language.MY);
                        break;
                    case th_TH: head.setLanguage(Language.TH);
                        break;
                    case id_ID: head.setLanguage(Language.ID);
                        break;
                    case vi_VN: head.setLanguage(Language.VN);
                        break;
                    case de_DE: head.setLanguage(Language.DE);
                        break;
                    default:head.setLanguage(Language.ENGLISH);
                        break;
                }
            }
        }
        return head;
    }


    /**
     * 转换WS层Airline信息
     * */
    public Airline convertWSAirlineInfo(AirLineInfo wsAirlineInfo){
        if (wsAirlineInfo == null){
            return null;
        }
        Airline airline = new Airline();
        airline.setAirlineLowestPrice(wsAirlineInfo.getAirlineLowestPrice());
        airline.setBelongToAlliance(wsAirlineInfo.getBelongToAlliance());
        airline.setCode(wsAirlineInfo.getCode());
        airline.setEnName(wsAirlineInfo.getENName());
        airline.setName(wsAirlineInfo.getName());
        return airline;
    }

    /**
     * 转换WS层City
     * */
    public City convertWSCityInfo(CityInfo wsCityInfo){
        if (wsCityInfo == null){
            return null;
        }

        City city = new City();
        city.setName(wsCityInfo.getName());
        city.setId(wsCityInfo.getID());
        city.setEnName(wsCityInfo.getENName());
        city.setCode(wsCityInfo.getCode());
        return city;
    }

    /**
     * 转化WS层Airport
     * */
    public Airport convertWSAirportInfo(AirPortInfo wsAirport){
        if (wsAirport == null){
            return null;
        }
        Airport airport = new Airport();
        airport.setCode(wsAirport.getCode());
        airport.setEnName(wsAirport.getENName());
        airport.setId(wsAirport.getID());
        airport.setName(wsAirport.getName());
        return airport;
    }

    /**
     * 转换航班经停信息
     * @param wsStopInfo 经停
     * */
    public FlightStopInfo convertWSFlightStopInfo(com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.flightcommon.v1.FlightStopInfo wsStopInfo){
        if (wsStopInfo == null){
            return null;
        }

        FlightStopInfo flightStopInfo = new FlightStopInfo();
        flightStopInfo.setHour(wsStopInfo.getHour());
        flightStopInfo.setMinute(wsStopInfo.getMin());
        flightStopInfo.setDuration(wsStopInfo.getDuration());

        if (wsStopInfo.getCity() != null){
            City stopCity = new City();
            stopCity.setName(wsStopInfo.getCity().getName());
            stopCity.setCode(wsStopInfo.getCity().getCode());
            stopCity.setEnName(wsStopInfo.getCity().getENName());
            stopCity.setId(wsStopInfo.getCity().getID());
            flightStopInfo.setCity(stopCity);
        }

        if (wsStopInfo.getAirPort() != null){
            Airport stopAirport = new Airport();
            stopAirport.setCode(wsStopInfo.getAirPort().getCode());
            stopAirport.setEnName(wsStopInfo.getAirPort().getENName());
            stopAirport.setId(wsStopInfo.getAirPort().getID());
            stopAirport.setName(wsStopInfo.getAirPort().getName());
            flightStopInfo.setAirport(stopAirport);
        }

        return flightStopInfo;
    }

    /**
     * 转换WS层航站楼信息
     * */
    public Terminal convertWSTerminalInfo(TerminalInfo wsTerminal){
        if (wsTerminal == null){
            return null;
        }
        Terminal terminal = new Terminal();
        terminal.setCode(wsTerminal.getCode());
        terminal.setEnName(wsTerminal.getENName());
        terminal.setId(wsTerminal.getID());
        terminal.setName(wsTerminal.getName());
        terminal.setShortName(wsTerminal.getShortName());
        return terminal;
    }

    /**
     * 转换WS层Passenger信息
     * */
    public Passenger convertWSPassengerInfo(GaPassengerInfo wsPassenger){
        if (wsPassenger == null){
            return null;
        }
        Passenger passenger = new Passenger();
        passenger.setPassengerId(wsPassenger.getPassengerInfoID());
        passenger.setPassengerType(convertWSPassengerType(wsPassenger.getAgeType()));
        passenger.setName(wsPassenger.getName());
        passenger.setBirthday(DateUtil.gregorianCalendarToCalendar(wsPassenger.getBirthDay()));
        passenger.setGender(wsPassenger.getGender());
        passenger.setIdNo(wsPassenger.getIDNo());
        passenger.setIdName(wsPassenger.getIDName());
        passenger.setIdValidPeriod(DateUtil.gregorianCalendarToCalendar(wsPassenger.getIDLimit()));
        passenger.setNationalityCode(wsPassenger.getNationalityCode());
        passenger.setNationalityName(wsPassenger.getNationalityName());
        passenger.setPassengerType(convertWSPassenterType(wsPassenger.getTicketType()));

        return passenger;
    }

    /**
     * 转换乘机人类型
     * @param wsPassengerType API乘机人类型
     * */
    public PassengerType convertWSPassenterType(GaPassengerType wsPassengerType){
        if (wsPassengerType == null){
            return PassengerType.UNKNOWN;
        }

        switch (wsPassengerType){
            case CHD:
                return PassengerType.CHD;
            case INF:
                return PassengerType.INF;
            default:
                return PassengerType.ADT;
        }
    }

    /**
     * 转换WS层最晚出票时间
     * */
    public LatestDraftTimeEntity convertWSLastestDraftTime(com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.orderdetailsearch.v1.LatestDraftTimeEntity wsLastestDraftTime){
        if (wsLastestDraftTime == null){
            return null;
        }

        LatestDraftTimeEntity lastestDraftTime = new LatestDraftTimeEntity();
        lastestDraftTime.setProtectReference(wsLastestDraftTime.getProtectReference());
        lastestDraftTime.setHour(wsLastestDraftTime.getHour());
        lastestDraftTime.setMinute(wsLastestDraftTime.getMinute());
        return lastestDraftTime;
    }

    /**
     * 转换WS层联系人信息
     * */
    public com.ctrip.ibu.flight.internalws.models.flight.ContactInfo convertWSContactInfo(ContactInfo wsContactInfo){
        if (wsContactInfo == null){
            return null;
        }
        com.ctrip.ibu.flight.internalws.models.flight.ContactInfo contactInfo = new com.ctrip.ibu.flight.internalws.models.flight.ContactInfo();
        contactInfo.setEmail(wsContactInfo.getEmail());
        contactInfo.setName(wsContactInfo.getName());
        contactInfo.setPhone(wsContactInfo.getPhone());
        contactInfo.setPhoneAreaCode(wsContactInfo.getPhoneCountryfix());
        return contactInfo;
    }

    /**
     * 转换订单合作方信息
     * */
    public AllianceOrderModel convertWSAllianceInfo(AllianceInfo wsAllianceInfo){
        if (wsAllianceInfo == null){
            return null;
        }

        AllianceOrderModel allianceOrderInfo = new AllianceOrderModel();
        allianceOrderInfo.setAllianceId(wsAllianceInfo.getAllianceID());
        allianceOrderInfo.setOuid(wsAllianceInfo.getOuID());
        allianceOrderInfo.setSid(wsAllianceInfo.getSID());
        allianceOrderInfo.setUuid(wsAllianceInfo.getUuid());

        return allianceOrderInfo;
    }

    /**
     * 转换订单支付信息(聚合各个节点)
     *
     * @param payType 支付方式
     * @param wsPaymentInfo 支付明细
     * @param insurance 保险信息
     * */
    public PaymentInfo convertWSPaymentInfo(PayType payType, GaFlightPaymentInfo wsPaymentInfo,OrderInsurance insurance){
        if (wsPaymentInfo == null){
            return null;
        }
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setPayMethod(convertWSPayType(payType));
        paymentInfo.setCnyCarrayRule(wsPaymentInfo.getCnyCarrayRule());
        paymentInfo.setCurrencyType(convertWSSettleCurrency(wsPaymentInfo.getCurrencyType()));
        paymentInfo.setDataRule(wsPaymentInfo.getDataRule());
        paymentInfo.setExchangeRate(wsPaymentInfo.getExchangeRate());
        paymentInfo.setIsPayToCBU(wsPaymentInfo.getIsPayToCBU());
        paymentInfo.setIsRealTimePay(wsPaymentInfo.getIsRealTimePay());
        paymentInfo.setLoungeFee(wsPaymentInfo.getLoungeFee());
        paymentInfo.setMerchantInfo(wsPaymentInfo.getMerchantInfo());
        paymentInfo.setOrderForeignAmount(wsPaymentInfo.getOrderForeignAmount());
        paymentInfo.setPayCCardFee(wsPaymentInfo.getPayCCardFee());
        paymentInfo.setPayCouponAmount(wsPaymentInfo.getPayCouponAmount());
        paymentInfo.setPayDeliverFee(wsPaymentInfo.getPayDeliverFee());
        paymentInfo.setPayFlightPrice(wsPaymentInfo.getPayFlightPrice());
        paymentInfo.setPayOil(wsPaymentInfo.getPayOil());
        paymentInfo.setPaySubsidy(wsPaymentInfo.getPaySubsidy());
        paymentInfo.setPayTax(wsPaymentInfo.getPayTax());
        paymentInfo.setPayTotalInsuranceFee(wsPaymentInfo.getPayTotalInsuranceFee());
        paymentInfo.setPayTotalPackageAttachAmount(wsPaymentInfo.getPayTotalPackageAttachAmount());
        paymentInfo.setPayTotalPrice(wsPaymentInfo.getPayTotalPrice());
        paymentInfo.setPayTotalPriceNoCCardFee(wsPaymentInfo.getPayTotalPriceNoCCardFee());
        paymentInfo.setPayValueAddedBaggageFee(wsPaymentInfo.getPayValueAddedBaggageFee());
        paymentInfo.setPayXHotelCouponFee(wsPaymentInfo.getPayXHotelCouponFee());

        //航意险，旅行险
        if (insurance != null){
            paymentInfo.setPayTotalAviationAccidentInsuranceFee(insurance.getInsUnitPrice().multiply(new BigDecimal(insurance.getInsuranceCount())));
            paymentInfo.setPayTotalTravelInsuranceFee(insurance.getTravelInsUnitPrice().multiply(new BigDecimal(insurance.getTravelInsuranceCount())));
        }

        return paymentInfo;
    }

    /**
     * 转换WS层支付币种
     * */
    public SettlementCurrency convertWSSettleCurrency(GaFlightSettlementCurrencyType wsSettlementCurrency){
        switch (wsSettlementCurrency){
            case AUD:
                return SettlementCurrency.AUD;
            case BRL:
                return SettlementCurrency.BRL;
            case CAD:
                return SettlementCurrency.CAD;
            case CHF:
                return SettlementCurrency.CHF;
            case CNY:
                return SettlementCurrency.CNY;
            case DKK:
                return SettlementCurrency.DKK;
            case EUR:
                return SettlementCurrency.EUR;
            case GBP:
                return SettlementCurrency.GBP;
            case HKD:
                return SettlementCurrency.HKD;
            case IDR:
                return SettlementCurrency.IDR;
            case INR:
                return SettlementCurrency.INR;
            case JPY:
                return SettlementCurrency.JPY;
            case KRW:
                return SettlementCurrency.KRW;
            case MOP:
                return SettlementCurrency.MOP;
            case MYR:
                return SettlementCurrency.MYR;
            case NZD:
                return SettlementCurrency.NZD;
            case PHP:
                return SettlementCurrency.PHP;
            case PLN:
                return SettlementCurrency.PLN;
            case RUB:
                return SettlementCurrency.RUB;
            case SEK:
                return SettlementCurrency.SEK;
            case SGD:
                return SettlementCurrency.SGD;
            case THB:
                return SettlementCurrency.THB;
            case TRY:
                return SettlementCurrency.TRY;
            case TWD:
                return SettlementCurrency.TWD;
            case USD:
                return SettlementCurrency.USD;
            case VND:
                return SettlementCurrency.VND;
            default:
                return SettlementCurrency.UNKNOWN;
        }
    }

    /**
     * 转换WS层航程类型
     * */
    public FlightWay convertWSFlightWayType(GaFlightWayType wsFlightWay){
        switch (wsFlightWay){
            case OW:
                return FlightWay.OW;
            case RT:
                return FlightWay.RT;
            case MT:
                return FlightWay.MT;
            default:
                return FlightWay.UNKNOWN;
        }
    }

    /**
     * 转换WS层改签信息
     */
    public com.ctrip.ibu.flight.internalws.models.flight.RebookingInfo convertWSRebookingInfo(RebookingInfo wsRebookingInfo) {
        if (wsRebookingInfo == null) {
            return null;
        }

        com.ctrip.ibu.flight.internalws.models.flight.RebookingInfo rebookingInfo = new com.ctrip.ibu.flight.internalws.models.flight.RebookingInfo();
        rebookingInfo.setOrderId(wsRebookingInfo.getOrderID());
        rebookingInfo.setRebookingApplicationId(wsRebookingInfo.getRebookingApplicationID());
        rebookingInfo.setApplicationStatus(wsRebookingInfo.getApplicationStatus());
        rebookingInfo.setAirline(wsRebookingInfo.getAirline());
        rebookingInfo.setBookSeatType(wsRebookingInfo.getBookSeatType());
        rebookingInfo.setRequestTime(DateUtil.gregorianCalendarToCalendar(wsRebookingInfo.getRequestTime()));
        rebookingInfo.setChosenNewFlight(wsRebookingInfo.isIsChosenNewFlight());
        rebookingInfo.setRebookNeedPay(wsRebookingInfo.isRebookNeedPay());
        rebookingInfo.setPayStatus(wsRebookingInfo.getPayStatus());
        rebookingInfo.setPayExternalNo(wsRebookingInfo.getPayExternalNo());
        rebookingInfo.setPaymentDeadline(DateUtil.gregorianCalendarToCalendar(wsRebookingInfo.getPaymentDeadline()));
        rebookingInfo.setBaggageRemark(wsRebookingInfo.getBaggageRemark());
        rebookingInfo.setfCardServiceFee(wsRebookingInfo.getFCardServiceFee());
        rebookingInfo.setFlightDescription(wsRebookingInfo.getFlightDescription());
        rebookingInfo.setUnconfirmedStatus(wsRebookingInfo.isIsFreeUnconfirmed());

        //改签旧航班
        rebookingInfo.setRebookingFlightItemList(CommonUtil.convertList(wsRebookingInfo.getRebookingFlightItemList(), (flightOrigDestInfo) -> convertWSFlightInfo(flightOrigDestInfo)));

        //改签新航班
        rebookingInfo.setRebookingNewFlightItemList(CommonUtil.convertList(wsRebookingInfo.getRebookingNewFlightItemList(), (flightOrigDestInfo -> convertWSFlightInfo(flightOrigDestInfo))));

        //改签乘机人信息
        rebookingInfo.setRebookingPassengerItemList(CommonUtil.convertList(wsRebookingInfo.getRebookingPassengerItemList(), (gaPassengerInfo -> convertWSPassengerInfo(gaPassengerInfo))));

        //改签联系人信息
        rebookingInfo.setRebookingContactInfo(convertWSContactInfo(wsRebookingInfo.getRebookingContactInfo()));

        //改签支付信息
        rebookingInfo.setRebookingPayDetail(convertRebookingPayDetail(wsRebookingInfo.getReookingPayDetailInfo()));

        return rebookingInfo;
    }

    /**
     * 转换改签支付明细信息
     */
    public RebookingPayDetail convertRebookingPayDetail(RebookingPayDetailInfo wsRebookingPayInfo) {
        if (wsRebookingPayInfo == null) {
            return null;
        }

        RebookingPayDetail rebookingPayDetail = new RebookingPayDetail();
        rebookingPayDetail.setAirlineServiceFeeForCustom(wsRebookingPayInfo.getAirlineServiceFeeForCustom());
        rebookingPayDetail.setCtripServiceFeeForCustom(wsRebookingPayInfo.getCtripServiceFeeForCustom());
        rebookingPayDetail.setCurrency(wsRebookingPayInfo.getCurrency());
        rebookingPayDetail.setCurrencyRate(wsRebookingPayInfo.getCurrencyRate());
        rebookingPayDetail.setDateChangeFee(wsRebookingPayInfo.getDateChangeFee());
        rebookingPayDetail.setfCardServiceFee(wsRebookingPayInfo.getFCardServiceFee());
        rebookingPayDetail.setPriceDifferential(wsRebookingPayInfo.getPriceDifferential());
        rebookingPayDetail.setSupplierServiceFeeForCustom(wsRebookingPayInfo.getSupplierServiceFeeForCustom());
        rebookingPayDetail.setTaxDifferential(wsRebookingPayInfo.getTaxDifferential());
        rebookingPayDetail.setTotalRebookFee(wsRebookingPayInfo.getTotalRebookFee());

        return rebookingPayDetail;
    }

    /**
     * 转换WS层保险信息
     * */
    public Insurance convertWSInsurance(OrderInsurance wsInsurance){
        if (wsInsurance == null){
            return null;
        }

        Insurance insurance = new Insurance();
        insurance.setCnyTotalPrice(wsInsurance.getCNYTotalPrice());
        insurance.setInsUnitPrice(wsInsurance.getInsUnitPrice());
        insurance.setInsuranceCount(wsInsurance.getInsuranceCount());
        insurance.setOrigTotalPrice(wsInsurance.getOrigTotalPrice());
        insurance.setShowCurrencyOrigTotalPrice(wsInsurance.getShowCurrencyOrigTotalPrice());
        insurance.setTotalPrice(wsInsurance.getTotalPrice());
        insurance.setTravelInsTotalPrice(wsInsurance.getTravelInsTotalPrice());
        insurance.setTravelInsType(wsInsurance.getTravelInsType());
        insurance.setTravelInsUnitPrice(wsInsurance.getTravelInsUnitPrice());
        insurance.setTravelInsuranceCount(wsInsurance.getTravelInsuranceCount());
        return insurance;
    }

    /**
     * 转换X产品信息
     * */
    public XProduct convertWSXProduct(XProductDetail wsXProduct, List<FlightOrigDestInfo> flightInfoList){
        if (wsXProduct == null){
            return null;
        }

        XProduct xProduct = new XProduct();

        //增值行李额
        if (wsXProduct.getValueAddedBaggageInfo() != null){
            List<PassengerBaggageDetailEntity> wsPassengerBaggageList = wsXProduct.getValueAddedBaggageInfo().getBaggageInfoList();
            if (wsPassengerBaggageList != null && !wsPassengerBaggageList.isEmpty()){
                ValueAddedBaggageInfo valueAddedBaggageInfo = new ValueAddedBaggageInfo();
                List<PassengerValueAddedBaggage> passengerValueAddedBaggageList = new ArrayList<>();
                valueAddedBaggageInfo.setBaggageInfoList(passengerValueAddedBaggageList);
                for (PassengerBaggageDetailEntity wsPassengerBaggage : wsPassengerBaggageList){
                    passengerValueAddedBaggageList.add(convertWSPassengerValueAddedBaggage(wsPassengerBaggage));
                }
                valueAddedBaggageInfo.setSegmentValueAddedBaggageList(convertSegmentValueAddedBaggages(wsXProduct.getValueAddedBaggageInfo().getBaggageInfoList(),flightInfoList));
                xProduct.setPassengerValueAddedBaggage(valueAddedBaggageInfo);
            }
        }

        //休息室
        if (wsXProduct.getAirportLoungeInfo() != null){
            List<AirportLoungeEntity> wsAirportLoungeList = wsXProduct.getAirportLoungeInfo().getAirportLoungeList();
            if (wsAirportLoungeList != null && !wsAirportLoungeList.isEmpty()){
                List<AirportLounge> airportLoungeList = new ArrayList<>();
                for (AirportLoungeEntity wsAirportLounge : wsAirportLoungeList){
                    airportLoungeList.add(convertWSAirportLounge(wsAirportLounge));
                }
                xProduct.setAirportLoungeList(airportLoungeList);
            }
        }

        //X产品酒店优惠券
        if (wsXProduct.getXCouponProductInfo() != null){
            List<XCouponProductEntity> wsXCouponList = wsXProduct.getXCouponProductInfo().getXCouponProductList();
            if (wsXCouponList != null && !wsXCouponList.isEmpty()){
                List<XCoupon> xCouponList = new ArrayList<>();
                for (XCouponProductEntity wsXCoupon : wsXCouponList){
                    xCouponList.add(convertWSXCoupon(wsXCoupon));
                }
                xProduct.setxCouponList(xCouponList);
            }
        }

        //X产品值机信息
        if (wsXProduct.getXCheckInInfoList() != null && wsXProduct.getXCheckInInfoList().size() > 0){
            List<XCheckIn> xCheckInList = new ArrayList<>();
            for (XCheckInEntity wsXCheckIn : wsXProduct.getXCheckInInfoList()){
                xCheckInList.add(convertWSXCheckIn(wsXCheckIn));
            }
            xProduct.setxCheckInList(xCheckInList);
        }
        return xProduct;
    }

    public FormatBaggageInfo convertWSFormatBaggageInfo(com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.flightcommon.v1.FormatBaggageInfo wsFormatBaggageInfo){

        if (wsFormatBaggageInfo == null){
            return null;
        }

        FormatBaggageInfo formatBaggageInfo = new FormatBaggageInfo();
        formatBaggageInfo.setAdultPiece(wsFormatBaggageInfo.getAdultPiece());
        formatBaggageInfo.setAdultWeight(wsFormatBaggageInfo.getAdultWeight());
        formatBaggageInfo.setChildPiece(wsFormatBaggageInfo.getChildPiece());
        formatBaggageInfo.setChildWeight(wsFormatBaggageInfo.getChildWeight());
        formatBaggageInfo.setInfantPiece(wsFormatBaggageInfo.getInfantPiece());
        formatBaggageInfo.setInfantWeight(wsFormatBaggageInfo.getInfantWeight());
        formatBaggageInfo.setSequenceNo(wsFormatBaggageInfo.getSequence());
        formatBaggageInfo.setSequenceNote(wsFormatBaggageInfo.getSequenceNote());
        formatBaggageInfo.setDesc(wsFormatBaggageInfo.getAdultDescription());

        return formatBaggageInfo;
    }

    private XCheckIn convertWSXCheckIn(XCheckInEntity wsXCheckIn){
        if (wsXCheckIn == null){
            return null;
        }
        XCheckIn xCheckIn = new XCheckIn();
        xCheckIn.setProductOrderId(wsXCheckIn.getProductOrderID());
        xCheckIn.setCheckInOrderStatus(wsXCheckIn.getCheckInOrderStatus());
        xCheckIn.setCheckInStartTime(DateUtil.longToDate(wsXCheckIn.getCheckInStartTime()*1000));
        xCheckIn.setSegmentNo(wsXCheckIn.getSegmentNo());
        xCheckIn.setSequenceNo(wsXCheckIn.getSequence());

        return xCheckIn;
    }

    private List<SegmentValueAddedBaggage> convertSegmentValueAddedBaggages(List<PassengerBaggageDetailEntity> baggageInfoList, List<FlightOrigDestInfo> flightInfoList) {
        if (baggageInfoList == null)
            return null;
        List<SegmentValueAddedBaggage> list = new ArrayList<>();
        Map<Long, SegmentValueAddedBaggage> map = new HashMap<>();
        for (PassengerBaggageDetailEntity baggage : baggageInfoList) {
            for (ValueAddedBaggageDetailEntity selectedBaggage : baggage.getSelectedBaggageList()) {
                SegmentValueAddedBaggage value = map.get(selectedBaggage.getProductOrderID());
                if (value == null) {
                    SegmentValueAddedBaggage item = new SegmentValueAddedBaggage();
                    item.setProductOrderID(selectedBaggage.getProductOrderID());
                    item.setBaggageStatus(selectedBaggage.getBaggageStatus());
                    FlightOrigDestInfo flightOrigDestInfo  = flightInfoList.stream().filter(p->p.getOriNo() == selectedBaggage.getSequenceNo()).findFirst().get();
                    item.setSegmentValueAddedBaggageDetailList(getSegmentValueAddedBaggageDetail(selectedBaggage, baggage ,flightOrigDestInfo));
                    list.add(item);
                    map.put(selectedBaggage.getProductOrderID(), item);
                } else {
                    boolean exist = false;
                    for (SegmentValueAddedBaggageDetail detail: value.getSegmentValueAddedBaggageDetailList()) {
                        if(detail.getSequenceNo()==selectedBaggage.getSequenceNo()){
                            SegmentValueAddedBaggagePassengerDetail passengerDetail = new SegmentValueAddedBaggagePassengerDetail();
                            passengerDetail.setPassengerName(baggage.getPassengerName());
                            passengerDetail.setxProductPriceInfo(convertXProdictPrice(selectedBaggage.getPriceInfoList().get(selectedBaggage.getPriceInfoList().size()-1)));
                            passengerDetail.setxProductWeightInfo(convertXProductWeight(selectedBaggage.getWeightInfoList().get(0)));
                            detail.getSegmentValueAddedBaggagePassengerDetailList().add(passengerDetail);
                            exist = true;
                            break;
                        }
                    }
                    if(!exist) {
                        value.getSegmentValueAddedBaggageDetailList().add(convertAddedBaggageDetailForAdd(selectedBaggage, baggage));
                    }
                }
            }
        }

        setTotalAmount(list);

        return list;
    }

    private void setTotalAmount(List<SegmentValueAddedBaggage> list) {
        for (SegmentValueAddedBaggage item: list) {
            BigDecimal totalAmount = BigDecimal.ZERO;
            for (SegmentValueAddedBaggageDetail detail : item.getSegmentValueAddedBaggageDetailList()) {
                for (SegmentValueAddedBaggagePassengerDetail passengerDetail : detail.getSegmentValueAddedBaggagePassengerDetailList()) {
                    totalAmount = totalAmount.add(passengerDetail.getxProductPriceInfo().getSalePrice());
                    if (item.getCurrency() == null)
                        item.setCurrency(passengerDetail.getxProductPriceInfo().getCurrency());
                }
            }
            item.setTotalAmount(totalAmount);
        }
    }

    private SegmentValueAddedBaggageDetail convertAddedBaggageDetailForAdd(ValueAddedBaggageDetailEntity selectedBaggage, PassengerBaggageDetailEntity baggage) {
        if (selectedBaggage == null)
            return null;

        SegmentValueAddedBaggageDetail baggageDetail = new SegmentValueAddedBaggageDetail();
        baggageDetail.setSequenceNo(selectedBaggage.getSequenceNo());

        List<SegmentValueAddedBaggagePassengerDetail> details = new ArrayList<>();
        SegmentValueAddedBaggagePassengerDetail detail = new SegmentValueAddedBaggagePassengerDetail();
        detail.setPassengerName(baggage.getPassengerName());
        detail.setxProductPriceInfo(convertXProdictPrice(selectedBaggage.getPriceInfoList().get(selectedBaggage.getPriceInfoList().size()-1)));
        detail.setxProductWeightInfo(convertXProductWeight(selectedBaggage.getWeightInfoList().get(0)));
        details.add(detail);
        baggageDetail.setSegmentValueAddedBaggagePassengerDetailList(details);
        return baggageDetail;
    }

    private List<SegmentValueAddedBaggageDetail> getSegmentValueAddedBaggageDetail(ValueAddedBaggageDetailEntity selectedBaggage, PassengerBaggageDetailEntity baggage, FlightOrigDestInfo flightOrigDestInfo) {
        if (selectedBaggage == null)
            return  null;

        List<SegmentValueAddedBaggageDetail> list = new ArrayList<>();
        SegmentValueAddedBaggageDetail item = new SegmentValueAddedBaggageDetail();
        item.setSequenceNo(selectedBaggage.getSequenceNo());
        item.setdCityName(flightOrigDestInfo.getDCity().getName());
        item.setaCityName(flightOrigDestInfo.getACity().getName());
        List<SegmentValueAddedBaggagePassengerDetail> details = new ArrayList<>();
        SegmentValueAddedBaggagePassengerDetail detail = new SegmentValueAddedBaggagePassengerDetail();
        detail.setPassengerName(baggage.getPassengerName());
        detail.setxProductPriceInfo(convertXProdictPrice(selectedBaggage.getPriceInfoList().get(selectedBaggage.getPriceInfoList().size()-1)));
        detail.setxProductWeightInfo(convertXProductWeight(selectedBaggage.getWeightInfoList().get(0)));
        details.add(detail);
        item.setSegmentValueAddedBaggagePassengerDetailList(details);
        list.add(item);

        return list;
    }

    private XProductWeightInfo convertXProductWeight(BaggageWeightEntity baggageWeightEntity) {
        if (baggageWeightEntity == null)
            return  null;

        XProductWeightInfo item = new XProductWeightInfo();
        item.setWeight(baggageWeightEntity.getWeight());
        item.setWeightUnits(baggageWeightEntity.getWeightUnits());
        item.setChargeWeight(baggageWeightEntity.getChargeWeight());
        item.setFreeWeight(baggageWeightEntity.getFreeWeight());
        item.setWeightExchangeRate(baggageWeightEntity.getWeightExchangeRate());
        return item;
    }

    private XProductPriceInfo convertXProdictPrice(BaggagePriceEntity baggagePriceEntity) {
        if (baggagePriceEntity == null)
            return null;

        XProductPriceInfo item = new XProductPriceInfo();
        item.setCurrency(baggagePriceEntity.getCurrency());
        item.setSalePrice(baggagePriceEntity.getSalePrice());
        return item;
    }

    /**
     * 转换乘机人及购买的行李额信息
     * */
    public PassengerValueAddedBaggage convertWSPassengerValueAddedBaggage(PassengerBaggageDetailEntity wsPassengerValueAddedBaggage){
        if (wsPassengerValueAddedBaggage == null){
            return null;
        }

        PassengerValueAddedBaggage passengerValueAddedBaggage = new PassengerValueAddedBaggage();
        passengerValueAddedBaggage.setPassengerName(wsPassengerValueAddedBaggage.getPassengerName());
        passengerValueAddedBaggage.setValueAddedBaggageList(CommonUtil.convertList(wsPassengerValueAddedBaggage.getSelectedBaggageList(), valueAddedBaggageDetailEntity -> convertWSValueAddedBaggage(valueAddedBaggageDetailEntity)));

        return passengerValueAddedBaggage;
    }

    /**
     * 转换增值行李额服务
     * */
    public ValueAddedBaggage convertWSValueAddedBaggage(ValueAddedBaggageDetailEntity wsValueAddedBaggage){
        if (wsValueAddedBaggage == null){
            return null;
        }
        ValueAddedBaggage valueAddedBaggage = new ValueAddedBaggage();
        valueAddedBaggage.setSequenceNo(wsValueAddedBaggage.getSequenceNo());
        valueAddedBaggage.setSegmentNo(wsValueAddedBaggage.getSegmentNo());
        valueAddedBaggage.setProductOrderID(wsValueAddedBaggage.getProductOrderID());
        valueAddedBaggage.setBaggageStatus(wsValueAddedBaggage.getBaggageStatus());
        valueAddedBaggage.setPkgNumber(wsValueAddedBaggage.getPkgNumber());
        valueAddedBaggage.setPriceInfoList(convertPriceInfo(wsValueAddedBaggage.getPriceInfoList()));
        valueAddedBaggage.setWeightInfoList(convertWeightInfo(wsValueAddedBaggage.getWeightInfoList()));
        return valueAddedBaggage;
    }

    private List<XProductWeightInfo> convertWeightInfo(List<BaggageWeightEntity> weightInfoList) {
        if(weightInfoList == null)
            return null;

        List<XProductWeightInfo> list = new ArrayList<>();
        for (BaggageWeightEntity weightEntity : weightInfoList
                ) {
            XProductWeightInfo item = new XProductWeightInfo();
            item.setWeightUnits(weightEntity.getWeightUnits());
            item.setWeightExchangeRate(weightEntity.getWeightExchangeRate());
            item.setWeight(weightEntity.getWeight());
            item.setFreeWeight(weightEntity.getFreeWeight());
            item.setChargeWeight(weightEntity.getChargeWeight());

            list.add(item);
        }
        return list;

    }

    private List<XProductPriceInfo> convertPriceInfo(List<BaggagePriceEntity> priceInfoList) {
        if (priceInfoList == null)
            return null;

        List<XProductPriceInfo> list = new ArrayList<>();
        for (BaggagePriceEntity baggagePrice : priceInfoList
                ) {
            XProductPriceInfo item = new XProductPriceInfo();
            item.setCurrency(baggagePrice.getCurrency());
            item.setSalePrice(baggagePrice.getSalePrice());
            list.add(item);
        }
        return list;
    }

    /**
     * 转换休息室
     * */
    public AirportLounge convertWSAirportLounge(AirportLoungeEntity wsAirportLounge){
        if (wsAirportLounge == null){
            return null;
        }

        AirportLounge airportLounge = new AirportLounge();
        airportLounge.setAirport(wsAirportLounge.getAirport());
        airportLounge.setBookingDate(DateUtil.gregorianCalendarToCalendar(wsAirportLounge.getBookingDate()));
        airportLounge.setBookingDateLong(wsAirportLounge.getBookingDateLong());
        airportLounge.setCanCancel(wsAirportLounge.isIsCanCancel());
        airportLounge.setCostPrice(wsAirportLounge.getCostPrice());
        airportLounge.setCreateTime(DateUtil.gregorianCalendarToCalendar(wsAirportLounge.getCreateTime()));
        airportLounge.setCreateTimeLong(wsAirportLounge.getCreateTimeLong());
        airportLounge.setDuration(wsAirportLounge.getDuration());
        airportLounge.setExpiryDate(DateUtil.gregorianCalendarToCalendar(wsAirportLounge.getExpiryDate()));
        airportLounge.setExpiryDateLong(wsAirportLounge.getExpiryDateLong());
        airportLounge.setFaciliesDescription(wsAirportLounge.getFaciliesDescription());
        airportLounge.setFlightAgency(wsAirportLounge.getFlightAgency());
        airportLounge.setId(wsAirportLounge.getID());
        airportLounge.setLocationInfo(wsAirportLounge.getLocationInfo());
        airportLounge.setLocationInfoEn(wsAirportLounge.getLocationInfoEn());
        airportLounge.setLoungeProductID(wsAirportLounge.getLoungeProductID());
        airportLounge.setWorkingTime(wsAirportLounge.getWorkingTime());
        airportLounge.setThirdPartOrderID(wsAirportLounge.getThirdPartOrderID());
        airportLounge.setShowCurrencyPrintPrice(wsAirportLounge.getShowCurrencyPrintPrice());
        airportLounge.setServices(wsAirportLounge.getServices());
        airportLounge.setSequence(wsAirportLounge.getSequence());
        airportLounge.setSalePrice(wsAirportLounge.getSalePrice());
        airportLounge.setRefundsInfoDescription(wsAirportLounge.getRefundsInfoDescription());
        airportLounge.setRefundInfoDescription(wsAirportLounge.getRefundInfoDescription());
        airportLounge.setRefsNote(wsAirportLounge.getRefsNote());
        airportLounge.setRefNote(wsAirportLounge.getRefNote());
        airportLounge.setQrStatus(wsAirportLounge.getQRStatus());
        airportLounge.setQrCode(wsAirportLounge.getQRCode());
        airportLounge.setProductNameEn(wsAirportLounge.getProductNameEn());
        airportLounge.setProductDescriptionEn(wsAirportLounge.getProductDescriptionEn());
        airportLounge.setProductCount(wsAirportLounge.getProductCount());
        airportLounge.setPrintPrice(wsAirportLounge.getPrintPrice());
        airportLounge.setPictureInfo(wsAirportLounge.getPictureInfo());
        airportLounge.setPassengerName(wsAirportLounge.getPassengerName());
        airportLounge.setPassagerName(wsAirportLounge.getPassagerName());
        airportLounge.setOrderStatusDescription(wsAirportLounge.getOrderStatusDescription());
        airportLounge.setOrderID(wsAirportLounge.getOrderID());
        airportLounge.setLoungesType(wsAirportLounge.getLoungesType());
        return airportLounge;
    }

    /**
     * 转换X产品酒店优惠券
     * */
    public XCoupon convertWSXCoupon(XCouponProductEntity wsXCoupon){
        if (wsXCoupon == null){
            return null;
        }

        XCoupon xCoupon = new XCoupon();
        xCoupon.setStatus(wsXCoupon.getStatus());
        xCoupon.setSequence(wsXCoupon.getSequence());
        xCoupon.setSalePrice(wsXCoupon.getSalePrice());
        xCoupon.setRuleId(wsXCoupon.getRuleId());
        xCoupon.setRefundInfo(wsXCoupon.getRefundInfo());
        xCoupon.setProductType(wsXCoupon.getProductType());
        xCoupon.setProductName(wsXCoupon.getProductName());
        xCoupon.setProductCount(wsXCoupon.getProductCount());
        xCoupon.setPrintPrice(wsXCoupon.getPrintPrice());
        xCoupon.setPassengerName(wsXCoupon.getPassengerName());
        xCoupon.setPassengerCount(wsXCoupon.getPassengerCount());
        xCoupon.setCouponType(wsXCoupon.getCouponType());
        xCoupon.setCouponDesc(wsXCoupon.getCouponDesc());
        xCoupon.setCategory(wsXCoupon.getCategory());
        return xCoupon;
    }

    /**
     * 转换乘客类型
     * */
    public PassengerType convertWSPassengerType(GaPassengerType wsPassenterType){
        switch (wsPassenterType){
            case ADT:
                return PassengerType.ADT;
            case CHD:
                return PassengerType.CHD;
            case INF:
                return PassengerType.INF;
            default:
                return PassengerType.UNKNOWN;
        }
    }

    /**
     * 转换订单支付类型
     * @param payType 订单支付类型
     * */
    public PayMethodEnum convertWSPayType(PayType payType){
        switch (payType){
            case CASH:
                return PayMethodEnum.CASH;
            case DQPAY:
                return PayMethodEnum.DQPAY;
            case PAYPAL:
                return PayMethodEnum.PAYPAL;
            case CREDITCARD:
                return PayMethodEnum.CREDITCARD;
            case DEPOSITCARD:
                return PayMethodEnum.DEPOSITCARD;
            default:
                return PayMethodEnum.NA;
        }
    }

    /**
     * 获取关联订单
     * @param wsOrderDetail 订单详情
     * */
    public List<OrderSummary> getRelatedOrderInfoList(OrderDetailInfo wsOrderDetail){
        List<OrderSummary> relatedOrderInfoList = new ArrayList<>();

        if (wsOrderDetail == null){
            return relatedOrderInfoList;
        }

        Long mainOrderId = wsOrderDetail.getOrderID();

        //聚合三个关联订单相关节点
        List<RelatedOrderInfoEntity> wsRelatedOrderInfoList = wsOrderDetail.getRelatedOrderInfoList();
        List<Long> wsRelatedOrderIdList = wsOrderDetail.getRelationOrderIDList();
        List<Long> wsAssociateOrderIdList = wsOrderDetail.getAssociateOrderID();

        //第一个关联订单节点
        if (wsRelatedOrderInfoList != null && !wsRelatedOrderInfoList.isEmpty()){
            for (RelatedOrderInfoEntity relatedOrderInfoEntity : wsRelatedOrderInfoList){
                if (!mainOrderId.equals(relatedOrderInfoEntity.getRelatedOrderId())){
                    OrderSummary relatedOrderInfo = new OrderSummary();
                    relatedOrderInfo.setOrderId(relatedOrderInfoEntity.getRelatedOrderId());
                    relatedOrderInfo.setActualOrderStatus(relatedOrderInfoEntity.getActualOrderStatus());
                    relatedOrderInfo.setOrderType(relatedOrderInfoEntity.getRelatedOrderType());
                    List<FlightWayInfo> flightWayList = relatedOrderInfoEntity.getFlightWayInfoList();
                    if (flightWayList != null && !flightWayList.isEmpty()){
                        for (FlightWayInfo flightWay : flightWayList) {
                            String cityDesc = String.format("%s - %s",flightWay.getDCityName(),flightWay.getACityName());
                            if (StringUtils.isBlank(relatedOrderInfo.getFlightWayInfo())){
                                relatedOrderInfo.setFlightWayInfo(cityDesc);
                            } else {
                                if (StringUtils.isNotBlank(cityDesc)){
                                    relatedOrderInfo.setFlightWayInfo(String.format("%s,%s%s",relatedOrderInfo.getFlightWayInfo(),",",cityDesc));
                                }
                            }
                        }
                    }
//                    String relatedOrderType = String.format("(%s)",relatedOrderInfoEntity.getRelatedOrderType());
//                    if (StringUtils.isBlank(relatedOrderInfo.getFlightWayInfo())){
//                        relatedOrderInfo.setFlightWayInfo(relatedOrderType);
//                    } else {
//                        if (StringUtils.isNotBlank(relatedOrderType)){
//                            relatedOrderInfo.setFlightWayInfo(String.format("%s%s",relatedOrderInfo.getFlightWayInfo(),relatedOrderType));
//                        }
//                    }
                    addRelatedOrderInfo(relatedOrderInfoList,relatedOrderInfo);
                }
            }
        }

        //第二个关联订单节点
        if (wsRelatedOrderIdList != null && !wsRelatedOrderIdList.isEmpty()){
            for (Long relatedOrderId : wsRelatedOrderIdList){
                if (!mainOrderId.equals(relatedOrderId)){
                    OrderSummary relatedOrderInfo = new OrderSummary();
                    relatedOrderInfo.setOrderId(relatedOrderId);
                    addRelatedOrderInfo(relatedOrderInfoList,relatedOrderInfo);
                }
            }
        }

        //第三个关联订单节点
        if (wsAssociateOrderIdList != null && !wsAssociateOrderIdList.isEmpty()){
            for (Long relatedOrderId : wsAssociateOrderIdList){
                if (!mainOrderId.equals(relatedOrderId)){
                    OrderSummary relatedOrderInfo = new OrderSummary();
                    relatedOrderInfo.setOrderId(relatedOrderId);
                    addRelatedOrderInfo(relatedOrderInfoList,relatedOrderInfo);
                }
            }
        }

        return relatedOrderInfoList;
    }

    /**
     * 转换航班信息
     * */
    public FlightInfo convertWSFlightInfo(FlightOrigDestInfo wsFlightInfo) {

        if (wsFlightInfo == null){
            return null;
        }

        FlightInfo flightInfo = new FlightInfo();
        flightInfo.setdDate(DateUtil.longToDate(wsFlightInfo.getDDate()*1000));
        flightInfo.setaDate(DateUtil.longToDate(wsFlightInfo.getADate()*1000));
        flightInfo.setdCity(convertWSCityInfo(wsFlightInfo.getDCity()));
        flightInfo.setaCity(convertWSCityInfo(wsFlightInfo.getACity()));
        flightInfo.setCrossDays(wsFlightInfo.getArrivalDays());
        flightInfo.setDuration(wsFlightInfo.getDuration());
        flightInfo.setSegmentNo(wsFlightInfo.getOriNo());
        flightInfo.setColumnLayoverDetailList(new ArrayList<>());

        List<FlightColumn> flightColumnList = new ArrayList<>();
        List<DetailColunmInfo> wsColumnList = wsFlightInfo.getColunmList();
        if (wsColumnList != null && !wsColumnList.isEmpty()){
            List<ColumnLayoverDetail> columnLayoverDetailList = new ArrayList<>();

            //前一个航段
            DetailColunmInfo preColumn = null;

            for (DetailColunmInfo wsColumn : wsColumnList){
                FlightColumn column = new FlightColumn();
                column.setSegmentNo(wsColumn.getOriNo());
                column.setSequenceNo(wsColumn.getSequence());
                column.setAirline(convertWSAirlineInfo(wsColumn.getAirLine()));
                column.setFlightNo(wsColumn.getFligntNo());
                column.setCarrierAirline(wsColumn.getCarrierAirLine());
                column.setCarrierFlightNo(wsColumn.getCarrierFligntNo());
                column.setClassName(wsColumn.getClassName());
                column.setdDate(DateUtil.longToDate(wsColumn.getDDate()*1000));
                column.setaDate(DateUtil.longToDate(wsColumn.getADate()*1000));
                column.setdCity(convertWSCityInfo(wsColumn.getDCity()));
                column.setaCity(convertWSCityInfo(wsColumn.getACity()));
                column.setdPort(convertWSAirportInfo(wsColumn.getDPort()));
                column.setaPort(convertWSAirportInfo(wsColumn.getAPort()));
                column.setdTerminal(convertWSTerminalInfo(wsColumn.getDTerminal()));
                column.setaTerminal(convertWSTerminalInfo(wsColumn.getATerminal()));

                //经停信息
                if (wsColumn.getFlightStopInfoList() != null && wsColumn.getFlightStopInfoList().size() > 0){
                    column.setFlightStopInfoList(new ArrayList<>());
                    for (com.ctrip.ibu.flight.commonservice.soaadapter.flightapi.contract.api.flightcommon.v1.FlightStopInfo wsStopInfo : wsColumn.getFlightStopInfoList()){
                        column.getFlightStopInfoList().add(convertWSFlightStopInfo(wsStopInfo));
                    }
                }

                flightColumnList.add(column);

                if (preColumn != null){
                    columnLayoverDetailList.add(getColumnLayoverDetail(wsColumn,preColumn));
                }

                preColumn = wsColumn;
            }
            flightInfo.setColumnLayoverDetailList(columnLayoverDetailList);
        }
        flightInfo.setColumnList(flightColumnList);

        return flightInfo;
    }

    /**
     * 获取航段间隔时间信息
     * @param curColumn 当前航段
     * @param preColumn 前一航段
     * */
    private ColumnLayoverDetail getColumnLayoverDetail(DetailColunmInfo curColumn,DetailColunmInfo preColumn){
        if (curColumn == null || preColumn == null){
            return null;
        }

        ColumnLayoverDetail columnLayoverDetail = new ColumnLayoverDetail();
        columnLayoverDetail.setCurColumnSegmentNo(curColumn.getOriNo());
        columnLayoverDetail.setCurColumnSequenceNo(curColumn.getSequence());
        columnLayoverDetail.setPreColumnSegmentNo(preColumn.getOriNo());
        columnLayoverDetail.setPreColumnSequenceNo(preColumn.getSequence());

        long layover = curColumn.getDDate() - preColumn.getADate();
        if (layover > 0){
            columnLayoverDetail.setHourLayover(Integer.parseInt(String.valueOf(layover/(60*60))));
            columnLayoverDetail.setMinuteLayover(Integer.parseInt(String.valueOf(layover/60%60)));
        }
        return columnLayoverDetail;
    }

    //添加一个关联订单信息到关联订单列表
    private void addRelatedOrderInfo(List<OrderSummary> relatedOrderInfoList,OrderSummary relatedOrderInfo){
        if (relatedOrderInfo != null && relatedOrderInfo.getOrderId() >= 0){

            if (relatedOrderInfoList == null || relatedOrderInfoList.isEmpty()){
                if (relatedOrderInfoList == null){
                    relatedOrderInfoList = new ArrayList<>();
                }
                relatedOrderInfoList.add(relatedOrderInfo);
            }else {
                boolean isExist = false;
                for (OrderSummary relatedOrderSummary : relatedOrderInfoList){
                    if (relatedOrderInfo.getOrderId().equals(relatedOrderSummary.getOrderId())){
                        isExist = true;
                        break;
                    }
                }
                if (!isExist){
                    relatedOrderInfoList.add(relatedOrderInfo);
                }
            }
        }
    }

    /**
     * 转换WS层Offline增值行李额信息
     */
    public OfflineBaggageInfo convertWSOfflineBaggageInfo(BaggagesInfoEntity wsBaggageInfo) {

        if (wsBaggageInfo == null){
            return null;
        }

        OfflineBaggageInfo baggageInfo = new OfflineBaggageInfo();

        baggageInfo.setTotalNumber(wsBaggageInfo.getQuantity());//总件数
        baggageInfo.setTotalNumber(wsBaggageInfo.getQuantity());
        baggageInfo.setNumber(wsBaggageInfo.getNumber());
        baggageInfo.setSpecification(wsBaggageInfo.getSpecification());
        baggageInfo.setPassengerNameList(CommonUtil.convertList(wsBaggageInfo.getPassengerName(), passengerName -> passengerName));

        return baggageInfo;
    }

    /**
     * 转换WS层Offline餐食信息
     */
    public OfflineMealsInfo convertWSOfflineMealsInfo(MealsInfoEntity wsMealsInfo) {
        OfflineMealsInfo mealsInfo = new OfflineMealsInfo();
        if (wsMealsInfo != null) {
            mealsInfo.setNumber(wsMealsInfo.getNumber());
            mealsInfo.setRemarker(wsMealsInfo.getRemark());
            mealsInfo.setSpecification(wsMealsInfo.getSpecification());
            mealsInfo.setPassengerNameList(CommonUtil.convertList(wsMealsInfo.getPassengerName(), passengerName -> passengerName));
        }
        return mealsInfo;
    }

    /**
     * 转换WS层Offline姓名更改服务信息
     */
    public OfflineNameModifyInfo convertWSOfflineNameModifyInfo(NameModifyInfoEntity wsNameModifyInfo) {
        OfflineNameModifyInfo nameModifyInfo = new OfflineNameModifyInfo();
        if (wsNameModifyInfo != null) {
            nameModifyInfo.setOriginalName(wsNameModifyInfo.getOriginalName());
            nameModifyInfo.setModifiedName(wsNameModifyInfo.getModifiedName());
        }
        return nameModifyInfo;
    }

    /**
     * 转换WS层Offline航班Column信息
     */
    public FlightColumn convertWSOfflineFlightColumnInfo(FlightWayEntity wsFlightColumnInfo) {
        FlightColumn flightColumn = new FlightColumn();
        if (wsFlightColumnInfo != null) {
            flightColumn.setFlightNo(wsFlightColumnInfo.getFlightNumber());

            City dCity = new City();
            dCity.setName(wsFlightColumnInfo.getDCityName());
            dCity.setCode(wsFlightColumnInfo.getDCityCode());
            flightColumn.setdCity(dCity);

            City aCity = new City();
            aCity.setName(wsFlightColumnInfo.getACityName());
            aCity.setCode(wsFlightColumnInfo.getACityCode());
            flightColumn.setaCity(aCity);
        }
        return flightColumn;
    }

    /**
     * 转换WS层Offline证件信息修改服务信息
     */
    public OfflinePaperModifyInfo convertWSOfflinePaperModifyInfo(PaperModifyInfoEntity wsPaperModifyInfo) {
        OfflinePaperModifyInfo paperModifyInfo = new OfflinePaperModifyInfo();
        if (wsPaperModifyInfo != null) {
            paperModifyInfo.setPassengerName(wsPaperModifyInfo.getPassengerName());
            paperModifyInfo.setModifiedPaperInfo(wsPaperModifyInfo.getModifiedPapersInfo());
            paperModifyInfo.setModifiedPaperType(wsPaperModifyInfo.getModifiedPapersType());
            paperModifyInfo.setOriginalPaperInfo(wsPaperModifyInfo.getOriginalPapersInfo());
            paperModifyInfo.setOriginalPaperType(wsPaperModifyInfo.getOriginalPapersType());
        }
        return paperModifyInfo;
    }

    /**
     * 转换WS层选座服务信息
     */
    public OfflineCheckSeatInfo convertWSOfflineCheckSeatInfo(CheckInInfoEntity wsCheckSeatInfo) {
        OfflineCheckSeatInfo checkSeatInfo = new OfflineCheckSeatInfo();
        if (wsCheckSeatInfo != null) {
            checkSeatInfo.setPassengerName(wsCheckSeatInfo.getPassengerName());
            checkSeatInfo.setPredilection(wsCheckSeatInfo.getPredilection());
            checkSeatInfo.setSeatNumber(wsCheckSeatInfo.getSeatNumber());
            checkSeatInfo.setPositionOptionList(CommonUtil.convertList(wsCheckSeatInfo.getPositionOption(), positionOption -> positionOption));
        }
        return checkSeatInfo;
    }

    /**
     * 转换WS层Offline特殊服务信息
     */
    public OfflineSpecialServiceInfo convertWSOfflineSpecialInfo(SpecialInfoEntity wsSpecialInfo) {
        OfflineSpecialServiceInfo specialServiceInfo = new OfflineSpecialServiceInfo();
        if (wsSpecialInfo != null) {
            specialServiceInfo.setNumber(wsSpecialInfo.getNumber());
            specialServiceInfo.setServiceInfo(wsSpecialInfo.getServiceInfomation());
            specialServiceInfo.setPassengerNameList(CommonUtil.convertList(wsSpecialInfo.getPassengerName(), passengerName -> passengerName));
        }
        return specialServiceInfo;
    }


    /**
     * 获取订单详情乘机人信息（聚合基本信息和）
     * @param wsPassengerInfo 乘机人信息
     * @param wsFlightInfoList 航班信息(存在乘机人票号信息)
     * */
    public Passenger getPassengerInfoList(GaPassengerInfo wsPassengerInfo, List<FlightOrigDestInfo> wsFlightInfoList){

        if (wsPassengerInfo == null){
            return null;
        }

        Passenger passenger = convertWSPassengerInfo(wsPassengerInfo);
        passenger.setTicketNoInfoList(getPassengerTicketNoInfoListByPassengerName(wsPassengerInfo.getName(),wsFlightInfoList));
        return passenger;
    }


    /**
     * 根据乘机人姓名，从航班中获取票号信息
     * @param passengerName 乘机人姓名
     * @param wsFlightInfoList 航班信息
     * */
    public List<TicketNoInfo> getPassengerTicketNoInfoListByPassengerName(String passengerName, List<FlightOrigDestInfo> wsFlightInfoList){
        if (StringUtils.isEmpty(passengerName) || wsFlightInfoList == null || wsFlightInfoList.isEmpty()){
            return null;
        }

        List<TicketNoInfo> ticketNoInfoList = new ArrayList<>();

        for (FlightOrigDestInfo wsFlightInfo : wsFlightInfoList) {
            List<GaTicketNoInfo> wsTicketNoList = wsFlightInfo.getTicketNoInfoList();
            if (wsTicketNoList != null && !wsTicketNoList.isEmpty()){
                for (GaTicketNoInfo wsTicketNoInfo : wsTicketNoList) {
                    if (passengerName.equals(wsTicketNoInfo.getPassengerName())){
                        TicketNoInfo ticketNoInfo = new TicketNoInfo();
                        ticketNoInfo.setTicketNo(wsTicketNoInfo.getTicketNo());
                        ticketNoInfo.setTicketNoStatus(wsTicketNoInfo.getStatus());
                        ticketNoInfo.setTicketNoStatusDesc(wsTicketNoInfo.getStatusDesc());

                        ticketNoInfoList.add(ticketNoInfo);
                    }
                }
            }
        }
        return ticketNoInfoList;
    }
}
