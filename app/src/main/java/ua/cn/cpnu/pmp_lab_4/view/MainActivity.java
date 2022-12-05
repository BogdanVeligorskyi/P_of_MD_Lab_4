package ua.cn.cpnu.pmp_lab_4.view;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ua.cn.cpnu.pmp_lab_4.R;
import ua.cn.cpnu.pmp_lab_4.model.BackendApi;
import ua.cn.cpnu.pmp_lab_4.model.RemoteUniversity;

public class MainActivity extends AppCompatActivity {

    private final String BASE_URL = "http://universities.hipolabs.com/";

    private String[] nameArray = {};
    private String[] infoArray = {};
    private ListView listView;
    private CustomListAdapter cla;
    private List<RemoteUniversity> remoteUniv;
    //HttpLoggingInterceptor httpLoggingInterceptor;
    private OkHttpClient client;
    private final Activity mainActivity = this;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.universities_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                String name = remoteUniv.get(position).getName();
                String alphaTwoCode = remoteUniv.get(position).getAlphaTwoCode();
                String country = remoteUniv.get(position).getCountry();
                String stateProvince = remoteUniv.get(position).getStateProvince();
                String[] webPages = remoteUniv.get(position).getWebPages();
                String[] domains = remoteUniv.get(position).getDomains();

                intent.putExtra("name", name);
                intent.putExtra("alpha_two_code", alphaTwoCode);
                intent.putExtra("country", country);
                intent.putExtra("state_province", stateProvince);
                intent.putExtra("web_pages", webPages);
                intent.putExtra("domains", domains);

                startActivity(intent);
            }
        });

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try  {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
                getUniversitiesFromJSON();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    void getUniversitiesFromJSON() {
        Gson gson = new Gson();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        BackendApi bApi = retrofit.create(BackendApi.class);

        bApi.getUniversities().enqueue(new Callback<List<RemoteUniversity>>() {

            @Override
            public void onResponse(Call<List<RemoteUniversity>> call, Response<List<RemoteUniversity>> response) {
                Log.d("SUCCESS_HTTP", "Successful response!");
                remoteUniv = response.body();
                nameArray = new String[remoteUniv.size()];
                infoArray = new String[remoteUniv.size()];
                for (int i = 0; i < remoteUniv.size(); i++) {
                    nameArray[i] = remoteUniv.get(i).getName();
                    infoArray[i] = remoteUniv.get(i).getCountry();
                    Log.d("Univer... ", remoteUniv.get(i).getName());
                }
                handler.post(() -> {
                    cla = new CustomListAdapter(mainActivity, nameArray, infoArray);
                    listView.setAdapter(cla);
                });
            }

            @Override
            public void onFailure(Call<List<RemoteUniversity>> call, Throwable t) {
                call.cancel();
            }

        });
    }

    /*private void onUniversitiesChanged(List<University> universities) {
        if (universities == null) return;
        cla.(universities);
    }*/
}