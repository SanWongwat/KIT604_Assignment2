package assignment2.kit607.timemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class question_detail extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private int score=0;
    private Question q= new Question();
    private int identifier=0;
    private ListView listView;

    private int cycle=0;//identify which type of question has not been answered
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);
       listView =(ListViewForScrollView)findViewById(R.id.question_detail);
        if(this.getIntent()!=null){
            Intent i=getIntent();
           int position= i.getIntExtra("item_position",5);
            switch (position){
                case 0:
                    LoadShortQ();
                    cycle=1;
                    break;
                case 1:
                    LoadAttitudeQ();
                    cycle=2;
                    break;
                case 2:
                    LoadLongQ();
                    cycle=4;
                    break;
            }

        }
        Button button=(Button)findViewById(R.id.question_submit_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View v){
               ArrayList<String> selections= getSelections();
                score+=countMark(selections);

                if(cycle==1){
                    LoadAttitudeQ();
                    cycle=3;

                }else if(cycle==3){
                    LoadLongQ();
                    cycle=7;
                }else if(cycle==7){
                   Intent i = new Intent(question_detail.this, MainActivity.class);
                    Bundle b= new Bundle();
                    b.putInt("score",score);
                    i.putExtra("score",b);
                    startActivity(i);
                }else if(cycle==2){

                    LoadShortQ();
                    cycle+=identifier;
                }else if(cycle==4){

                    LoadShortQ();
                    cycle+=identifier;
                }else if(cycle==5){
                    LoadAttitudeQ();
                    cycle+=identifier;
                }





                /*Intent i = new Intent(question_detail.this, tmqList.class );
                Bundle b = new Bundle();
                b.putInt("score",score);
                b.putInt("Q_type",identifier);
                i.putExtra("score",b);
                startActivity(i);*/
            }

        });
    }

    public void LoadShortQ(){
        identifier=1;
        String[] shortQ=q.getShortQ();
        questionAdapter qa= new questionAdapter(this, shortQ);
        listView.setAdapter(qa);
    }
    public void LoadAttitudeQ(){
        identifier=2;
        String[] attitudeQ=q.getAttitude();
        questionAdapter qa= new questionAdapter(this, attitudeQ);
        listView.setAdapter(qa);
    }
    public void LoadLongQ(){
        identifier=4;
        String[] longQ=q.getLongQ();
        questionAdapter qa= new questionAdapter(this, longQ);
        listView.setAdapter(qa);
    }
    public ArrayList<String> getSelections(){
        ArrayList<String> selections= new ArrayList<String>();
        for(int i=0;i<listView.getChildCount();i++){
            View v = listView.getChildAt(i);
            Spinner spinner =(Spinner)v.findViewById(R.id.question_spinner);
            String selection=spinner.getSelectedItem().toString();
            selections.add(selection);
        }
        return selections;
    }
    public int countMark(ArrayList<String> array){
        int score=0;
        for(String str:array){
            if(str.equals("Never"))
                score+=1;
            if(str.equals("Rarely"))
                score+=2;
            if(str.equals("Occasionally"))
                score+=3;
            if(str.equals("Regularly"))
                score+=4;
            if(str.equals("Always"))
                score+=5;
        }
        return score;
    }

}
