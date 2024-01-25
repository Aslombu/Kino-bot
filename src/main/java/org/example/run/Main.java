package org.example.run;

import lombok.SneakyThrows;
import org.example.Bot.MyBot.MyBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyBot("6513821661:AAFM0NrnilTDJt6_djY9lK_Y-KmZTXBYRRw"));
    }
}