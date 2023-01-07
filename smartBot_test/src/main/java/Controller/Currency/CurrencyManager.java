package Controller.Currency;

import Controller.InlineButtonUtil;
import Controller.MyTelegramBot;
import org.checkerframework.checker.units.qual.C;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyManager {

    MyTelegramBot myTelegramBot;

    public CurrencyManager(MyTelegramBot myTelegramBot) {
        this.myTelegramBot = myTelegramBot;
    }



    private String valyuta = "USD";

    public void handle(Message message, String text) throws IOException {

        Currency currency = new Currency();

        String temp = text.substring(4);


        if (temp.equals("usd")) {
            valyuta = "USD";
        } else if (temp.equals("try")) {
            valyuta = "TRY";
        } else if (temp.equals("rub")) {
            valyuta = "RUB";
        } else if (temp.equals("eur")) {
            valyuta = "EUR";
        }

        String url = "https://cbu.uz/oz/arkhiv-kursov-valyut/json/" + valyuta + "/";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));


        String response;
        String outline = null;
        while ((response = in.readLine()) != null) {
            outline = response.replace("[", "").replace("]", "");
        }
        in.close();
//            System.out.println(response.toString());
        JSONObject obj_JSONObject = new JSONObject(outline.toString());

        currency.setCcy(obj_JSONObject.getString("Ccy"));
        currency.setCcyNm_EN(obj_JSONObject.getString("CcyNm_EN"));
        currency.setNominal(obj_JSONObject.getInt("Nominal"));
        currency.setRate(obj_JSONObject.getDouble("Rate"));
        currency.setDiff(obj_JSONObject.getDouble("Diff"));
       currency.setDate(obj_JSONObject.getString("Date"));


        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setChatId(message.getChatId());
        editMessageText.setText(currency.getCcy() + "\n\n" +
                "Currency: " + currency.getCcyNm_EN() + "\n\n" +
                "Nominal: " + currency.getNominal() +" " +currency.getCcy() + "\n\n" +
                "Rate: " + currency.getRate() + " so'm \n\n" +
                "Difference: " +currency.getDiff() + "\n\n" +
                "Date: " +currency.getDate());
        editMessageText.setReplyMarkup(InlineButtonUtil.keyboardMarkup(
                InlineButtonUtil.rowList(
                        InlineButtonUtil.row(InlineButtonUtil.button("\uD83D\uDCB5USD", "Cur/usd"),
                                InlineButtonUtil.button("\uD83D\uDCB2RUB", "Cur/rub")),
                        InlineButtonUtil.row(InlineButtonUtil.button("\uD83D\uDCB6EUR", "Cur/eur"),
                                InlineButtonUtil.button("\uD83D\uDCB0TRY", "Cur/try")),
                        InlineButtonUtil.row(InlineButtonUtil.button("🔙 Back to Menu","menu"))
                )
        ));

        myTelegramBot.send(editMessageText);
    }
}
