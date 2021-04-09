package sk.kosickaakademia.kolesarova.exchange1.calc;

import sk.kosickaakademia.kolesarova.exchange1.api.ApiRequest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CalcExchangeRates {
    private static final String[] rates=new String[]{"USD","CZK","HUF","BTC","PLN"};//zoznam mien do ktorych chceme euro prevadzat

    public void calculate(double eur){
        if(eur<0){
            System.out.println("Negative value is incoret input parameter.");
            return;
        }

        Set<String> set=new HashSet<>();
        Collections.addAll(set,rates);

        ApiRequest apiRequest=new ApiRequest();
        Map map=apiRequest.getExchangeRates(set);//volám metodu getExchangeRates, ktora mi vrati moje pozadovane kurzy

        for(String temp:rates){
            if(map.containsKey(temp)){
                double value=(double)map.get(temp);
                double result= eur*value;
                print("Eur",temp, eur,result,value);
            }
        }
    }

    //pomocná metoda na vypis výsledkov kým nie je GUI
    private void print(String from, String to, double eur, double result, double rate){
        System.out.println(eur+" "+from+" "+" -> "+result+" "+to+" (exchange rate: "+rate+" )");
    }
}
