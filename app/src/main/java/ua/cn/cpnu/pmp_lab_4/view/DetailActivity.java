package ua.cn.cpnu.pmp_lab_4.view;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import ua.cn.cpnu.pmp_lab_4.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String savedName = getIntent().getStringExtra("name");
        String savedCountry = getIntent().getStringExtra("country");
        String savedAlphaTwoCode = getIntent().getStringExtra("alpha_two_code");
        String savedStateProvince = getIntent().getStringExtra("state_province");
        String savedWebPages[] = getIntent().getStringArrayExtra("web_pages");
        String savedDomains[] = getIntent().getStringArrayExtra("domains");

        //TextView textViewDetails = (TextView) findViewById(R.id.text_view_details);
        TextView textViewDetailsNameValue = (TextView) findViewById(R.id.text_view_details_name_value);
        TextView textViewDetailsCodeValue = (TextView) findViewById(R.id.text_view_details_code_value);
        TextView textViewDetailsStateValue = (TextView) findViewById(R.id.text_view_details_state_value);
        TextView textViewDetailsDomainsValue = (TextView) findViewById(R.id.text_view_details_domains_values);
        TextView textViewDetailsCountryValue = (TextView) findViewById(R.id.text_view_details_country_value);
        TextView textViewDetailsWebValues = (TextView) findViewById(R.id.text_view_details_web_values);

        if (savedStateProvince == null) {
            textViewDetailsStateValue.setText("None");
        }
        textViewDetailsNameValue.setText(savedName);
        textViewDetailsCodeValue.setText(savedAlphaTwoCode);
        textViewDetailsCountryValue.setText(savedCountry);
        textViewDetailsDomainsValue.setText("");
        textViewDetailsWebValues.setText("");
        for(int i = 0; i < savedDomains.length; i++) {
            textViewDetailsDomainsValue.append(savedDomains[i]);
            textViewDetailsDomainsValue.append("\t");
        }

        for(int i = 0; i < savedWebPages.length; i++) {
            textViewDetailsWebValues.append(savedWebPages[i]);
            textViewDetailsWebValues.append("\t");
        }
    }
}
