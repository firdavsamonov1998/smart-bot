package Controller;

import com.google.gson.Gson;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainController {

    MyTelegramBot myTelegramBot;


    public MainController(MyTelegramBot myTelegramBot) {
        this.myTelegramBot = myTelegramBot;
    }


    public void handle(Message message) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        String text = message.getText();


        if (text.equals("/start")) {
            sendMessage.setText("SmartBotga xush kelibsiz");


            sendMessage.setReplyMarkup(InlineButtonUtil.keyboardMarkup(InlineButtonUtil.rowList(
                    InlineButtonUtil.row(
                            InlineButtonUtil.button("Go to Menu", "menu")
                    )
            )));


            myTelegramBot.send(sendMessage);
        }


    }

    public void handleCallback(Message message, String text) throws IOException {

        if (text.equals("menu")) {
            EditMessageText editMessageText = new EditMessageText();
            editMessageText.setChatId(message.getChatId());
            editMessageText.setMessageId(message.getMessageId());
            editMessageText.setText("Asosiy menu");

            editMessageText.setReplyMarkup(InlineButtonUtil.keyboardMarkup(
                    InlineButtonUtil.rowList(InlineButtonUtil.row(
                                    InlineButtonUtil.button("☁Weather", "weather"),
                                    InlineButtonUtil.button("🎓University List", "list")
                            ),
                            InlineButtonUtil.row(
                                    InlineButtonUtil.button("🐶Random Dog image", "image"),
                                    InlineButtonUtil.button("💵Currency", "currency")
                            ),
                            InlineButtonUtil.row(InlineButtonUtil.button("🕋Namoz time", "namoz"),
                                    InlineButtonUtil.button("👥Random Users","user")))

            ));

            myTelegramBot.send(editMessageText);

        }  else if (text.equals("weather")) {


        }
    }




}
