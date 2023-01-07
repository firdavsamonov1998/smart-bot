package Controller.UniversityManager;

import Controller.InlineButtonUtil;
import Controller.MyTelegramBot;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UniversityManager {

    University university = new University();
    MyTelegramBot myTelegramBot;

    public UniversityManager(MyTelegramBot myTelegramBot) {
        this.myTelegramBot = myTelegramBot;
    }


    public void handle(Message message) throws IOException {


        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Universitutlar haqida ma'lumot");


        sendMessage.setText(university.getDomains() + "\n\n" +
                university.getCountry() + "\n\n" +
                university.getWe_pages() + "\n\n" +
                university.getName() + "\n\n");
        myTelegramBot.send(sendMessage);


        sendMessage.setReplyMarkup(InlineButtonUtil.keyboardMarkup(InlineButtonUtil.rowList(
                InlineButtonUtil.row(InlineButtonUtil.button("🔙 Backt to menu", "menu"))
        )));

        myTelegramBot.send(sendMessage);


    }

    public void manage() throws IOException {

        String url = "http://universities.hipolabs.com/search?country=Uzbekistan";
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

        JSONArray obj_JSONObject = new JSONArray(response.toString());

        for (int i = 0; i < obj_JSONObject.length(); i++) {
            JSONObject temp = obj_JSONObject.getJSONObject(i);
            university.setDomains(temp.getJSONArray("domains"));
            university.setCountry(temp.getString("country"));
            university.setWe_pages(temp.getJSONArray("web_pages"));
            university.setName(temp.getString("name"));

               /* String domain= String.valueOf(domains);
                String web_page= String.valueOf(we_pages);*/

        }


    }

}

