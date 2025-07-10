package com.api.importacoes.controllers;

import com.api.importacoes.dtos.MaritimeRecordDto;
import com.api.importacoes.models.MaritimeModel;
import com.api.importacoes.services.MaritimeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/maritime-process")
public class MaritimeController {

    @Autowired
    private MaritimeService maritimeService;

    @PostMapping
    public ResponseEntity<MaritimeModel> createMaritimeProcess(@RequestBody @Valid MaritimeRecordDto maritimeRecordDto) {
        MaritimeModel savedProcess = maritimeService.createMaritimeProcess(maritimeRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProcess);
    }

    @GetMapping
    public ResponseEntity<List<MaritimeModel>> getAllProcess() {
        return ResponseEntity.status(HttpStatus.OK).body(maritimeService.getAllMaritimeProcesses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProcess(@PathVariable(value = "id") UUID id) {
        return maritimeService.getMaritimeProcessById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProcess(@PathVariable(value = "id") UUID id, @RequestBody @Valid MaritimeRecordDto maritimeRecordDto) {
        return maritimeService.updateMaritimeProcess(id, maritimeRecordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProcess(@PathVariable(value = "id") UUID id) {
        return maritimeService.deleteMaritimeProcess(id);
    }
}
