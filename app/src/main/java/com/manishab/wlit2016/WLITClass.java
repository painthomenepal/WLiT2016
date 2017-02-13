package com.manishab.wlit2016;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by admin on 5/20/2016.
 */
public class WLITClass extends Fragment {
    ListView listView;
    String url="http://wlit.org.np/index.php/api/";
    CustomArrayAdapter arrayAdapter;
    String jsonString;
    JSONArray jsonArray;
    ArrayList list;
    ArrayList<Entity>entityArrayList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.activity_namelist,container,false);
        listView=(ListView)view.findViewById(R.id.list_item);
        // list=getListData();
        // arrayAdapter=new CustomArrayAdapter(Namelist.this,list);
        //listView.setAdapter(arrayAdapter);
        //android arrayadapter
        // arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,Name);
        // listView.setAdapter(arrayAdapter);
        new wlitlist().execute(url);

        return view;
    }
    private class wlitlist extends AsyncTask<String,String,String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(getActivity());
            progressDialog.setMessage("loading...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            System.out.println(params[0]);
            DownloadUtil downloadUtil=new DownloadUtil(params[0],getActivity().getApplicationContext());
            jsonString=downloadUtil.downloadStringContent();
            return jsonString;
        }

        @Override
        protected void onPostExecute(String responseString) {
            super.onPostExecute(responseString);
            if(responseString.equalsIgnoreCase(DownloadUtil.NotOnline)){
                progressDialog.dismiss();
                Toast.makeText(getActivity().getApplicationContext(),"Net chaina",Toast.LENGTH_LONG).show();
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
                    arrayAdapter=new CustomArrayAdapter(getActivity().getApplicationContext(),entityArrayList);
                    listView.setAdapter(arrayAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent(getActivity().getApplicationContext(),InfoActivity.class);
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

}
