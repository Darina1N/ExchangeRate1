package sk.kosickaakademia.kolesarova.exchange1;

import sk.kosickaakademia.kolesarova.exchange1.api.ApiRequest;
import sk.kosickaakademia.kolesarova.exchange1.calc.CalcExchangeRates;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args ){
        System.out.println("This is exchange rates App");
        Set<String> set=new HashSet<>();//nepotrebujem to mať usporiadané preto mi stačí set
        set.add("USD");
        set.add("HUF");
        set.add("CZK");
        set.add("BTC");

/*
        Map map=new ApiRequest().getExchangeRates(set);//do metody posielam len tieto 4 kurzy aby mi vypisalo o nich info
        System.out.println(map.toString());*/

        CalcExchangeRates calcExchangeRates=new CalcExchangeRates();
        calcExchangeRates.calculate(85);
    }
}
