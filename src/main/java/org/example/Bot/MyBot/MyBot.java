package org.example.Bot.MyBot;

import lombok.SneakyThrows;
import org.example.Bot.model.Kino;
import org.example.Bot.model.UserStatus;
import org.example.Bot.model.Users;
import org.example.Bot.servis.KinoService;
import org.example.Bot.servis.UserServis;
import org.example.Bot.ui.Reply;
import org.example.Bot.ui.Text;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;


public class MyBot extends TelegramLongPollingBot {
    public MyBot(String s) {
        super(s);
    }
    static   Kino kino = new Kino();
    @SneakyThrows
    @Override

    public void onUpdateReceived(Update update) {
        UserServis userServis = new UserServis();
        KinoService kinoService = new KinoService();
        Reply reply = new Reply();
        if (update.hasMessage()) {
            Message message = update.getMessage();
            String chatId = message.getChatId().toString();
            String text = message.getText();
            Users users = userServis.findByChatId(chatId);

            if (users == null) {
                if (message.hasContact()) {
                    Contact contact = message.getContact();
                    Users users1 = new Users();
                    users1.setChatId(contact.getUserId().toString());
                    users1.setUserName(message.getChat().getUserName());
                    users1.setUserStatus(UserStatus.REGESTRED);
                    users1.setPhoneNumer(contact.getPhoneNumber());
                    users1.setName(contact.getFirstName());
                    userServis.addUsers(users1);
                    execute(reply.MainMenyu(chatId));
                } else {
                    execute(reply.TelefonNumer(chatId));
                }
                return;
            }
            if (users.getUserStatus().equals(UserStatus.VEDIOFORWORD) && message.hasVideo()){
                text = message.getVideo().getFileId();
            }

            if (text.equals("/start") || users.getUserStatus().equals(UserStatus.KINOIZLASH) && text.equals(Text.Ortga)) {
                userServis.statusUptate(chatId, UserStatus.REGESTRED);
                execute(reply.MainMenyu(chatId));
            } else if (users.getUserStatus().equals(UserStatus.REGESTRED) && text.equals(Text.kinoKodi)) {
                execute(reply.KinoLarKodiniKurish(chatId));
            } else if (users.getUserStatus().equals(UserStatus.REGESTRED) && text.equals(Text.kinoIzlash)) {
                userServis.statusUptate(chatId, UserStatus.KINOIZLASH);
                execute(reply.KinoIzlashutish(chatId));
            } else if (users.getUserStatus().equals(UserStatus.KINOIZLASH)) {
                List<Kino> list = kinoService.bitaKinoQaytarish(text);
                if (list.isEmpty()) {
                    execute(reply.kinoTopilmadi(chatId));
                } else {
                    execute(reply.kinoKodiBilanIzlash(chatId, list.get(0)));
                }
            } else if (text.equals("/admin")) {
                userServis.statusUptate(chatId, UserStatus.ADMIN);
                execute(reply.adminlikuchunsurovpasword(chatId));
            } else if (users.getUserStatus().equals(UserStatus.ADMIN) && text.equals("ASLOMBU20021117$$")) {
                userServis.statusUptate(chatId, UserStatus.ADMINMENYU);
                execute(reply.adminmenyu(chatId));
            } else if (users.getUserStatus().equals(UserStatus.ADMINMENYU) && text.equals(Text.Kinoqushish)) {
                userServis.statusUptate(chatId, UserStatus.KINORAQAMI);
                execute(reply.KinoRaqaminiSurash(chatId));
            } else if (users.getUserStatus().equals(UserStatus.KINORAQAMI)) {
                Boolean tekshir = kinoService.kinoKodiniTekshirish(text);
                if (tekshir) {
                    userServis.statusUptate(chatId, UserStatus.VEDIOFORWORD);
                    kino.setNumer(text);
                    execute(reply.KinoForword(chatId));
                } else {
                    execute(reply.Xatolik(chatId));
                }
            } else if (users.getUserStatus().equals(UserStatus.VEDIOFORWORD)) {

                    userServis.statusUptate(chatId, UserStatus.KINONOMI);
                    kino.setVedioLink(text);
                    execute(reply.kinoNominiKirtish(chatId));

            } else if (users.getUserStatus().equals(UserStatus.KINONOMI)) {

                    kino.setVedioName(text);
                    kinoService.kinoAdd(kino);
                    userServis.statusUptate(chatId, UserStatus.ADMINMENYU);
                    execute(reply.adminmenyu(chatId));

            } else if (users.getUserStatus().equals(UserStatus.ADMINMENYU) && text.equals(Text.KinoUchirish)) {
                userServis.statusUptate(chatId,UserStatus.KINODELETE);
                     execute(reply.KinoIzlashutish(chatId));
            }else if (users.getUserStatus().equals(UserStatus.KINODELETE) && text.equals(Text.Ortga)){
                userServis.statusUptate(chatId,UserStatus.ADMINMENYU);
                execute(reply.adminmenyu(chatId));
            }else if (users.getUserStatus().equals(UserStatus.KINODELETE)) {

              execute(reply.KinoUchirish(chatId, text));
            }else if(users.getUserStatus().equals(UserStatus.ADMINMENYU) && text.equals(Text.DasturchigaYozing)){
                execute(reply.DasturchilarBilan(chatId));
            }


        }

    }

    @Override
    public String getBotUsername() {
        return "https://t.me/Chiroyli_kulguBot";
    }
}
