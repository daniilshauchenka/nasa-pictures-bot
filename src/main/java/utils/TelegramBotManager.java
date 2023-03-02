package utils;


import entity.NasaObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;


public class TelegramBotManager extends TelegramLongPollingBot {

    public static final String BOT_TOKEN = "5992397872:AAFQfoORbRORUZ8NhA2tcluqD0KWKDRkG1k";

    public static final String BOT_USERNAME = "dshauchenka_nasa_pictures_bot";

    public static final String URI = "https://api.nasa.gov/planetary/apod?api_key=XBUnOudzrgaIXFfTr2rI4Ofmfnmj2VgsaynHu2b2";

    public static long chatId;

    public TelegramBotManager() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
        httpManager = new HttpManager();
    }

    private HttpManager httpManager;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            chatId = update.getMessage().getChatId();
            switch (update.getMessage().getText()){
                case "/help":
                    sendMessage("this is help\nbot token: "+getBotToken()+"\nchat id: "+getChatId());
                    break;
                case "/pic":
                    HttpManager httpManager = new HttpManager();
                    CloseableHttpResponse response = null;
                    try {
                        response = httpManager.executeRequest(URI);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    NasaObject nasaObject = null;
                    try {
                        nasaObject = httpManager.getMapper().readValue(response.getEntity().getContent(), NasaObject.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String message = nasaObject.toString();
                    sendMessage(message);
                    break;
                default:
                    sendMessage("command not found");
                    break;
            }
        }
    }

    private long getChatId() {
        return chatId;
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    private void sendMessage(String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        try {
            execute(message);
        } catch (TelegramApiException ex) {
            ex.printStackTrace();
        }

    }
}
