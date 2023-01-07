package Controller;

import com.google.gson.Gson;
import com.sun.tools.javac.Main;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;




public class DogPhoto {

private MyTelegramBot myTelegramBot;

   public DogPhoto(MyTelegramBot myTelegramBot){
       this.myTelegramBot=myTelegramBot;
   }

   private String url;


    public void DogManager(Message message,String text) throws IOException {

        url=null;

        url="https://source.unsplash.com/1600x900/?beach";
        DogInfo dogInfo = new DogInfo();
        SendPhoto sendPhoto = new SendPhoto();
        InputFile inputFile = new InputFile();
        sendPhoto.setChatId(message.getChatId());
        inputFile.setMedia(url);

        sendPhoto.setPhoto(inputFile);
//            sendPhoto.setCaption("salom");

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Click ⏭ Next to download the image\n" +
                "Click 🔙 back to go menu");

        sendMessage.setReplyMarkup(InlineButtonUtil.keyboardMarkup(
                InlineButtonUtil.rowList(
                        InlineButtonUtil.row(
                                InlineButtonUtil.button("⏭ Next Photo","image"),
                                InlineButtonUtil.button("🔙 Back","menu"))
                )
        ));



        myTelegramBot.send(sendPhoto);
        myTelegramBot.send(sendMessage);

    }

    public static String  randomImageUrl() throws IOException {
        URL url = new URL("https://source.unsplash.com/random");

        URLConnection urlConnection = url.openConnection();

        InputStream stream = urlConnection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));


        StringBuilder sb = new StringBuilder();
        String line;

        while((line =br.readLine())!=null)

        {
            sb.append(line).append("\n");
        }
        br.close();

        String json = sb.toString();

        Gson gson = new Gson();
        DogInfo dogInfo = gson.fromJson(json, DogInfo.class);

        //        System.out.println(dogInfo);
        String result =dogInfo.getMessage();

        return result;

    }


}

class DogInfo {
    private String message;
    private String status;

    public DogInfo() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}


