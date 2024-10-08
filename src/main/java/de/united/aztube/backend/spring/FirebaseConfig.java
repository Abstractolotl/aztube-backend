package de.united.aztube.backend.spring;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {


    @Bean
    public FirebaseApp firebaseApp(GoogleCredentials credentials) {
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    public FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }

    @Value("#{environment.GOOGLE_SERVICE_JSON}")
    private String googleServiceJson;

    @Bean
    public GoogleCredentials googleCredentials() throws IOException {
        InputStream is = new ByteArrayInputStream(googleServiceJson.getBytes(StandardCharsets.UTF_8));
        return GoogleCredentials.fromStream(is);
    }

}
