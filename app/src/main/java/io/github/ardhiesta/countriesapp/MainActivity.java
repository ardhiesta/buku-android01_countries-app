package io.github.ardhiesta.countriesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.github.ardhiesta.countriesapp.model.Country;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.country_recycler_view);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        // menampilkan loading progress bar
        progressBar = (ProgressBar) findViewById(R.id.country_progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
        getData();
    }

    // method ini untuk memecah / parsing data negara dalam format JSON
    // dan memasukkannya ke object Country dalam bentuk array
    private ArrayList<Country> isiDataCountry(JSONArray countryJsonArray) {
        ArrayList<Country> countryArray = new ArrayList<>();
        try {
            for (int i = 0; i < countryJsonArray.length(); i++) {
                JSONObject countryJsonObject = null;
                countryJsonObject = countryJsonArray.getJSONObject(i);
                Country country = new Country();
                country.setName(countryJsonObject
                        .getString("name"));
                country.setCapital(countryJsonObject
                        .getString("capital"));

// gambar bendera negara diambil dari countryflags.io
                country.setFlag(
                        "http://www.countryflags.io/"
                                + countryJsonObject
                                .getString("alpha2Code")
                                + "/flat/64.png");
                countryArray.add(country);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return countryArray;
    }

    private void getData() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://restcountries.eu/rest/v2/all";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Country> countryArray = isiDataCountry(response);
                        // define an adapter
                        mAdapter = new CountryRecyclerViewAdapter(countryArray, getApplicationContext());

                        // mengakhiri loading progressBar dan menyembunyikan progressBar setelah data berhasil diambil
                        progressBar.setIndeterminate(false);
                        progressBar.setVisibility(View.GONE);

                        // memasang adapter ke RecyclerView
                        recyclerView.setAdapter(mAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("");
                    }
                });
        queue.add(jsonArrayRequest);
    }
}
