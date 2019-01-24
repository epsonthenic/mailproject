package com.example.mail_project.controller;

import com.example.mail_project.MailProjectApplication;
import com.example.mail_project.service.AppMailDataService;
import com.example.mail_project.util.ListKeyword;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;

import com.google.common.io.ByteStreams;
import com.linecorp.bot.client.MessageContentResponse;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import lombok.Value;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@LineMessageHandler
public class LineBotController {

    public String sender_Totext = "";
    public String Subjecttext = "";
    public String msgtext = "";
    public String imgline = "";

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LineMessagingClient lineMessagingClient;

    @Autowired
    private AppMailDataService appMailDataService;


    @EventMapping //รับข้อความ
    public void handleTextMessage(MessageEvent<TextMessageContent> event) {
        TextMessageContent message = event.getMessage();
        String replyToken = event.getReplyToken();
        String userId = event.getSource().getUserId();
        String text = message.getText();
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        //DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime("yyyy-MM-dd ");
        //DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        OffsetDateTime localDate = OffsetDateTime.now();
        boolean hasText = text.contains("@");
        boolean hasText1 = text.contains("NOT");
        boolean hasText2 = text.contains("เนื้อหา;");
        boolean hasText3 = text.contains("@END");
        String pattern = "(@*+\\S+)(.*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(text);
        if((ListKeyword.checkTextMatches(text) == true) && (hasText == false)){
            reply(replyToken, Arrays.asList(
                    new TextMessage("ระบบได้ทำการบันทึกแล้ว")
            ));
            String json = "{\"sender\" : \"-\" , \"send_To\" : \"SS\", \"subject\" : \"-\" , \"email\":\"-\", \"msg\" : \"" + text + "\" , \"attachments\" : \"-\" , \"responsible\" : \"-\" , \"sentDate\" : \"" + dtf.format(localDate) + "\" , \"status\" : \"wait\" , \"type\" : \"LINE\" , \"level\":\"0\", \"idline\" : \"" + userId + "\"}";
            appMailDataService.SaveLineText(json);
            //appMailDataService.SaveByJsonCus(JsonList.get(i));
        }
        else if ((hasText == true) && (ListKeyword.checkTextMatches(text) == true)) {
            if (userId != null) {
                if (m.find()) {
                    sender_Totext = m.group(1);
                    //Subjecttext = m.group();
                    msgtext = m.group(2);
                    lineMessagingClient.getProfile(userId)
                            .whenComplete((profile, throwable) -> {
                                reply(replyToken, Arrays.asList(
                                        new TextMessage("ระบบได้ทำการบันทึกแล้ว")
                                ));
                                String json = "{\"sender\" : \"-\" , \"send_To\" : \"" + sender_Totext + "\", \"subject\" : \"-\" , \"email\":\"-\", \"msg\" : \"" + msgtext + "\" , \"attachments\" : \"-\" , \"responsible\" : \"-\" , \"sentDate\" : \"" + dtf.format(localDate) + "\" , \"status\" : \"wait\" , \"type\" : \"LINE\" , \"level\":\"0\", \"idline\" : \"" + userId + "\"}";
                                appMailDataService.SaveLineText(json);
                            });
                }
            }
        }
    }

    /////////////////////////////รูปภาพ////////////////////////////////
    @EventMapping // รับส่งรูปภาพ
    public void handleImageMessage(MessageEvent<ImageMessageContent> event) {
        ImageMessageContent message = event.getMessage();
        String replyToken = event.getReplyToken();
        String userId = event.getSource().getUserId();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        OffsetDateTime localDate = OffsetDateTime.now();
        try {
            MessageContentResponse response = lineMessagingClient.getMessageContent(
                    message.getId()).get();
            DownloadedContent jpg = saveContent("jpg", response);
            String pattern = "(.*downloaded/)(.*)";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(jpg.getUri());
            if (userId != null) {
                if (m.find()) {
                    //Subjecttext = m.group();
                    imgline = m.group(2);
                    lineMessagingClient.getProfile(userId)
                        .whenComplete((profile, throwable) -> {
                            reply(replyToken, Arrays.asList(
                                    new TextMessage("บันทึกแล้ว")
                            ));
                            String jsonn = "{ \"imgg\" : \"" + imgline + "\",\"idline\":\"" + userId + "\",\"sentDate\":\"" + dtf.format(localDate) + "\"}";
                            appMailDataService.SaveLineImg(jsonn);
                        });
            }
        }
        } catch (InterruptedException | ExecutionException e) {
            reply(replyToken, new TextMessage("กรุณาลองใหม่อีกครั้ง"));
            throw new RuntimeException(e);
        }
    }

    private static DownloadedContent saveContent(String ext,
                                                 MessageContentResponse response) {
        DownloadedContent tempFile = createTempFile(ext);
        try (OutputStream outputStream = Files.newOutputStream(tempFile.path)) {
            ByteStreams.copy(response.getStream(), outputStream);
            return tempFile;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static DownloadedContent createTempFile(String ext) {
        String fileName = LocalDateTime.now() + "@"
                + UUID.randomUUID().toString()
                + "." + ext;
        Path tempFile = MailProjectApplication.downloadedContentDir.resolve(fileName);
        tempFile.toFile().deleteOnExit();
        return new DownloadedContent(tempFile, createUri("/downloaded/" + tempFile.getFileName()));
    }

    private static String createUri(String path) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(path).toUriString();
    }

    @Value
    public static class DownloadedContent {

        Path path;
        String uri;
    }

    private void reply(@NonNull String replyToken, @NonNull Message message) {
        reply(replyToken, Collections.singletonList(message));
    }

    private void reply(@NonNull String replyToken, @NonNull List<Message> messages) {
        try {
            BotApiResponse response = lineMessagingClient.replyMessage(
                    new ReplyMessage(replyToken, messages)
            ).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
