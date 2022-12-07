package com.muiedhossain.LocationAndTemperature;

import retrofit2.Call;
import retrofit2.http.Query;

public interface windApi {

    Call<GetWindData> getWind(@Query("q")String cityname, @Query("appid")String apikey);
    
}
