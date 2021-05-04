package com.mercadolibre.quasarfirechallenge.infrastructure.delivery.rest;

import com.mercadolibre.quasarfirechallenge.domain.usecase.dto.Satellite;
import com.mercadolibre.quasarfirechallenge.domain.usecase.satellites.SecretMessageResponse;
import com.mercadolibre.quasarfirechallenge.domain.usecase.service.DerivationService;
import com.mercadolibre.quasarfirechallenge.infrastructure.exception.MessagesException;
import com.mercadolibre.quasarfirechallenge.infrastructure.util.MessageFormater;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Controlador que provee servicios para el cumplimiento de la Operación fuego de Quasar
 */
@Api(value = "OperationController: Controlador que provee servicios para el cumplimiento de la Operación fuego de Quasar" )
@RestController
@RequestMapping("/")
public class SecretMessageController {

    @Autowired
    DerivationService derivationService;

    /**
     * Nivel 2 de la operación fuego de Quasar
     * @param request
     * @return
     */
    @ApiOperation(value = "Nivel 2 de la operación fuego de Quasar ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PostMapping(value="/topsecret", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SecretMessageResponse> topSecret(@RequestBody MessageFormater request) {
        try {
            SecretMessageResponse result = derivationService.topSecret(request);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (MessagesException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    /**
     * Nivel 3 de la Operación fuego de Quasar, servicio POST
     * @param satelliteName
     * @param request
     * @return
     */
    @ApiOperation(value = "Nivel 3 de la Operación fuego de Quasar, servicio POST ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PostMapping(value="/topsecret_split/{satellite_name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Satellite> topSecretSplit(@PathVariable("satellite_name") String satelliteName
            , @RequestBody Satellite request) {
        try {
            Satellite result = derivationService.topSecretSplitPost(request, satelliteName);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (MessagesException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Nivel 3 de la Operación fuego de Quasar, servicio GET
     * @return
     */
    @ApiOperation(value = "Nivel 3 de la Operación fuego de Quasar, servicio GET ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping(value = "/topsecret_split", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SecretMessageResponse> topSecretSplitGet() {
        SecretMessageResponse result;
        try {
            result = derivationService.topSecretSplit();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (MessagesException e) {
            result = new SecretMessageResponse();
            result.setMessage("No hay suficiente información");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

}
