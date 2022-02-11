package com.example.pasarela.Llamada;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class Sales {
    Context ctn;
    String Token;

    public Sales(Context context, String AccessToken) {
        this.ctn = context;
        this.Token = AccessToken;
    }
    public String salesPost( String jsonModel){
        String UrlRegions = "https://pay.payphonetodoesposible.com/api/Sale";
        final String[] respuesta = new String[1];
        StringRequest llamado = new StringRequest(Request.Method.POST, UrlRegions, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONArray ArrayJson = new JSONArray(response);
                        Log.i("res", ArrayJson.toString());
                        respuesta[0] = ArrayJson.toString() ;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error is ", "" + error.getMessage());
                Toast.makeText(ctn.getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("Authorization", "Bearer " + Token);
                return params;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("model",jsonModel);
                return params;
            }
        };
        RequestQueue ejecVolley = Volley.newRequestQueue(ctn);
        ejecVolley.add(llamado);
        return respuesta[0];
    }
}
