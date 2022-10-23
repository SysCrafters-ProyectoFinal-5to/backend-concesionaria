package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Modelo;
import com.autos.concesionaria.service.ModeloService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/modelo")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ModeloController {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(ModeloController.class);

    @Autowired
    // Service injected by constructor
    private final ModeloService modeloService;

    // GET
    // Get mapping to get all the modelos
    @GetMapping
    public ResponseEntity<List<Modelo>> getModelos(@RequestParam(required = false) String marca, @RequestParam(required = false) String tipoVehiculo) {
        if (marca != null && tipoVehiculo != null) {
            logger.info("Obteniendo modelos por marca y tipo de vehiculo");
            return new ResponseEntity<>(modeloService.getModelosByMarcaAndTipoVehiculo(marca, tipoVehiculo), HttpStatus.OK);
        } else if (marca != null) {
            logger.info("Obteniendo modelos por marca");
            return new ResponseEntity<>(modeloService.getModelosByMarca(marca), HttpStatus.OK);
        } else if (tipoVehiculo != null) {
            logger.info("Obteniendo modelos por tipo de vehiculo");
            return new ResponseEntity<>(modeloService.getModelosByTipoVehiculo(tipoVehiculo), HttpStatus.OK);
        } else {
            logger.info("Obteniendo todos los modelos");
            return new ResponseEntity<>(modeloService.getModelos(), HttpStatus.OK);
        }
    }

    // GET by ID
    // Get mapping to get a modelo by id
    @GetMapping("/{id}")
    public ResponseEntity<Modelo> getModeloPorId(@PathVariable Long id) {
        logger.info("Obteniendo modelo con id: " + id);
        return new ResponseEntity<>(modeloService.getModelo(id), HttpStatus.OK);
    }

    // POST
    // Post mapping to create a modelo
    @PostMapping
    public ResponseEntity<Modelo> guardarModelo(@RequestBody Modelo modelo) {
        logger.info("Guardando modelo con nombre: " + modelo.getNombre());
        return new ResponseEntity<>(modeloService.crearModelo(modelo), HttpStatus.CREATED);
    }

    // PUT
    // Put mapping to update a modelo
    @PutMapping("/{id}")
    public ResponseEntity<Modelo> actualizarModelo(@PathVariable Long id, @RequestBody Modelo modelo) {
        logger.info("Actualizando modelo con id: " + id);
        return new ResponseEntity<>(modeloService.actualizarModelo(id, modelo), HttpStatus.OK);
    }

    // DELETE
    // Delete mapping to delete a modelo
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarModelo(@PathVariable Long id) {
        modeloService.borrarModelo(id);
        logger.info("Borrando modelo con id: " + id);
        return new ResponseEntity<String>("Modelo borrado: " + id, HttpStatus.NO_CONTENT);
    }

}
