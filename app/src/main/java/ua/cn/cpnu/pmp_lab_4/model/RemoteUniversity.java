package ua.cn.cpnu.pmp_lab_4.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class RemoteUniversity implements Parcelable {

    @SerializedName("alpha_two_code")
    private String alphaTwoCode;

    private String country;

    @SerializedName("state-province")
    private String stateProvince;
    private String[] domains;
    private String name;

    @SerializedName("web_pages")
    private String[] webPages;

    protected RemoteUniversity(Parcel in) {
        alphaTwoCode = in.readString();
        country = in.readString();
        stateProvince = in.readString();
        domains = in.createStringArray();
        name = in.readString();
        webPages = in.createStringArray();
    }

    public static final Creator<RemoteUniversity> CREATOR = new Creator<RemoteUniversity>() {
        @Override
        public RemoteUniversity createFromParcel(Parcel in) {
            return new RemoteUniversity(in);
        }

        @Override
        public RemoteUniversity[] newArray(int size) {
            return new RemoteUniversity[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(alphaTwoCode);
        parcel.writeString(country);
        parcel.writeString(stateProvince);
        parcel.writeStringArray(domains);
        parcel.writeString(name);
        parcel.writeStringArray(webPages);
    }
}
