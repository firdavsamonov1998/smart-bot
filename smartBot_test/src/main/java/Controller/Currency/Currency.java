package Controller.Currency;

public class Currency {

    private String ccy;
    private String ccyNm_EN;
    private Integer nominal;
    private Double rate;
    private Double diff;
    private String date;


    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getCcyNm_EN() {
        return ccyNm_EN;
    }

    public void setCcyNm_EN(String ccyNm_EN) {
        this.ccyNm_EN = ccyNm_EN;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getDiff() {
        return diff;
    }

    public void setDiff(Double diff) {
        this.diff = diff;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
