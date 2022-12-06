package ua.cn.cpnu.pmp_lab_4.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import ua.cn.cpnu.pmp_lab_4.R;

// detail activity class that is used when you click
// on list item in main window
public class DetailActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String savedName = getIntent().getStringExtra("name");
        String savedCountry = getIntent().getStringExtra("country");
        String savedAlphaTwoCode = getIntent().getStringExtra("alpha_two_code");
        String savedStateProvince = getIntent().getStringExtra("state_province");
        String[] savedWebPages = getIntent().getStringArrayExtra("web_pages");
        String[] savedDomains = getIntent().getStringArrayExtra("domains");

        TextView textViewDetailsNameValue = (TextView)
                findViewById(R.id.text_view_details_name_value);
        TextView textViewDetailsCodeValue = (TextView)
                findViewById(R.id.text_view_details_code_value);
        TextView textViewDetailsStateValue = (TextView)
                findViewById(R.id.text_view_details_state_value);
        TextView textViewDetailsDomainsValue = (TextView)
                findViewById(R.id.text_view_details_domains_values);
        TextView textViewDetailsCountryValue = (TextView)
                findViewById(R.id.text_view_details_country_value);
        TextView textViewDetailsWebValues = (TextView)
                findViewById(R.id.text_view_details_web_values);

        // if there`s no state-province
        if (savedStateProvince == null) {
            textViewDetailsStateValue.setText("None");
        }
        textViewDetailsNameValue.setText(savedName);
        textViewDetailsCodeValue.setText(savedAlphaTwoCode);
        textViewDetailsCountryValue.setText(savedCountry);
        textViewDetailsDomainsValue.setText("");
        textViewDetailsWebValues.setText("");
        for (String savedDomain : savedDomains) {
            textViewDetailsDomainsValue.append(savedDomain);
            //textViewDetailsDomainsValue.append("\t");
        }

        for (String savedWebPage : savedWebPages) {
            textViewDetailsWebValues.append(savedWebPage);
            //textViewDetailsWebValues.append("\t");
        }

        textViewDetailsWebValues.setClickable(true);
        textViewDetailsWebValues.setMovementMethod(LinkMovementMethod.getInstance());
        textViewDetailsWebValues.setLinkTextColor(Color.BLUE);
        String text = "<a href='" + savedWebPages[0] + "'>" +  savedWebPages[0] + "</a>";
        textViewDetailsWebValues.setText(Html.fromHtml(text));
    }
}
