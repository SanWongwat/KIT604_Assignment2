package assignment2.kit607.timemanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by PeitaoYe on 2017/5/20.
 */

public class questionAdapter extends ArrayAdapter<String> {
    private static final String TAG = "MainActivity";
    public questionAdapter(Context cont, String[] array){
        super(cont,0,array);
    }
    @Override
    public View getView(int position, View convert, ViewGroup parent){
        String a =getItem(position);
        if (convert == null) {
            convert = LayoutInflater.from(getContext()).inflate(R.layout.question_list_item, parent, false);
        }
        TextView textView=(TextView)convert.findViewById(R.id.question_content);
        textView.setText(a);
        Spinner spinner =(Spinner)convert.findViewById(R.id.question_spinner);
        String[] ques_type= convert.getResources().getStringArray(R.array.Q_choice);
        ArrayAdapter<String> spinnerAdapter= new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item,ques_type);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        return convert;
        }


}
