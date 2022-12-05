package ua.cn.cpnu.pmp_lab_4.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import ua.cn.cpnu.pmp_lab_4.model.LocalUniversity;
import ua.cn.cpnu.pmp_lab_4.model.RemoteUniversity;
import ua.cn.cpnu.pmp_lab_4.model.UniversitiesDao;
import ua.cn.cpnu.pmp_lab_4.model.UniversitiesDatabase;

public class MainActivity extends AppCompatActivity {

    private final String BASE_URL = "http://universities.hipolabs.com/";

    private String[] nameArray = {};
    private String[] infoArray = {};
    private ListView listView;
    private CustomListAdapter cla;
    private List<RemoteUniversity> remoteUniv;
    private List<LocalUniversity> localUniv;
    private OkHttpClient client;
    private final Activity mainActivity = this;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.universities_list);
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            String name, alphaTwoCode, country, stateProvince;
            String [] webPages, domains;

            // if data were loaded from web-page
            if (remoteUniv != null) {
                name = remoteUniv.get(position).getName();
                alphaTwoCode = remoteUniv.get(position).getAlphaTwoCode();
                country = remoteUniv.get(position).getCountry();
                stateProvince = remoteUniv.get(position).getStateProvince();
                webPages = remoteUniv.get(position).getWebPages();
                domains = remoteUniv.get(position).getDomains();

            // if data were loaded from database
            } else {
                name = localUniv.get(position).getName();
                alphaTwoCode = localUniv.get(position).getAlphaTwoCode();
                country = localUniv.get(position).getCountry();
                stateProvince = localUniv.get(position).getStateProvince();
                String webStr = localUniv.get(position).getWebPages();
                webPages = webStr.split("|");
                String domainStr = localUniv.get(position).getDomains();
                domains = domainStr.split("|");
            }

            intent.putExtra("name", name);
            intent.putExtra("alpha_two_code", alphaTwoCode);
            intent.putExtra("country", country);
            intent.putExtra("state_province", stateProvince);
            intent.putExtra("web_pages", webPages);
            intent.putExtra("domains", domains);

            startActivity(intent);
        });

        getUniversitiesFromJSON();

    }

    // read list of universities from JSON-page
    private void getUniversitiesFromJSON() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            Gson gson = new Gson();
            Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
            BackendApi bApi = retrofit.create(BackendApi.class);

            bApi.getUniversities().enqueue(new Callback<List<RemoteUniversity>>() {

                @Override
                public void onResponse(@NonNull Call<List<RemoteUniversity>> call,
                                       @NonNull Response<List<RemoteUniversity>> response) {
                    Log.d("HTTP_SUCCESS", "Successful response!");
                    remoteUniv = response.body();
                    assert remoteUniv != null;
                    nameArray = new String[remoteUniv.size()];
                    infoArray = new String[remoteUniv.size()];
                    for (int i = 0; i < remoteUniv.size(); i++) {
                        nameArray[i] = remoteUniv.get(i).getName();
                        infoArray[i] = remoteUniv.get(i).getCountry();
                        //Log.d("Univer... ", remoteUniv.get(i).getName());

                    }
                    handler.post(() -> {
                        updateDb();
                        cla = new CustomListAdapter(mainActivity, nameArray, infoArray);
                        listView.setAdapter(cla);
                    });
                    //call.cancel();
                }

                @Override
                public void onFailure(@NonNull Call<List<RemoteUniversity>> call, @NonNull Throwable t) {
                    Log.d("HTTP_FAIL",
                        "Looks like your Internet is not stable, but we`ll check database...");
                    findInDb();
                    call.cancel();
                }
            });
        });
    }

    // try to read universities from local database
    private void findInDb() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            UniversitiesDatabase db = Room.databaseBuilder(getApplicationContext(),
                            UniversitiesDatabase.class, "univers.db")
                    .build();
            UniversitiesDao dao = db.getUniversitiesDao();
            localUniv = dao.getUniversities();
            nameArray = new String[localUniv.size()];
            infoArray = new String[localUniv.size()];
            for (int i = 0; i < localUniv.size(); i++) {
                nameArray[i] = localUniv.get(i).getName();
                infoArray[i] = localUniv.get(i).getCountry();
                Log.d("DB", localUniv.get(i).getName());
            }

            handler.post(() -> {
                cla = new CustomListAdapter(mainActivity, nameArray, infoArray);
                listView.setAdapter(cla);
            });
        });

    }

    // update all values in local db
    private void updateDb() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            UniversitiesDatabase db = Room.databaseBuilder(getApplicationContext(),
                            UniversitiesDatabase.class, "univers.db")
                    .build();
            UniversitiesDao dao = db.getUniversitiesDao();
            dao.deleteAll();

            for (int i = 0; i < remoteUniv.size(); i++) {
                String[] domainsArr = remoteUniv.get(i).getDomains();
                String domainsStr = "";
                for(int j = 0; j < domainsArr.length; j++) {
                    domainsStr += domainsArr[j];
                    domainsStr += "|";
                }
                String[] webArr = remoteUniv.get(i).getWebPages();
                String webStr = "";
                for(int j = 0; j < webArr.length; j++) {
                    webStr += webArr[j];
                    webStr += "|";
                }
                LocalUniversity univ = new LocalUniversity(remoteUniv.get(i).getAlphaTwoCode(),
                        remoteUniv.get(i).getCountry(),
                        remoteUniv.get(i).getStateProvince(),
                        domainsStr,
                        remoteUniv.get(i).getName(),
                        webStr);
                dao.insertOneUniversity(univ);
            }

            List<LocalUniversity> lu = dao.getUniversities();
            for (int i = 0; i < lu.size(); i++) {
                Log.d("DB", lu.get(i).getName());
            }

        });
    }

}