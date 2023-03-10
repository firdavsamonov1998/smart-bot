package Controller;

import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NamozManager {

    MyTelegramBot myTelegramBot;

    public NamozManager(MyTelegramBot myTelegramBot) {
        this.myTelegramBot = myTelegramBot;
    }


    public void handle(Message message, String text) throws IOException {







            String url = "https://islomapi.uz/api/present/day?region=Toshkent";
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


            String region = obj_JSONObject.getString("region");
            String date = obj_JSONObject.getString("date");
            String weekday = obj_JSONObject.getString("weekday");
            JSONObject times = obj_JSONObject.getJSONObject("times");
            String tong =times.getString("tong_saharlik");
            String quyosh = times.getString("quyosh");
            String peshin = times.getString("peshin");
            String asr = times.getString("asr");
            String shom=times.getString("shom_iftor");
            String hufton = times.getString("hufton");

        SendPhoto sendPhoto = new SendPhoto();
        InputFile inputFile = new InputFile();
        sendPhoto.setChatId(message.getChatId());
        inputFile.setMedia("AgACAgIAAxkBAAICVmMd8q_9BEVsNGOkhUrVcQneab8sAAKxvzEbgPLQSILLctIerx8cAQADAgADcwADKQQ");
        sendPhoto.setPhoto(inputFile);
        sendPhoto.setCaption( "Toshkent shahri\n\n" +  "???"+date + "\n\n"+
                weekday +"\n\n"+"\uD83C\uDFD9"+ tong+"\uD83D\uDD70\n\n"
                +"\uD83C\uDF05\t"+quyosh +"\uD83D\uDD70\n\n"+"???\t"+ peshin+"\uD83D\uDD70\n\n"
                +"\uD83C\uDFDE\t"+ asr+"\uD83D\uDD70\n\n" +
                "\uD83C\uDF06\t" +shom + "\uD83D\uDD70\n\n" +
                "\uD83C\uDF03\t" + hufton +"\uD83D\uDD70\n\n" +
                "???????????????????? ?????????? ???????????????????????? ?????????????? ?????????? (????????????)\n" +
                "\n" +
                "??????????: ?????????????? (-1), ?????????????????? (-5), ???????????? (-6), ?????????? (-7), ???????????? (-7), ???????????????? (-10), ?????????????? (-10), ???????????????? (-10), ?????????????? (-11), ???? (-14), ?????????????????? (-15), ???????????? (-21),7 ???????? ??? ?????? (-21)\n" +
                "\n" +
                "??????????: ?????????????? (+4), ?????????????????? (+4), ???????????? (+6), ???????????????? (+7), ?????????? (+7), ???????????? (+7), ?????????????????? (+9), ?????????????????? (+10), ?????????????????????? (+12), ?????????? (+12), ???????????? (+14), ???????????? (+19), ???????????? (+21), ???????? (+35), ?????????? (+42)\n" +
                "\n" +
                "???????????????????? ?????????? ( ???????? ) ???? ?????????????? ???? ( ?????????????? ?????? ) ???????? ( ?????????????? ) ???????????????? ????????????!??? (????????, 132).\n" +
                "\n" +
                "\n" +
                "??? ?????????????? ???????????????????????????? ?????? ??????????????");


        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Bosh Menuga Qaytish");
        sendMessage.setReplyMarkup(InlineButtonUtil.keyboardMarkup(InlineButtonUtil.rowList(
                InlineButtonUtil.row(InlineButtonUtil.button("???? back to menu","menu"))
        )));


        myTelegramBot.send(sendPhoto);
        myTelegramBot.send(sendMessage);



            /*System.out.println("result after Reading JSON Response");
            System.out.println("statusCode- " + obj_JSONObject.getString("region"));
            St

            System.out.println("statusMessage- " + obj_JSONObject.getString("date"));
            System.out.println("countryCode- " + obj_JSONObject.getString("weekday"));
            System.out.println("awdaw- " + obj_JSONObject.getJSONObject("times"));
            System.out.println("ipAddress- " + obj_JSONObject.getInt("day"));
            JSONObject times = obj_JSONObject.getJSONObject("times");
            System.out.println(times.getString("peshin"));*/


//
        }



    }

