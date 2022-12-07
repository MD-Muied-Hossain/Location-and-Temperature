package com.muiedhossain.LocationAndTemperature;

import com.google.gson.annotations.SerializedName;

public class GetMainData {
    @SerializedName("main")
    Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
