package com.kitri.dto;
public class ExchangeRate {
    /*
RESULT	Integer	조회 결과	1 : 성공, 2 : DATA코드 오류, 3 : 인증코드 오류, 4 : 일일제한횟수 마감
CUR_UNIT	String	통화코드	？
CUR_NM	String	국가/통화명	？
TTB	String	전신환(송금)
받으실때	？
TTS	String	전신환(송금)
보내실때	？
DEAL_BAS_R	String	매매 기준율	？
BKPR	String	장부가격	？
YY_EFEE_R	String	년환가료율	？
TEN_DD_EFEE_R	String	10일환가료율	？
KFTC_DEAL_BAS_R	String	서울외국환중계매매기준율	?
KFTC_BKPR	String	서울외국환중계 장부가격
     */


    private int result;	//조회 결과	1 : 성공, 2 : DATA코드 오류, 3 : 인증코드 오류, 4 : 일일제한횟수 마감
    private String cur_unit;	//통화코드	？
    private String cur_nm;		//국가/통화명	？
    private String ttb;//전신환(송금)받으실때	？
    private String tts;	//전신환(송금)보내실때	？
    private String deal_bas_r;	//매매 기준율	？
    private String bkpr;	//장부가격	？
    private String yy_efee_r;	//년환가료율	？
    private String ten_dd_efee_r;	//10일환가료율	？
    private String kftc_deal_bas_r;	//서울외국환중계    매매기준율	?
    private String kftc_bkpr;	//서울외국환중계장부가격

    public ExchangeRate() {
    }

    public ExchangeRate(int result, String cur_unit, String cur_nm, String ttb, String tts, String deal_bas_r, String bkpr, String yy_efee_r, String ten_dd_efee_r, String kftc_deal_bas_r, String kftc_bkpr) {
        this.result = result;
        this.cur_unit = cur_unit;
        this.cur_nm = cur_nm;
        this.ttb = ttb;
        this.tts = tts;
        this.deal_bas_r = deal_bas_r;
        this.bkpr = bkpr;
        this.yy_efee_r = yy_efee_r;
        this.ten_dd_efee_r = ten_dd_efee_r;
        this.kftc_deal_bas_r = kftc_deal_bas_r;
        this.kftc_bkpr = kftc_bkpr;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getCur_unit() {
        return cur_unit;
    }

    public void setCur_unit(String cur_unit) {
        this.cur_unit = cur_unit;
    }

    public String getCur_nm() {
        return cur_nm;
    }

    public void setCur_nm(String cur_nm) {
        this.cur_nm = cur_nm;
    }

    public String getTtb() {
        return ttb;
    }

    public void setTtb(String ttb) {
        this.ttb = ttb;
    }

    public String getTts() {
        return tts;
    }

    public void setTts(String tts) {
        this.tts = tts;
    }

    public String getDeal_bas_r() {
        return deal_bas_r;
    }

    public void setDeal_bas_r(String deal_bas_r) {
        this.deal_bas_r = deal_bas_r;
    }

    public String getBkpr() {
        return bkpr;
    }

    public void setBkpr(String bkpr) {
        this.bkpr = bkpr;
    }

    public String getYy_efee_r() {
        return yy_efee_r;
    }

    public void setYy_efee_r(String yy_efee_r) {
        this.yy_efee_r = yy_efee_r;
    }

    public String getTen_dd_efee_r() {
        return ten_dd_efee_r;
    }

    public void setTen_dd_efee_r(String ten_dd_efee_r) {
        this.ten_dd_efee_r = ten_dd_efee_r;
    }

    public String getKftc_deal_bas_r() {
        return kftc_deal_bas_r;
    }

    public void setKftc_deal_bas_r(String kftc_deal_bas_r) {
        this.kftc_deal_bas_r = kftc_deal_bas_r;
    }

    public String getKftc_bkpr() {
        return kftc_bkpr;
    }

    public void setKftc_bkpr(String kftc_bkpr) {
        this.kftc_bkpr = kftc_bkpr;
    }


    @Override
    public String toString() {
        return "ExchangeRate{" +
                "result=" + result +
                ", cur_unit='" + cur_unit + '\'' +
                ", cur_nm='" + cur_nm + '\'' +
                ", ttb='" + ttb + '\'' +
                ", tts='" + tts + '\'' +
                ", deal_bas_r='" + deal_bas_r + '\'' +
                ", bkpr='" + bkpr + '\'' +
                ", yy_efee_r='" + yy_efee_r + '\'' +
                ", ten_dd_efee_r='" + ten_dd_efee_r + '\'' +
                ", kftc_deal_bas_r='" + kftc_deal_bas_r + '\'' +
                ", kftc_bkpr='" + kftc_bkpr + '\'' +
                '}';
    }
}
