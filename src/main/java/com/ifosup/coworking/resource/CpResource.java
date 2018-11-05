package com.ifosup.coworking.resource;

import com.ifosup.coworking.domain.Cp;
import com.ifosup.coworking.repository.CpRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cps")
public class CpResource {

    private final CpRepository cpRepository;

    public CpResource(CpRepository cpRepository) {
        this.cpRepository = cpRepository;
    }

    @GetMapping("")
    public List<Cp> getAllCps() {
        return cpRepository.findAll();
    }

    @GetMapping("/{code}")
    public List<Cp> findByCode(@PathVariable("code") int code) {
    return cpRepository.findByCode(code);}

    @GetMapping("/city/{city}")
    public List<Cp> findByCity(@PathVariable("city") String city) {
        return cpRepository.findByCity(city);}

}
