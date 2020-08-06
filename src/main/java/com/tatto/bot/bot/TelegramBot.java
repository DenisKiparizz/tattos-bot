package com.tatto.bot.bot;

import com.tatto.bot.dto.StyleDto;
import com.tatto.bot.dto.TattooDto;
import com.tatto.bot.service.impl.StyleServiceImpl;
import com.tatto.bot.service.impl.TattooServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${telegram.token}")
    private String TOKEN;
    @Value("${telegram.username}")
    private String USERNAME;

    public final TelegramBotsApi telegramBotsApi;
    public final TattooServiceImpl tattooService;
    public final StyleServiceImpl styleService;

    public static String message;
    public static ReplyKeyboardMarkup keyboardMarkup;
    public static List<TattooDto> tattooDto;

    @PostConstruct
    public void init() {
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            keyboardMarkup = getPictureKeyboard(update.getMessage().getText());
        }
        message = getMaterialsPicture(update);
        sendMsg(
                update.getMessage().getChatId().toString(), message, keyboardMarkup);
    }

    private ReplyKeyboardMarkup getPictureKeyboard(String style) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow picture = new KeyboardRow();
        KeyboardRow styles = new KeyboardRow();
        tattooService.findByStyle(style).stream()
                .map(pic -> picture.add(pic.getPicture()))
                .collect(Collectors.toList());
        styleService.getAll().stream()
                .map(st -> styles.add(st.getStyle()))
                .collect(Collectors.toList());
        keyboard.add(picture);
        keyboard.add(styles);
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    private String getMaterialsPicture(Update update) {
        tattooDto = tattooService.findByPicture(update.getMessage().getText());
        if (tattooDto.size() > 0) {
            message = tattooDto.stream()
                    .map(tattooDto1 ->
                            tattooDto1.getPicture() + "\n" +
                                    tattooDto1.getDescription() + "\n" +
                                    tattooDto1.getUrl() + "\n"
                    )
                    .map(String::valueOf)
                    .collect(Collectors.joining("\n"));
            keyboardMarkup = new ReplyKeyboardMarkup();
        } else {
            List<String> styles = styleService.getAll().stream()
                    .map(StyleDto::getStyle)
                    .collect(Collectors.toList());
            if (styles.contains(update.getMessage().getText())) {
                message = "You selected " + update.getMessage().getText() +
                        "\nSelect picture";
            } else if (update.getMessage().getText().equals("/start")) {
                message = "Hello mr";
            } else {
                message = "Unfortunately picture or style with this name doesn't exist";
            }
        }
        return message;
    }


    public synchronized void sendMsg(String chatId,
                                     String text,
                                     ReplyKeyboardMarkup keyboardMarkup) {
        SendMessage sendMessage = new SendMessage();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);
        sendMessage.setReplyMarkup(keyboardMarkup);
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return (USERNAME);
    }

    @Override
    public String getBotToken() {
        return (TOKEN);
    }
}
