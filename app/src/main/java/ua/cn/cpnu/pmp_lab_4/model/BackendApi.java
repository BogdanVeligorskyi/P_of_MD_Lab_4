package ua.cn.cpnu.pmp_lab_4.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

// interface with GET-method for searching
// ukrainian universities on page
public interface BackendApi {

    @GET("search?country=ukraine")
    Call<List<RemoteUniversity>> getUniversities();

}
