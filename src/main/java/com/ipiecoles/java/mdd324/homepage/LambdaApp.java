package com.ipiecoles.java.mdd324.homepage;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class LambdaApp implements RequestHandler<Object,GatewayResponse> {
    @Override
    public GatewayResponse handleRequest(Object input, Context context) {
        List<Horoscope> horoscope = null;
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Access-Control-Allow-Origin", "https://pjvilloud.github.io");
        try {
            horoscope = HoroscopeService.getHoroscope();
        } catch (Exception e) {
            //500
            return new GatewayResponse(
                    "{'error':'Erreur interne'}",
                    headers,
                    500
            );
        }
        Genson g = new GensonBuilder().useRuntimeType(true).create();
        String jsonOutput = g.serialize(horoscope);

        GatewayResponse gatewayResponse = new GatewayResponse(
                jsonOutput,//body
                headers,//headers
                200);//statusCode
        return gatewayResponse;
    }
}
