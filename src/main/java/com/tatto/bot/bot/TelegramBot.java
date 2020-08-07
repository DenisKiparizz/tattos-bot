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

    private final static String NOT_FOUND_MESSAGE = "Unfortunately picture or style with this name doesn't exist";
    private final static String WELCOME_MESSAGE = "Welcome to tattoo Bot.\nFor interaction you can use keyboard bellow as well as you can input desired picture or style";

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
        getMaterialsPicture(update);
        sendMsg(
                update.getMessage().getChatId().toString(),
                message,
                keyboardMarkup);
    }

    private ReplyKeyboardMarkup getPictureKeyboard(String style) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow picture = new KeyboardRow();
        KeyboardRow styles = new KeyboardRow();
        tattooService.findByStyle(style).stream()
                .map(pic -> picture.add(pic.getPicture())).collect(Collectors.toList());
        styleService.getAll().stream()
                .map(st -> styles.add(st.getStyle())).collect(Collectors.toList());
        keyboard.add(picture);
        keyboard.add(styles);
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    private void getMaterialsPicture(Update update) {
        tattooDto = tattooService.findByPicture(update.getMessage().getText());
        if (tattooDto.size() > 0) {
            keyboardMarkup = new ReplyKeyboardMarkup();
            message = getInfoAboutSelectedPicture();
        } else {
            message = getMassageForMoving(update);
        }
    }

    private String getInfoAboutSelectedPicture() {
        return tattooDto.stream()
                .map(tattooDto1 ->
                        tattooDto1.getPicture() + "\n" +
                                tattooDto1.getDescription() + "\n" +
                                tattooDto1.getUrl() + "\n"
                )
                .map(String::valueOf)
                .collect(Collectors.joining("\n"));
    }


    private List<String> getStyle() {
        return styleService.getAll().stream()
                .map(StyleDto::getStyle)
                .collect(Collectors.toList());
    }

    private String getMassageForMoving(Update update) {
        List<String> styles = getStyle();
        if (styles.contains(update.getMessage().getText())) {
            return "You selected " + update.getMessage().getText() +
                    "\nSelect picture";
        } else if (update.getMessage().getText().equals("/start")) {
            return WELCOME_MESSAGE;
        } else {
            return NOT_FOUND_MESSAGE;
        }
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
