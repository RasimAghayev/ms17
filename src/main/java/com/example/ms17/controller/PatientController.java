package com.example.ms17.controller;

import com.example.ms17.dto.PatientDto;
import com.example.ms17.model.onetone.Patient;
import com.example.ms17.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
@Slf4j
public class PatientController {
    private final PatientService patientService;

    @PostMapping("")
    public PatientDto save(@RequestBody PatientDto patientDto){
        return patientService.save(patientDto);
    }

    @GetMapping("")
    public List<PatientDto> findAll(){
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> findById(@PathVariable long id){
        return ResponseEntity.ok(patientService.findById(id));
    }

    @PutMapping("/{id}")
    public PatientDto edit(@PathVariable long id,@RequestBody PatientDto patientDto){
        return patientService.edit(id, patientDto);
    }

    @DeleteMapping("/{id}")
    public Patient deleteById(@PathVariable long id){
        return patientService.deleteById(id);
    }

}
