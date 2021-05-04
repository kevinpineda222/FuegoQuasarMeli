package com.mercadolibre.quasarfirechallenge.domain.usecase.service;

import com.mercadolibre.quasarfirechallenge.infrastructure.exception.MessagesException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio que permite procesar la información de los satélites y obtener el mensaje
 */
@Service
public class MessagesService {

    /**
     * Procesa los mensajes recibidos de cada satélite
     * @param messages Mensajes recibidos
     * @return Mensaje compilado
     * @throws MessagesException
     */
    public String getMessage(List<List<String>> messages) throws MessagesException {
        if (messages.size() != 3)
            throw new MessagesException("Son necesarios al menos tres mensajes");

        if (!sizeEquals(messages))
            throw new MessagesException("Los mensajes deben tener el mismo tamaño");

        return compileMessage(messages);
    }

    private boolean sizeEquals(List<List<String>> messages) {
        int size = messages.get(0).size();
        if (size == 0)
            return false;
        for (List<String> message : messages) {
            if (message.size() != size)
                return false;
        }
        return true;
    }

    private String compileMessage(List<List<String>> messages) {
        List<String> compiledMessage = new ArrayList<>();
        for (List<String> message : messages) {
            for (int i = 0; i < message.size(); i++) {
                if (compiledMessage.size() <= i)
                    compiledMessage.add("");
                if (!"".equals(message.get(i))) {
                    compiledMessage.set(i, message.get(i));
                }
            }
        }

        return String.join(" ", compiledMessage);
    }

}