package Controller;


import Controller.Currency.CurrencyManager;
import Controller.UniversityManager.UniversityManager;
import Controller.UserPackage.UserManager;
import Controller.Weather.WeatherController;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.List;

public class MyTelegramBot extends TelegramLongPollingBot {
    private MainController mainController;
    private DogPhoto dogPhoto;
    private WeatherController weatherController;
    private NamozManager namozManager;

    private UserManager userManager;
    private CurrencyManager currencyManager;
    private UniversityManager universityManager;


    public MyTelegramBot() {
        this.mainController = new MainController(this);
        this.dogPhoto = new DogPhoto(this);
        this.weatherController = new WeatherController(this);
        this.namozManager = new NamozManager(this);
        this.userManager = new UserManager(this);
        this.currencyManager = new CurrencyManager(this);
        this.universityManager = new UniversityManager(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {

            if (update.hasMessage()) {

                Message message = update.getMessage();
                System.out.println(message.getPhoto());
                if (message.hasText()) {


                    mainController.handle(message);
                } else {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(message.getChatId());
                    sendMessage.setText("Unknown!");
                    send(sendMessage);
                }

            } else if (update.hasCallbackQuery()) {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                Message message = callbackQuery.getMessage();
                String query = callbackQuery.getData();
                if (query.equals("image")) {
                    dogPhoto.DogManager(message, query);
                } else if (query.equals("weather") || query.startsWith("We")) {
                    weatherController.WeatherHandle(message, query);

                } else if (query.equals("namoz")) {
                    namozManager.handle(message, query);

                } else if (query.equals("user")) {
                    userManager.handle(message);
                } else if (query.equals("currency") || query.startsWith("Cur")) {
                    currencyManager.handle(message, query);
                } else if (query.equals("list")) {
                    universityManager.handle(message);
                } else {
                    mainController.handleCallback(message, query);

                }

            }


        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void send(SendMessage sms) {
        try {
            execute(sms);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(EditMessageText sms) {
        try {
            execute(sms);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(SendPhoto sms) {
        try {
            execute(sms);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return "smartTestFirst_bot";
    }

    @Override
    public String getBotToken() {
        return "5510058623:AAHuwQdqlVAcQ2P9D0GLGDvzn1VkDdXoXJ4";
    }
}
