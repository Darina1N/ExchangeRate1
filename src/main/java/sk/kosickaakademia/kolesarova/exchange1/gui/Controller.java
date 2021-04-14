package sk.kosickaakademia.kolesarova.exchange1.gui;


import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.json.simple.JSONObject;
import sk.kosickaakademia.kolesarova.exchange1.api.ApiRequest;
import sk.kosickaakademia.kolesarova.exchange1.calc.CalcExchangeRates;
import sk.kosickaakademia.kolesarova.exchange1.database.MongoDB;

import java.util.Arrays;
import java.util.Set;

public class Controller {

    public Button btn_change;
    public TextField txt_value;
    public ComboBox cmbx_currency;
    public TextField txt_result;
    public TextField txt_rate;

    public void change(ActionEvent actionEvent) {
        String enter=txt_value.getText();//je to textove poličko takže vždy mi dá string
        double value=Double.parseDouble(enter);//môžem dať aj desatinne čisla
        CalcExchangeRates calc=new CalcExchangeRates();

        String key = (String)cmbx_currency.getValue();
        calc.calculateGUI(value,key);
        txt_rate.setText(String.valueOf(getRate(key)));
        txt_result.setText(String.valueOf(calc.calculateGUI(value,key)));
        new MongoDB().insertAllResult(key,value,calc.calculateGUI(value,key));
    }

    public String[] getNameOfCurrency(){
        JSONObject jsonObject = new ApiRequest().parseString();
        Set<String> keys = jsonObject.keySet();
        String[] array = new String[keys.size()];
        int i = 0;
        for(String st : keys){
            array[i] = st;
            i++;
        }
        return array;
    }

    public double getRate(String array){
        JSONObject jsonObject = new ApiRequest().parseString();
        double rate=(double)jsonObject.get(array);
        return rate;
    }
    //metoda ktorá mi naplní combo nazvami mien
    public void name_currency(MouseEvent mouseEvent) {
        String[] arrayNameOfCurrency = getNameOfCurrency();
        Arrays.sort(arrayNameOfCurrency);
        for(int i = 0; i < arrayNameOfCurrency.length; i++){
            cmbx_currency.getItems().add(arrayNameOfCurrency[i]);
        }
    }
}
