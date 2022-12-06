package ua.cn.cpnu.pmp_lab_4.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import ua.cn.cpnu.pmp_lab_4.R;

// class that is necessary for ListView component setup
public class CustomListAdapter extends ArrayAdapter {

    // to reference the Activity
    private final Activity context;

    // to store the list of university names
    private final String[] nameArray;

    // to store the list of universities` countries
    private final String[] infoArray;

    public CustomListAdapter(Activity context, String[] nameArrayParam, String[] infoArrayParam){
        super(context, R.layout.listview_row , nameArrayParam);
        this.context = context;
        this.nameArray = nameArrayParam;
        this.infoArray = infoArrayParam;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"})
        View rowView=inflater.inflate(R.layout.listview_row, null,true);

        TextView nameTextField = (TextView) rowView.findViewById(R.id.university_name);
        TextView infoTextField = (TextView) rowView.findViewById(R.id.university_details);

        nameTextField.setText(nameArray[position]);
        infoTextField.setText(infoArray[position]);

        return rowView;

    }
}
