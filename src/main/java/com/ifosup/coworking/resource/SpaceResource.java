package com.ifosup.coworking.resource;

import com.ifosup.coworking.domain.Space;
import com.ifosup.coworking.repository.SpaceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/spaces")
public class SpaceResource {

    private final SpaceRepository spaceRepository;

    public SpaceResource(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    @GetMapping("")
    public List<Space> getAllSpaces() {
        return spaceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Space getOneSpaces(@PathVariable("id") long id) {return  spaceRepository.findById(id).orElse(null);}

    @GetMapping("/cp/{code}")
    public List<Space> findByCode(@PathVariable("code") int code) {
        return spaceRepository.findByCode(code);}


}
