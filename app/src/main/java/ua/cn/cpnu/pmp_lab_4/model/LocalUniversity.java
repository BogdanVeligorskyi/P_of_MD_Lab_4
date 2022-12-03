package ua.cn.cpnu.pmp_lab_4.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import androidx.annotation.NonNull;

@Entity(tableName = "universities", indices = {@Index("remote_id")})
public class LocalUniversity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "identifier")
    String identifier;
    @ColumnInfo(name = "alpha_two_code")
    String alphaTwoCode;
    @ColumnInfo(name = "country")
    String country;
    @ColumnInfo(name = "state_province")
    String stateProvince;
    @ColumnInfo(name = "domains")
    String[] domains;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "web_pages")
    String[] webPages;

    public LocalUniversity(@NonNull String identifier, String alphaTwoCode,
                      String country, String stateProvince, String[] domains, String name, String[] webPages) {
        this.identifier = identifier;
        this.alphaTwoCode = alphaTwoCode;
        this.country = country;
        this.stateProvince = stateProvince;
        this.domains = domains;
        this.name = name;
        this.webPages = webPages;
    }

    public LocalUniversity() {
    }


    // getters
    public String getIdentifier() {
        return identifier;
    }

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

    // setters
    public void setIdentifier(@NonNull String identifier) {
        this.identifier = identifier;
    }

    public void setAlphaTwoCode(String alphaTwoCode) {
        this.alphaTwoCode = alphaTwoCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public void setDomains(String[] domains) {
        this.domains = domains;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWebPages(String[] webPages) {
        this.webPages = webPages;
    }

}
