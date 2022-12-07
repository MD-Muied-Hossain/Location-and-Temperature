package com.muiedhossain.LocationAndTemperature;

import com.google.gson.annotations.SerializedName;

public class GetWindData {

    @SerializedName("speed")
    Wind wind;

    public Wind getWind()
    {
        return wind;
    }

    public GetWindData(Wind wind) {
        this.wind = wind;
    }
}
