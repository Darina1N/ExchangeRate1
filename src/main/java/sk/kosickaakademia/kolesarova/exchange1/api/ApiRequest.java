package sk.kosickaakademia.kolesarova.exchange1.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ApiRequest {
    public Map getExchangeRates(Set<String> rates){
        if(rates==null || rates.size()==0)//kontrola aby nebol prázdny vstup
            return null;
        return parseData(rates);
    }

    //metoda na pripojenie na API server a vrati data vo formate string nerozparsovane este
    private String getRatesFromAPIServer(){
        try {
            URL url = new URL("http://api.exchangeratesapi.io/v1/latest?access_key=749dbdfe370c04298a40df3057360819&format=1");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //návratový kod zo servra či sa konekt podaril alebo ci je nejaky problem
            int responsecode = conn.getResponseCode();
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                scanner.close();
                //System.out.println(inline);
                return inline;//vratime komplet cely vysledok zo servra sko string
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //metoda ktora prijme kurzy ktore chceme, zavola predoslu metodu, rozparsuje data a vráti map
    private Map parseData(Set<String> rates){
        String inline=getRatesFromAPIServer();
        if(inline==null)
            return null;
        try{
            //Using the JSON simple library parse the string into a json object
            JSONParser parse = new JSONParser();
            JSONObject data_obj = (JSONObject) parse.parse(inline);

            JSONObject obj = (JSONObject) data_obj.get("rates");

            Map<String,Double> map=new HashMap<>();
            for(String temp:rates){//prechadzam si vsetky kurzy a chcem si vytiahnuť len tie ktore mam zadane
                if(obj.containsKey(temp)){
                    double value=(double)obj.get(temp);
                    map.put(temp,value);//naplňam si map kluc a k nemu value
                }
            }
            return map;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

        public JSONObject parseString(){
            String inline = getRatesFromAPIServer();
            JSONParser parse = new JSONParser();
            JSONObject data_obj = null;
            try {
                data_obj = (JSONObject) parse.parse(inline);
                JSONObject obj = (JSONObject) data_obj.get("rates");
                return obj;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }


