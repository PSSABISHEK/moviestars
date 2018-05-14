package com.example.asus.moviestars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.AsyncTask;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView t1;
    private Button b1;
    private String url;
    private EditText ed1;

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient client = new OkHttpClient();

        t1 = (TextView) findViewById(R.id.textView1);
        b1 = (Button) findViewById(R.id.button1);
        ed1 = (EditText) findViewById(R.id.editText1);

        b1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "Clicked", Toast.LENGTH_SHORT).show();
                String query = ed1.getText().toString();
                url = "https://api.themoviedb.org/3/search/movie?api_key=9773640d0bdc38d3f8214429e5e625c7&query=";
                url = url.concat(query);
                getdata(url);
            }
        });
    }
    private void getdata(final String url){

        Request request = new Request.Builder()
                              .url(url)
                              .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e)  {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String res = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            t1.setText(res);
                        }
                    });
                }
            }
        });
    }
}
