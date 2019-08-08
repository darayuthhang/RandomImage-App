package com.darayuth.randompicture;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {


    public static final String TAG = "MainActivity2";
    public static final String API_KEY = "9c40f30f6c151dba2695bc5be81c81a460e2ef322c1a8287ad4db7094fe01261";
    private int count = 1;
    //private ArrayAdapter<PictureAdapter> mAdapter;
    private ListView listView;
    private PictureAdapter mAdapter;
    private Intent intent2;
    private String queryData;
    private int pageNumber;
    private ArrayList<Picture> mList = new ArrayList<Picture>();
    private static String refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StartApp();
        getImage();
        listView = (ListView) findViewById(R.id.list_view);
    }
    public void StartApp(){
        intent2 = getIntent();
        //get the number of page, and the query of the page
        queryData = intent2.getStringExtra(MainActivity.EXTRA_MESSAGE);
        pageNumber = getRandomInteger(1, 10);
        //keep the the input from Mainactivity
        //one the same.
        if(queryData == null){
            queryData= intent2.getStringExtra(Main2Activity.refresh);
        }
        Log.i(TAG, "Query"+queryData);
        Log.i(TAG, "pageNumber"+pageNumber);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //add the action bar to layout
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_favorite:
                Intent intent = new Intent(this, Main2Activity.class);
                 refresh = "refresh";
                intent.putExtra(refresh, queryData);
                Log.i(TAG, "onOptionsItemSelected2: "+queryData);
                startActivity(intent);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    public void getImage(){
        String url2 = "https://api.unsplash.com/search/collections?page="+pageNumber+"&query=" +queryData+
                "&client_id=9c40f30f6c151dba2695bc5be81c81a460e2ef322c1a8287ad4db7094fe01261";

        DownloadTask task = new DownloadTask();
        task.execute(url2);

    }
    public int getRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }
    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String url = downloadJson(urls[0]);
            if(url == null){
                Log.e(TAG, "doInBackground: Error downloading" );
            }
            //return the data to onPostExcute after server respond.
            return url;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            listView = (ListView) findViewById(R.id.list_view);
            requestingJSON(s);

        }
        public String downloadJson(String urlPath){
            StringBuilder jsonResult = new StringBuilder();
            try{
                //open the url connection
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //get the response code the server
                int response = connection.getResponseCode();
                //display the response code in log file
                Log.d( TAG,"server"+ response);
                //get data from internet, read data, and improve the performance.
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                int charsRead;
                char[] inputBuffer = new char[500];
                while(true){
                    //read the number of data
                    charsRead = reader.read(inputBuffer);
                    if(charsRead > 0){
                        //convert char to string , and append to stringbuilder
                        jsonResult.append(String.copyValueOf(inputBuffer, 0, charsRead));
                    }else{
                        break;
                    }
                }
                reader.close();
                return jsonResult.toString();
            }catch (MalformedURLException e){
                Log.e(TAG, "downloadJson: Invalid Url" + e.getMessage() );
            }catch (IOException e){
                Log.e(TAG, "downloadJson: IO Exception reading data" + e.getMessage() );
            }catch (SecurityException e){
                Log.e(TAG, "downloadJson: Security Exception" + e.getMessage() );
            }
            return null;
        }

        public void requestingJSON(String s){
            try{
                JSONObject jsonobjRoot = new JSONObject(s);
                String results = jsonobjRoot.getString("results");
                JSONArray resultArr = new JSONArray(results);
                for(int i=0;i<resultArr.length();i++){
                    JSONObject getObject = resultArr.getJSONObject(i);
                    String user_name = getObject.getString("user");
                    String cover_photo = getObject.getString("cover_photo");

                    JSONObject user = new JSONObject(user_name);
                    JSONObject cover_PhotoObject = new JSONObject(cover_photo);

                    String name = user.getString("username");
                    String location = user.getString("location");
                    String urls = cover_PhotoObject.getString("urls");

                    JSONObject urlsObject = new JSONObject(urls);
                    //data image from json consist of mutiple
                    //size that can be choose from
                    String image = urlsObject.getString("regular");
                    mList.add(new Picture(name, location, image));

                }
                mAdapter = new PictureAdapter(getApplicationContext(), R.layout.listviewroot, mList);
                listView.setAdapter(mAdapter);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }





    }


}
