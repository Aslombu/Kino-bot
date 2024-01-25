package org.example.Bot.ui;


import org.example.Bot.model.Kino;
import org.example.Bot.servis.KinoService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Reply {


    public SendMessage TelefonNumer(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "Telefon raqam kirtish ");

        KeyboardRow keyboardRow = new KeyboardRow();
        List<KeyboardRow> list = new ArrayList<>();
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText("\uD83D\uDCF1 Telfon nomer");
        keyboardButton.setRequestContact(true);
        keyboardRow.add(keyboardButton);
        list.add(keyboardRow);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(list);


        sendMessage.setReplyMarkup(replyKeyboardMarkup);


        return sendMessage;

    }

    public SendMessage MainMenyu(String chatId) {

        SendMessage sendMessage = new SendMessage(chatId, "MainMenyu");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> list = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(Text.kinoKodi);
        keyboardRow.add(Text.kinoIzlash);
        list.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(list);
        replyKeyboardMarkup.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);


        return sendMessage;
    }

    public SendMessage KinoLarKodiniKurish(String chatId) {
        KinoService kinoService = new KinoService();
        List<Kino> list = kinoService.kinolarListniQaytarish();
        String str = "";
        for (Kino kino : list) {
            str += "Kino kodi " + kino.getNumer() + "  Kino nomi" + kino.getVedioName() + "\n";
        }
        if (str.isEmpty()) {
            str = "proflaktika";
        }
        SendMessage sendMessage = new SendMessage(chatId, str);
        return sendMessage;

    }

    public SendMessage KinoIzlashutish(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "kino kodini kirting :");
        List<KeyboardRow> list = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(Text.Ortga);
        list.add(keyboardRow);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(list);
        replyKeyboardMarkup.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    public SendVideo kinoKodiBilanIzlash(String chatId, Kino text) {
        SendVideo sendVideo = new SendVideo(chatId, new InputFile(text.getVedioLink()));
        sendVideo.setCaption(text.getVedioName());
        return sendVideo;
    }

    public SendMessage kinoTopilmadi(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "codga mos kino topilmadi");
        return sendMessage;
    }

    public SendMessage adminlikuchunsurovpasword(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "iltimos parolingizni kirting :");
        return sendMessage;
    }

    public SendMessage adminmenyu(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "AdminMenyu");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> list = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(Text.Kinoqushish);
        keyboardRow.add(Text.KinoUchirish);
        list.add(keyboardRow);
        keyboardRow = new KeyboardRow();
        keyboardRow.add(Text.DasturchigaYozing);
        list.add(keyboardRow);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(list);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    public SendMessage KinoNominiSurash(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "");
        return sendMessage;
    }

    public SendMessage KinoRaqaminiSurash(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "Kino raqamini kirting:");
        return sendMessage;
    }

    public SendMessage Xatolik(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "xatolik Aniqlandi iltimos qayta kirting :");
        return sendMessage;
    }

    public SendMessage KinoForword(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "Vedio Tashlang :");
        return sendMessage;
    }

    public SendMessage kinoNominiKirtish(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "Kino nomini kirting :");
        return sendMessage;
    }

    public SendMessage KinoUchirish(String chatId, String text) {
        KinoService kinoService = new KinoService();
        Boolean tekshiruv = kinoService.kinoKodiniTekshirish(text);
        if (tekshiruv) {
            SendMessage sendMessage = new SendMessage(chatId,"Bu kodga mos kino topilmadi :");
            return sendMessage;
        }else {
            kinoService.KinoDelete(text);
            SendMessage sendMessage = new SendMessage(chatId,"Kino muvaqiyatli ucirildi");
            return sendMessage;
        }
    }
    public  SendPhoto DasturchilarBilan(String chatId) {
        SendPhoto sendPhoto = new SendPhoto(chatId,new InputFile(new File("src/rasm/Aslombu.jpg")));
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>>rows = new ArrayList<>();
        List<InlineKeyboardButton>list = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
//        inlineKeyboardButton.setText("Aslombu");
//        inlineKeyboardButton.setUrl("https://t.me/aslombu");
//        list.add(inlineKeyboardButton);
        inlineKeyboardButton.setSwitchInlineQuery("Aslombu");
        list.add(inlineKeyboardButton);
        rows.add(list);
        inlineKeyboardMarkup.setKeyboard(rows);


        sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
        sendPhoto.setCaption("Dasturchi bilan bog'lanish link ustuga bosing ");
        sendPhoto.setParseMode("Markdown");
        return sendPhoto;
    }



}

