package io.github.ardhiesta.countriesapp.model;

/**
 * Created by linuxluv on 07/03/18.
 */

public class Country {
    // nama negara
    String name;
    // ibukota negara
    String capital;
    // alamat file gambar bendera
    String flag;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCapital() {
        return capital;
    }
    public void setCapital(String capital) {
        this.capital = capital;
    }
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }

}
