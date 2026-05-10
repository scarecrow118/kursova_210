package com.example.taxicompany.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class BotInitializer {

    private final TechSupportBot techSupportBot;

    public BotInitializer(TechSupportBot techSupportBot) {
        this.techSupportBot = techSupportBot;
        initBot();
    }

    private void initBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(techSupportBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
