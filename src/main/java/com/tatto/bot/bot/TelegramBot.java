package com.tatto.bot.bot;

import com.tatto.bot.dto.TattooDto;
import com.tatto.bot.service.TattooServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;
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
    public final TattooServiceImpl tattoService;

    @PostConstruct
    public void init() {
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return (TOKEN);
    }

    @Override
    public void onUpdateReceived(Update update) {
        List<TattooDto> tattooDto = tattoService.findByStyle(update.getMessage().getText());
        String message;
        if (tattooDto.size() > 0) {
            message = tattooDto.stream()
                    .map(tattooDto1 ->
                            tattooDto1.getPicture() + "\n" +
                                    tattooDto1.getDescription() + "\n" +
                                    tattooDto1.getUrl()+"\n")
                    .map(String::valueOf)
                    .collect(Collectors.joining("\n"));
        } else {
            message = update.getMessage().getText();
        }
        sendMsg(update.getMessage().getChatId().toString(), message);
    }

    public synchronized void sendMsg(String chatId, String text) {
        SendMessage sendMessage = new SendMessage();
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
}
