package com.manishab.wlit2016;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Namelist extends AppCompatActivity {
    ListView listView;
    String url="http://wlit.org.np/index.php/api/";
    CustomArrayAdapter arrayAdapter;
    String jsonString;
    JSONArray jsonArray;
    ArrayList list;
    ArrayList<Entity>entityArrayList;
  // private String[] Name={"Mandira","Binita","Sichita","Kiran","Suman"};
   // ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namelist);
        listView=(ListView)findViewById(R.id.list_item);
       // list=getListData();
       // arrayAdapter=new CustomArrayAdapter(Namelist.this,list);
        //listView.setAdapter(arrayAdapter);
        //android arrayadapter
       // arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,Name);
       // listView.setAdapter(arrayAdapter);
        new wlitlist().execute(url);

    }
    private class wlitlist extends AsyncTask<String,String,String>{
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(Namelist.this);
            progressDialog.setMessage("loading...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            System.out.println(params[0]);
            DownloadUtil downloadUtil=new DownloadUtil(params[0],Namelist.this);
            jsonString=downloadUtil.downloadStringContent();
            return jsonString;
        }

        @Override
        protected void onPostExecute(String responseString) {
            super.onPostExecute(responseString);
            if(responseString.equalsIgnoreCase(DownloadUtil.NotOnline)){
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),"Net chaina",Toast.LENGTH_LONG).show();
        }
        else{

                try {
                    entityArrayList=new ArrayList<Entity>();
                    jsonArray=new JSONArray(responseString);
                    for(int i=0;i<jsonArray.length();i++){
                        Entity entity=new Entity();
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String name=jsonObject.getString("Name");
                        String address=jsonObject.getString("Address");
                        String mobile_number=jsonObject.getString("Mobile Number");
                        String email=jsonObject.getString("email");
                        String university=jsonObject.getString("University");
                        String info=jsonObject.getString("info");
                        String picture=jsonObject.getString("picture");
                        entity.setName(name);
                        entity.setAddress(address);
                        entity.setMobileno(mobile_number);
                        entity.setEmail(email);
                        entity.setUniversity(university);
                        entity.setInfo(info);
                        entity.setPicture(picture);
                        entityArrayList.add(entity);
                }
                    arrayAdapter=new CustomArrayAdapter(getApplicationContext(),entityArrayList);
                    listView.setAdapter(arrayAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent(Namelist.this,InfoActivity.class);
                            intent.putExtra("entity",entityArrayList.get(position));
                           // intent.putExtra("name",entityArrayList.get(position));
                            startActivity(intent);

                        }
                    });
                    progressDialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        }
    }
   // private ArrayList getListData(){
       // entityArrayList=new ArrayList<Entity>();
       // Entity entity=new Entity();
       // entity.setName("Mandira");
       // entity.setAddress("Bhaktapur");
       // entityArrayList.add(entity);
        //return entityArrayList;

   // }
}
