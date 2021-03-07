package com.jskno.bingapitrackerbackend.restservices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
public class BingNewsRestServiceTest {

    @Autowired
    private BingNewsRestService bingNewsRestService;

//    @Test
    public void testBingNewsRestService() {
        bingNewsRestService.searchNews("Espana", "inflacion");
    }
}
