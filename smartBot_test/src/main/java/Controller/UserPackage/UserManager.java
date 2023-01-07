package Controller.UserPackage;

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
import java.util.LinkedList;
import java.util.List;

public class UserManager {


    private List<Profile> profileList = new LinkedList<>();

    private Status nameSt = Status.START;
    private Status locationSt = Status.START;
    private Status dobSt = Status.START;
    private Status pictureSt = Status.START;
    private MyTelegramBot myTelegramBot;
    private Profile profile = new Profile();


    public UserManager(MyTelegramBot myTelegramBot) {
        this.myTelegramBot = myTelegramBot;
    }


    public void handle(Message message) throws IOException {


        String url = "https://randomuser.me/api/";
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
        JSONArray jsonArray = obj_JSONObject.getJSONArray("results");
        JSONObject temp1 = jsonArray.getJSONObject(0);
        profile.setGender(temp1.getString("gender"));
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject temp = jsonArray.getJSONObject(i);
            if (temp.has("name") && nameSt.equals(Status.START)) {
                profile.setName(temp.getJSONObject("name"));
                profile.setFirstName(profile.getName().getString("first"));
                profile.setLastName(profile.getName().getString("last"));

                System.out.println(profile.getName());
                System.out.println(profile.getFirstName());
                i--;
                nameSt = Status.FINISH;
            } else if (temp.has("location") && locationSt.equals(Status.START)) {
                profile.setLocation(temp.getJSONObject("location"));
                profile.setCountry(profile.getLocation().getString("country"));
                profile.setCity(profile.getLocation().getString("city"));
                i--;
                locationSt = Status.FINISH;
            } else if (temp.has("dob") && dobSt.equals(Status.START)) {
                profile.setDob(temp.getJSONObject("dob"));
                profile.setBirthday(profile.getDob().getString("date"));
                profile.setAge(profile.getDob().getInt("age"));
                i--;
                dobSt = Status.FINISH;
            } else if (temp.has("picture") && pictureSt.equals(Status.START)) {
                profile.setPicture(temp.getJSONObject("picture"));
                profile.setLarge(profile.getPicture().getString("large"));
                i--;
                pictureSt = Status.FINISH;
            }
        }


        StringBuilder imogi;
        if (profile.getGender().equals("male")) {
            imogi = new StringBuilder("\uD83D\uDC68\uD83C\uDFFB\u200D\uD83E\uDDB0");
        } else {
            imogi = new StringBuilder("\uD83D\uDC71\uD83C\uDFFB\u200D♀️");
        }

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId());
        InputFile inputFile = new InputFile();
        inputFile.setMedia(profile.getLarge());
        sendPhoto.setPhoto(inputFile);

        sendPhoto.setCaption("Firstname: \t" + profile.getFirstName() + "\n\n" +
                "LastName: \t" + profile.getLastName() + "\n\n" +
                imogi + "Gender: \t" + profile.getGender() + "\n\n" +
                "\uD83C\uDFE2Country: \t" + profile.getCountry() + "\n\n" +
                "\uD83C\uDF06City: \t" + profile.getCity() + "\n\n" +
                "\uD83D\uDCE7Birthdate: " + profile.getBirthday() + "\n\n" +
                "Age: " + profile.getAge());


        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Bosh Menuga Qaytish");
        sendMessage.setReplyMarkup(InlineButtonUtil.keyboardMarkup(InlineButtonUtil.rowList(
                InlineButtonUtil.row(InlineButtonUtil.button("⏭Next Random User", "user"),
                        InlineButtonUtil.button("🔙 back to menu", "menu"))
        )));


        myTelegramBot.send(sendPhoto);
        myTelegramBot.send(sendMessage);



    }
}



