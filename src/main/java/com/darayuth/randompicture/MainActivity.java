package com.darayuth.randompicture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button officeBtn, natureBtn, animalBtn;
    private Intent intent;
    public static final String EXTRA_MESSAGE = "com.darayuth.randompicture";
    private CustomAdapter arrayAdapter ;
    private ListView listView;
    private TextView textview;
    private String[] catgories= {"Film", "Animal","Nature", "Travel", "FoodDrink", "People", "Arts-Culture", "Spirituality"
                        ,"Office", "Architecture" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = (ListView) findViewById(R.id.list_view2);

        arrayAdapter = new CustomAdapter(this, R.layout.listview_root2, catgories);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (catgories[position]){
                    case "Film":
                        sendData("film");
                        break;
                    case "Animal":
                        sendData("animal");
                        break;
                    case "Nature":
                        sendData("nature");
                        break;
                    case "Travel":
                        sendData("travel");
                        break;
                    case "FoodDrink":
                        sendData("food-drink");
                        break;
                    case "People":
                        sendData("people");
                        break;
                    case "Arts-Culture":
                        sendData("arts-culture");
                        break;
                    case "Spirituality":
                        sendData("spirituality");
                        break;
                    case "Office":
                        sendData("office");
                        break;
                    case "Architecture":
                        sendData("architecture");
                        break;
                    default:
                        Log.e("MainActitivty", "onItemClick: Data is not sending " );
                    }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.categorieView:

                break;
        }
    }
    public void sendData(String info){
        intent = new Intent(this, Main2Activity.class);
        intent.putExtra(EXTRA_MESSAGE, info);
        startActivity(intent);
    }



}





















