package com.muiedhossain.LocationAndTemperature;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface weatherApi {
    @GET("weather")
    Call<GetMainData> getWeather(@Query("q")String cityname, @Query("appid")String apikey);


}
