package assignment2.kit607.timemanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class tmqList extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private int Ascore=0;
    private int Bscore=0;
    private int Cscore=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmq_list);
       /* if(getIntent()!=null){
            Intent i =getIntent();
            Bundle b= i.getBundleExtra("score");
            int type=b.getInt("Q_type");
            int score=b.getInt("score");
            if(type==1){
                Ascore=score;
            }if(type==2){
                Bscore=score;
            }else
                Cscore=score;

        }*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.btn_toolbar_back) {
                    AlertDialog.Builder bldr = Util.AlertDialogBuilder(tmqList.this
                            , getString(R.string.confirm_cancel_addEdit_title), getString(R.string.confirm_cancel_addEdit_message));
                    bldr.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            tmqList.this.finish();
                        }
                    });
                    bldr.create().show();
                }

                return false;
            }
        });
       initialList();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_edit_delete; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //Initiate Question type ListView
    public void initialList(){
        ListView listView=(ListView)findViewById(R.id.tmq_lsit_listview);
        String[] ques_type= getResources().getStringArray(R.array.questions_type);
        ArrayAdapter<String> tmq_adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                ques_type);
        listView.setAdapter(tmq_adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
                public void onItemClick(AdapterView<?> parent, View v,int position,long id) {
                Intent i= new Intent(tmqList.this, question_detail.class);
                i.putExtra("item_position", position);
                startActivity(i);
            }
        });

    }

}
