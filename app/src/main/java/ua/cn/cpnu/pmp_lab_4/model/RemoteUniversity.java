package ua.cn.cpnu.pmp_lab_4.model;

import com.google.gson.annotations.SerializedName;

public class RemoteUniversity {

    @SerializedName("identifier")
    private String alphaTwoCode;
    private String country;
    private String stateProvince;
    private String[] domains;
    private String name;
    private String[] webPages;

    public String getAlphaTwoCode() {
        return alphaTwoCode;
    }

    public String getCountry() {
        return country;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public String[] getDomains() {
        return domains;
    }

    public String getName() {
        return name;
    }

    public String[] getWebPages() {
        return webPages;
    }
}
