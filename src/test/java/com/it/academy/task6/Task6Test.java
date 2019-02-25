package com.it.academy.task6;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Runner.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@NoArgsConstructor
public class Task6Test {

    @Autowired
    private RestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void getBooks() {
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://127.0.0.1:" + port + "/task6/books/download")
                .queryParam("authorNames", "Herbert George Wells")
                .queryParam("genres", "adventure", "fantastic")
                .queryParam("fromYear", 1)
                .queryParam("tillYear", 2000);

        ResponseEntity<byte[]> response = restTemplate.exchange(builder.build().toUri(),
                HttpMethod.GET,
                entity
                , byte[].class);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        System.out.println(new String(Objects.requireNonNull(response.getBody())));
    }

}
