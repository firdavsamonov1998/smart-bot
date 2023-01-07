package Controller.UniversityManager;

import org.json.JSONArray;

public class University {

    private String alpha ;
    private String country ;
    private String name;
    private JSONArray we_pages ;
    private JSONArray domains ;


    public String getAlpha() {
        return alpha;
    }

    public void setAlpha(String alpha) {
        this.alpha = alpha;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONArray getWe_pages() {
        return we_pages;
    }

    public void setWe_pages(JSONArray we_pages) {
        this.we_pages = we_pages;
    }

    public JSONArray getDomains() {
        return domains;
    }

    public void setDomains(JSONArray domains) {
        this.domains = domains;
    }
}
