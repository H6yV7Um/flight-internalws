package com.ctrip.ibu.flight.internalws.contract.commontypes;

public enum LanguageType {

    unspecified(0),
    en_US(1),
    en_HK(2),
    zh_HK(3),
    zh_TW(4),
    zh_CN(5),
    ja_JP(6),
    ko_KR(7),
    fr_FR(8),
    en_SG(9),
    de_DE(10),
    ru_RU(11),
    es_ES(12),
    ms_MY(13),
    th_TH(14),
    id_ID(15),
    en_AU(16),
    vi_VN(17),
    tl_PH(18),
    it_IT(19);

    private final int value;

    LanguageType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static LanguageType findByValue(int value) {
        switch (value) {
            case 0:
                return unspecified;
            case 1:
                return en_US;
            case 2:
                return en_HK;
            case 3:
                return zh_HK;
            case 4:
                return zh_TW;
            case 5:
                return zh_CN;
            case 6:
                return ja_JP;
            case 7:
                return ko_KR;
            case 8:
                return fr_FR;
            case 9:
                return en_SG;
            case 10:
                return de_DE;
            case 11:
                return ru_RU;
            case 12:
                return es_ES;
            case 13:
                return ms_MY;
            case 14:
                return th_TH;
            case 15:
                return id_ID;
            case 16:
                return en_AU;
            case 17:
                return vi_VN;
            case 18:
                return tl_PH;
            case 19:
                return it_IT;
            default:
                return null;
        }
    }
}
