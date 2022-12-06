package ua.cn.cpnu.pmp_lab_4.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// class for representing University entity loaded from
// a local database
@Entity(tableName = "universities")
public class LocalUniversity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "identifier")
    int identifier;
    @ColumnInfo(name = "alpha_two_code")
    String alphaTwoCode;
    @ColumnInfo(name = "country")
    String country;
    @ColumnInfo(name = "state_province")
    String stateProvince;
    @ColumnInfo(name = "domains")
    String domains;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "web_pages")
    String webPages;

    public LocalUniversity(String alphaTwoCode,
                           String country, String stateProvince, String domains,
                           String name, String webPages) {
        this.alphaTwoCode = alphaTwoCode;
        this.country = country;
        this.stateProvince = stateProvince;
        this.domains = domains;
        this.name = name;
        this.webPages = webPages;
    }

    // getters
    public int getIdentifier() {
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

    public String getDomains() {
        return domains;
    }

    public String getName() {
        return name;
    }

    public String getWebPages() {
        return webPages;
    }

    // setters
    public void setIdentifier(@NonNull int identifier) {
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

    public void setDomains(String domains) {
        this.domains = domains;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWebPages(String webPages) {
        this.webPages = webPages;
    }

}
