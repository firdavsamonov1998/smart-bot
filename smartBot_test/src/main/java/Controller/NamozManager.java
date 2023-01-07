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
        sendPhoto.setCaption( "Toshkent shahri\n\n" +  "☪"+date + "\n\n"+
                weekday +"\n\n"+"\uD83C\uDFD9"+ tong+"\uD83D\uDD70\n\n"
                +"\uD83C\uDF05\t"+quyosh +"\uD83D\uDD70\n\n"+"☀\t"+ peshin+"\uD83D\uDD70\n\n"
                +"\uD83C\uDFDE\t"+ asr+"\uD83D\uDD70\n\n" +
                "\uD83C\uDF06\t" +shom + "\uD83D\uDD70\n\n" +
                "\uD83C\uDF03\t" + hufton +"\uD83D\uDD70\n\n" +
                "Тошкентдан бошқа шаҳарлардаги вақтлар фарқи (дақиқа)\n" +
                "\n" +
                "Аввал: Чимкент (-1), Конибодом (-5), Хўжанд (-6), Қўқон (-7), Жамбул (-7), Наманган (-10), Фарғона (-10), Марғилон (-10), Андижон (-11), Ўш (-14), Жалолобод (-15), Бишкек (-21),7 Олма – ота (-21)\n" +
                "\n" +
                "Кейин: Бекобод (+4), Туркистон (+4), Жиззах (+6), Гулистон (+7), Денов (+7), Жомбой (+7), Самарқанд (+9), Шаҳрисабз (+10), Каттақўрғон (+12), Қарши (+12), Нурота (+14), Навоий (+19), Бухоро (+21), Хива (+35), Нукус (+42)\n" +
                "\n" +
                "Аҳлингизни намоз ( ўқиш ) га буюринг ва ( ўзингиз ҳам ) унга ( намозга ) бардошли бўлинг!” (Тоҳа, 132).\n" +
                "\n" +
                "\n" +
                "✅ Албатта яқинларингизга ҳам юборинг");


        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Bosh Menuga Qaytish");
        sendMessage.setReplyMarkup(InlineButtonUtil.keyboardMarkup(InlineButtonUtil.rowList(
                InlineButtonUtil.row(InlineButtonUtil.button("🔙 back to menu","menu"))
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

