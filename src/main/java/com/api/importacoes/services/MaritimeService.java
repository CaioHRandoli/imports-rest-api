package com.api.importacoes.services;

import com.api.importacoes.controllers.MaritimeController;
import com.api.importacoes.dtos.MaritimeRecordDto;
import com.api.importacoes.models.MaritimeModel;
import com.api.importacoes.repositories.MaritimeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class MaritimeService {

    @Autowired
    private MaritimeRepository maritimeRepository;

    public MaritimeModel createMaritimeProcess(MaritimeRecordDto maritimeRecordDto) {
        var maritimeModel = new MaritimeModel();
        BeanUtils.copyProperties(maritimeRecordDto, maritimeModel);
        return maritimeRepository.save(maritimeModel);
    }

    public List<MaritimeModel> getAllMaritimeProcesses() {
        List<MaritimeModel> maritimeList = maritimeRepository.findAll();

        if(!maritimeList.isEmpty()) {
            for(MaritimeModel maritime : maritimeList) {
                UUID id = maritime.getIdMaritimeProcess();
                maritime.add(linkTo(methodOn(MaritimeController.class).getOneProcess(id)).withSelfRel());
            }
        }
        return maritimeList;
    }

    public ResponseEntity<Object> getMaritimeProcessById(UUID id) {
        Optional<MaritimeModel> maritimeO = maritimeRepository.findById(id);

        if(maritimeO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Process not found.");
        }

        MaritimeModel maritime = maritimeO.get();
        maritime.add(linkTo(methodOn(MaritimeController.class).getAllProcess()).withRel("Maritime List"));

        return ResponseEntity.status(HttpStatus.OK).body(maritime);
    }

    public ResponseEntity<Object> updateMaritimeProcess(UUID id, MaritimeRecordDto maritimeRecordDto) {
        Optional<MaritimeModel> maritimeO = maritimeRepository.findById(id);

        if(maritimeO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Process not found.");
        }

        MaritimeModel maritimeModel = maritimeO.get();
        BeanUtils.copyProperties(maritimeRecordDto, maritimeModel);
        MaritimeModel updatedProcess = maritimeRepository.save(maritimeModel);

        return ResponseEntity.status(HttpStatus.OK).body(updatedProcess);
    }

    public ResponseEntity<Object> deleteMaritimeProcess(UUID id) {
        Optional<MaritimeModel> maritimeO = maritimeRepository.findById(id);

        if(maritimeO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Process not found.");
        }

        maritimeRepository.delete(maritimeO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Process deleted successfully.");
    }
}
