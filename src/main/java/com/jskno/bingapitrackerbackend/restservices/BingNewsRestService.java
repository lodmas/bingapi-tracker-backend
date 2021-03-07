package com.jskno.bingapitrackerbackend.restservices;

import com.jskno.bingapitrackerbackend.model.BingSearchResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class BingNewsRestService {

    private final static String SUBSCRIPTION_KEY = "73d3bc2ca8f94b3c897062496794e09a";
    private final static String HOST = "https://api.bing.microsoft.com";
    private final static String PATH = "/v7.0/news/search";
    private final static String SEARCH_TERM = "Microsoft";

    // "https://api.bing.microsoft.com/v7.0/news/search"
    private final static String SEARCH_URL = HOST + PATH;

    private RestTemplate restTemplate;

    @Autowired
    public BingNewsRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BingSearchResults searchNews(String... searchQuery) {

        String searchTerm = Arrays.stream(searchQuery)
                .map(s -> {
                    try {
                        return URLEncoder.encode(s, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        throw new IllegalArgumentException();
                    }
                })
                .collect(Collectors.joining("+"));

        LocalDateTime time = LocalDateTime.now().minusHours(18);
        ZoneId zoneId = ZoneId.systemDefault(); // or: ZoneId.of("Europe/Oslo");
        long epoch = time.atZone(zoneId).toEpochSecond();
        String stringTime = time.atZone(zoneId).toString();

        //adding the query params to the URL
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(SEARCH_URL)
                .queryParam("q", searchTerm + "+loc:es+language:es")
                .queryParam("mkt", "es-ES")
                .queryParam("count", 10000)
//                .queryParam("offset", 0)
                .queryParam("freshness", "Day")
//                .queryParam("originalImg", false)
                .queryParam("setLang", "es")
//                .queryParam("since", stringTime)
                .queryParam("sortBy", "Date");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Ocp-Apim-Subscription-Key", "73d3bc2ca8f94b3c897062496794e09a");

        HttpEntity<?> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<BingSearchResults> responseEntity = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                requestEntity,
                BingSearchResults.class);


        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            System.out.println("response received");
            System.out.println(responseEntity.getBody());
        } else {
            System.out.println("error occurred");
            System.out.println(responseEntity.getStatusCode());
        }

        return responseEntity.getBody();
    }
}
