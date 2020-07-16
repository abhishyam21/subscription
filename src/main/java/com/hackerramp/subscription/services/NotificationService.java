package com.hackerramp.subscription.services;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class NotificationService {
    public void sendNotification(String title, String text) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        MediaType mediaType = MediaType.parse("application/json");
        MediaType.parse("application/json");
        RequestBody responseBody = RequestBody.create(crateNotificationObject(title,text),
                mediaType);
        Request request = new Request.Builder()
                .url("https://fcm.googleapis.com/fcm/send")
                .method("POST", responseBody)
                .addHeader("Authorization", "key=AAAAf-1n1Sw:APA91bHv8Ru6jGz4NMAVzQPKf9GzDcxyiz-ZRsdfq51_GDhVbXnTUPTuOc9Nxm8TSA5CiQ_pRHTSunQOPrlp2o4WVAvunmC128XNxtaWVRWD0cbzt-6BcYhfuog5WdCxZsnYkjxQYFq4")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body());
    }

    private String crateNotificationObject(String title, String text) {
        String body = "{\n    \"notification\": {\n       " +
                " \"title\": \"%s\",\n        \"text\": \"%s\"\n    },\n   " +
                " \"condition\": \"!('Notopic' in topics)\"\n}";
        return String.format(body, title, text);
    }
}
