
import Controller.MyTelegramBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;



public class Main {
    public static void main(String[] args) {
try {
    TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
    telegramBotsApi.registerBot(new MyTelegramBot());
}catch (TelegramApiException e){
    e.printStackTrace();
}











     /*  LocalDateTime today = LocalDateTime.now();
       LocalDateTime birthday = LocalDateTime.of(2001,6,12);
      int years=  Period.between().get;
//        System.out.println(years);*/
//        LocalDateTime start = LocalDateTime.;
//        LocalDateTime end = LocalDateTime.now(); // use for age-calculation: LocalDate.now()
//        long years = ChronoUnit.YEARS.between(start, end);
//        System.out.println(years); ;
    }
}