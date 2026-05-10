package com.example.taxicompany.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class TechSupportBot extends TelegramLongPollingBot {

    @Value("${app.botUsername}")
    private String botUsername;

    @Value("${app.botToken}")
    private String botToken;

    @Value("${app.adminChatid}")
    private String adminChatid;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long userChatID;
            System.out.println(update.getMessage().getChatId());
            if(update.getMessage().getChatId()==Long.parseLong(adminChatid)){
                String reply = update.getMessage().getText();
                userChatID = Long.parseLong(reply.split("\n")[0]);
                SendMessage sendMessage = new SendMessage();
                reply  = reply.replace(reply.split("\n")[0], " ");
                sendMessage.setChatId(userChatID);
                sendMessage.setText("Надійшла відповідь: " + reply + "\nВід модератора: " + update.getMessage().getFrom().getFirstName());
                try{
                    execute(sendMessage);
                }catch (TelegramApiException e){
                    e.printStackTrace();
                }
            }
            else {
                userChatID =update.getMessage().getChatId();
                String request = update.getMessage().getText();


                if(update.getMessage().getText().equals("/start")){
                    SendMessage sendRequest = new SendMessage();
                    sendRequest.setChatId(userChatID);
                    sendRequest.setText("Чим ми можемо допомогти?\n(Надамо відповідь за першої ж можливості)");
                    try {
                        execute(sendRequest);
                    }catch (TelegramApiException e){
                        e.printStackTrace();
                    }
                }

                SendMessage sendRequest = new SendMessage();
                sendRequest.setChatId(Long.parseLong(adminChatid));
                sendRequest.setText("Username: " + update.getMessage().getFrom().getUserName() +"\nChatID: "+ userChatID +"\n "+ request);
                try {
                    execute(sendRequest);
                }catch (TelegramApiException e){
                    e.printStackTrace();
                }
            }
        }
    }
}