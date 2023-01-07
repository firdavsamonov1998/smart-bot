package Controller.Weather;

import Controller.InlineButtonUtil;
import Controller.MyTelegramBot;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherController {

    MyTelegramBot myTelegramBot;

    public WeatherController(MyTelegramBot myTelegramBot) {
        this.myTelegramBot = myTelegramBot;
    }

    private String description;
    private String cloud;
    private JSONObject main;
    private Double temp;
    private Double temp_min;
    private Double temp_max;

    private Integer humidity;
    private Double speed;
    private Double deg;
    private String name;
    private Integer bosim;
    private Double formula = 273.;

    private String viloyat;

    private JSONObject weather;
    private JSONObject wind;


    public void WeatherHandle(Message message, String text) throws IOException {
        String temp1 = text.substring(2);


        if (temp1.equals("samarkand")) {
            viloyat = "Samarkand";
        } else if (temp1.equals("tashkent")) {
            viloyat = "Tashkent";
        } else if (temp1.equals("naoiy")) {
            viloyat="Navoi";
        }else if (temp1.equals("qashqadaryo")) {
            viloyat="Qashqadaryo";
        }else if (temp1.equals("xorazm")) {
            viloyat="Andijan";
        }else if (temp1.equals("sirdaryo")) {
            viloyat="Sirdaryo";
        }else if (temp1.equals("andijon")) {
            viloyat="Andijan";
        } else if (temp1.equals("jizzax")) {
            viloyat="Jizzax";
        } else {
            viloyat = "Tashkent";
        }
        manage();


        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId());
        InputFile inputFile = new InputFile();
        inputFile.setMedia("AgACAgIAAxkBAAIEuGMe2tmIzSVwe-kDCh_kSHL7Vyt8AAIUuzEbU6L4SOFkkJ2GNsZwAQADAgADcwADKQQ");
        sendPhoto.setPhoto(inputFile);
        sendPhoto.setCaption(name + ": Shahri Ob-havo Malumoti\n\n" +
                "Urtacha: " + temp + "°\n\n" +
                "Minimal: " + temp_min + "°\n\n" +
                "Maximal: " + temp_max + "°\n\n\n" +
                "Namlik: " + humidity + "\n\n" +
                "Shamol: " + speed + "m/s\n\n" +
                "Bosim: " + bosim + "mm sim.ust\n\n" +
                "Description: " + description);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Boshqa Viloyatlarni Tanlash ");
        sendMessage.setReplyMarkup(InlineButtonUtil.keyboardMarkup(
                InlineButtonUtil.rowList(InlineButtonUtil.row(
                                InlineButtonUtil.button("Samarqand", "Wesamarkand"),
                                InlineButtonUtil.button("Jizzax", "Wejizzax"),
                                InlineButtonUtil.button("Sirdaryo", "Wesirdaryo")),
                        InlineButtonUtil.row(InlineButtonUtil.button("Navoi", "Wenaoiy"),
                                InlineButtonUtil.button("Xorazm", "Wexorazm"),
                                InlineButtonUtil.button("Qashqadaryo", "Weqashqadaryo")),
                        InlineButtonUtil.row(InlineButtonUtil.button("Andijon", "Weandijon"),
                                InlineButtonUtil.button("Tashkent", "Wetashkent")),
                        InlineButtonUtil.row(InlineButtonUtil.button("🔙 Backt to Menu", "menu"))
                )));


        myTelegramBot.send(sendPhoto);
        myTelegramBot.send(sendMessage);



    }


    public void manage() throws IOException {

        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + viloyat + "&APPID=99359aa7c82d931dc451734dea583180";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject obj_JSONObject = new JSONObject(response.toString());


        JSONArray jsonArray = obj_JSONObject.getJSONArray("weather");
        weather = (JSONObject) jsonArray.get(0);
        description = weather.getString("description");
        cloud = weather.getString("main");

        name = obj_JSONObject.getString("name");
        main = obj_JSONObject.getJSONObject("main");
        temp = main.getDouble("temp") - formula;
        temp_min = main.getDouble("temp_min") - formula;
        temp_max = main.getDouble("temp_max") - formula;
        bosim = main.getInt("pressure");
        humidity = main.getInt("humidity"); //namlik
        wind = obj_JSONObject.getJSONObject("wind");
        speed = wind.getDouble("speed"); //tezlik
        deg = wind.getDouble("deg"); //daraja


    }
}




