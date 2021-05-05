package com.mercadolibre.quasarfirechallenge.service;

import com.mercadolibre.quasarfirechallenge.domain.usecase.dto.Satellite;
import com.mercadolibre.quasarfirechallenge.domain.usecase.service.PositionService;
import com.mercadolibre.quasarfirechallenge.infrastructure.exception.MessagesException;
import com.mercadolibre.quasarfirechallenge.infrastructure.util.MessageFormater;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
class PositionServiceTest {

    @Mock
    Environment environment;

    @InjectMocks
    PositionService positionService;



    @Test
    void calculatePosition() throws MessagesException {

        MockitoAnnotations.initMocks(this);
        Satellite kenobi = new Satellite();
        Satellite skywalker = new Satellite();
        Satellite sato = new Satellite();
        List<String> message1 = Arrays.asList(new String[]{"Este", "", "", "mensaje", "de", ""});
        List<String> message2 = Arrays.asList(new String[]{"", "es", "", "", "", "emergencia"});
        List<String> message3 = Arrays.asList(new String[]{"", "es", "un", "", "de", ""});

        kenobi.setName("kenobi");
        kenobi.setDistance(100);
        kenobi.setMessage(message1);

        skywalker.setName("skywalker");
        skywalker.setDistance(115.5);
        skywalker.setMessage(message2);

        sato.setName("sato");
        sato.setDistance(142.7);
        sato.setMessage(message3);

        List<Satellite> satellites = new ArrayList<>();
        satellites.add(kenobi);
        satellites.add(skywalker);
        satellites.add(sato);
        
        MessageFormater request = new MessageFormater(satellites);

        Mockito.when(environment.getProperty(Mockito.eq("satellite.kenobi"))).thenReturn("-500,-200");
        Mockito.when(environment.getProperty(Mockito.eq("satellite.skywalker"))).thenReturn("100,-100");
        Mockito.when(environment.getProperty(Mockito.eq("satellite.sato"))).thenReturn("500,100");

        assertNotNull(positionService.decodingPosition(request));
    }
}