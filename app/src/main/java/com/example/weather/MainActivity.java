package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView mDate, mCity, mTemp, mDescription;
    ImageView imgIcon;
    String maVilles = "Toronto";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDate = findViewById(R.id.mDate);
        mCity = findViewById(R.id.mCity);
        mTemp = findViewById(R.id.mTemp);
        mDescription = findViewById(R.id.mDescription);
        afficher();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recherche, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)menuItem.getActionView();
        searchView.setQueryHint("Ecrire le nom de la Ville");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTetListener()){
            @Override
                    public boolean onQueryTextSubmit(String query){
                maVille = query;
                afficher();
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if(getCurrent() != null){
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return false;


            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    public void afficher(){

String.url = "https://api.openweathermap.org/data/2.5/weather?q=" + maVille + "&appid=7f9799eff79f0699f9b9a8f787d62739&units=metric"
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(DownloadManager.Request.method.GET.url, null, new Response.Listener)
                new Response.Listener<JSONObject>(){


                @Override
                        public void onResponse(JSONObject response){
                    try {
                        JSONObject main_object = response.getJSONObject("main");
                        JSONArray array = response.getJSONArray("weather");
                        //Log.d("Tag", "resultat = " + array.tostring());
                        JsonObject object = array.getJSONObject(0);
                        int tempC = (int) Math.round(main_object.getDouble("temp"));
                        String temp = String.valueO(tempC);

                        String description = object.getString("description");
                        String city = resopnse.getStrign("name");
                        String icon = object.getString("icon");

                        mCity.setText(city);
                        mTemp.setText(temp);
                        mDescription.setText(descpription);

                        Calendar calendar = Calendar.getInstance();
                        SimpleDataFormat simpleDataFormat = new SimpleDataFormat("EEEE, MMMM, dd");
                        String formatted_date = simpleDateFormat.format(calendar.getTime);

                        mDate.setText(formatted_date);

                        String imageUri = "https://openweathermap.org/img/w/" + icon + " .png";
                        imgIcon = findViewById(R.id.imgIcon);
                        Uri myUri.parse(imageUri);
                        Picasso.with(MainActivity.this).load(myUri).resize(200, 200).into(imgIcon);
                    }catch(JSONException e) {

                    e.printStackTrace();
                    }
                    }

        }, new Response.ErrorListener(){
    @Override
            public void onErrorResponse(VolleyError error) {
    }
    }) ;
RequestQueue queue = Volley.newRequestQueue(this);
queue.add(jsonObjectRequest);

}
}