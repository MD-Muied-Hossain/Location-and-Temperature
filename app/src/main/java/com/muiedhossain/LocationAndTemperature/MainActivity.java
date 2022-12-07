package com.muiedhossain.LocationAndTemperature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.muiedhossain.LocationAndTemperature.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText et;
    TextView tv,tv2;
    String url ="https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";
    String apikey = "2aecf18d81582c378c13ed1fadd7020f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et);
        tv = findViewById(R.id.tv);
        /*tv2 = findViewById(R.id.tv2);*/
    }

    public void getWeather(View view) {

        if(et.getText().toString().equals(""))
        {
            Toast.makeText(MainActivity.this,"Please enter a city name First",Toast.LENGTH_LONG).show();
        }
        else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            weatherApi myapi = retrofit.create(weatherApi.class);
            windApi windApi = retrofit.create(windApi.class);

            Call<GetMainData> getTheWeather = myapi.getWeather(et.getText().toString().trim(), apikey);
            /* Call<GetWindData> getTheWind = windApi.getWind(et.getText().toString().trim(),apikey);*/

            getTheWeather.enqueue(new Callback<GetMainData>() {
                @Override
                public void onResponse(Call<GetMainData> call, Response<GetMainData> response) {
                    if (response.code() == 404) {
                        Toast.makeText(MainActivity.this, "Please Enter A Valid City", Toast.LENGTH_LONG).show();
                    } else if (!(response.isSuccessful())) {
                        Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_LONG).show();
                    } else {
                        GetMainData myData = response.body();
                        Main main = myData.getMain();

                        double temp = main.getTemp();
                        float pressure = main.getPressure();
                        double feelsLike = main.getFeelsLike();
                        Integer humidity = main.getHumidity();

                        Integer temperature_in_C = (int) (temp - 273.15);
                        Integer feelLike_ic_C = (int) (feelsLike - 273.15);
                        Integer pressureInt = (int) pressure;
                        Integer humidityInt = (int) humidity;

                        String output = "Current Temperature : " + temperature_in_C + " C"
                                + "\n Environment Feels Like : " + feelLike_ic_C + " C"
                                + "\n Pressure : " + pressureInt + " hpa"
                                + "\n Humidity : " + humidityInt + " %";

                        tv.setText(output);
                    }
                }

                @Override
                public void onFailure(Call<GetMainData> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }

     /*   /////////wind
        getTheWind.enqueue(new Callback<GetWindData>() {
            @Override
            public void onResponse(Call<GetWindData> call, Response<GetWindData> response) {
                if(response.code() == 404)
                {
                    Toast.makeText(MainActivity.this,"Please Enter A Valid City",Toast.LENGTH_LONG).show();
                }else if(!(response.isSuccessful())){
                    Toast.makeText(MainActivity.this,response.code(),Toast.LENGTH_LONG).show();
                }else {
                    GetWindData myWindData = response.body();
                    Wind wind = myWindData.getWind();;
                    String speed = String.valueOf(wind.getSpeed());

                    tv2.setText(speed+"m/s");
                }
            }
            @Override
            public void onFailure(Call<GetWindData> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        /////////wind*/

    }

    public void myLocation(View view) {

        Intent myLocationIntent = new Intent(MainActivity.this,MyLocationActivity.class);
        startActivity(myLocationIntent);

    }

}