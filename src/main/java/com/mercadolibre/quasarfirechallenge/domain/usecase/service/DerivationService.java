package com.mercadolibre.quasarfirechallenge.domain.usecase.service;

import com.mercadolibre.quasarfirechallenge.domain.usecase.dto.Satellite;
import com.mercadolibre.quasarfirechallenge.domain.usecase.satellites.SatelliteBDD;
import com.mercadolibre.quasarfirechallenge.domain.usecase.satellites.SecretMessageResponse;
import com.mercadolibre.quasarfirechallenge.infrastructure.exception.MessagesException;
import com.mercadolibre.quasarfirechallenge.infrastructure.repository.SatellitesRepository;
import com.mercadolibre.quasarfirechallenge.infrastructure.util.MessageFormater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DerivationService {

    @Autowired
    PositionService positionService;

    @Autowired
    MessagesService messagesService;

    @Autowired
    SatellitesRepository satellitesRepository;

    public SecretMessageResponse topSecret(MessageFormater messageFormater) throws MessagesException {
        SecretMessageResponse secretMessageResponse = new SecretMessageResponse();

        secretMessageResponse.setDataPosition(positionService.decodingPosition(messageFormater));
        secretMessageResponse.setMessage(messagesService.getMessage(messageFormater.getMessages()));
        return secretMessageResponse;

    }


    public Satellite topSecretSplitPost(Satellite satellite, String satelliteName) throws MessagesException {
        Optional<SatelliteBDD> satellitesOptional = satellitesRepository.findById(satelliteName);
        if (satellitesOptional.isPresent()) {
            SatelliteBDD satelliteBDD = satellitesOptional.get();
            satelliteBDD.setDistance(satellite.getDistance());
            satelliteBDD.setMessage(String.join(",", satellite.getMessage()));
            satellitesRepository.save(satelliteBDD);
            return new Satellite(satelliteBDD);
        } else {
            throw new MessagesException("No se pudo procesar la información");
        }
    }

    public SecretMessageResponse topSecretSplit() throws MessagesException {
        List<SatelliteBDD> satelliteBDDList = satellitesRepository.findAll();
        List<Satellite> satelliteList = new ArrayList<>();
        satelliteBDDList.forEach(satelliteBDD -> satelliteList.add(new Satellite(satelliteBDD)));
        return topSecret(new MessageFormater(satelliteList));
    }
}
