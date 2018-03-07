package io.github.ardhiesta.countriesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.ardhiesta.countriesapp.model.Country;

/**
 * Created by linuxluv on 07/03/18.
 */

public class CountryRecyclerViewAdapter extends RecyclerView.Adapter<CountryRecyclerViewAdapter.ViewHolder> {
    // values : data berbentuk List berisi object Country yang akan ditampilkan di RecyclerView
    private List<Country> countries;
    public Context context;

    // class ini akan melakukan mapping layout country_item_layout.xml ke logic program untuk dapat diisi data yang akan ditampilkan
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtCountryName;
        public TextView txtCountryCapital;
        public View layout;
        public ImageView ivCountry;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtCountryName = (TextView) v.findViewById(R.id.country_name);
            txtCountryCapital = (TextView) v.findViewById(R.id.country_capital);
            ivCountry = (ImageView) v.findViewById(R.id.country_flag);
        }
    }

    // constructor untuk mengisi data
    public CountryRecyclerViewAdapter(List<Country> myDataset, Context context) {
        this.countries = myDataset;
        this.context = context;
    }

    @Override
    public CountryRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.country_item_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // untuk mengisi data ke komponen layout country_item_layout.xml
    @Override
    public void onBindViewHolder(CountryRecyclerViewAdapter.ViewHolder holder, int position) {
        final String name = countries.get(position).getName();
        final String capital = countries.get(position).getCapital();
        final String flag = countries.get(position).getFlag();
        holder.txtCountryName.setText(name);
        holder.txtCountryCapital.setText(capital);
        Picasso.with(context)
                .load(flag)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.ivCountry);
    }

    // mengembalikan ukuran data (array cuntries)
    @Override
    public int getItemCount() {
        return countries.size();
    }

}
