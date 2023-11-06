package cat.itacademy.barcelonactiva.abdellaoi.fethi.s05.t01.n03.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;

import cat.itacademy.barcelonactiva.abdellaoi.fethi.s05.t01.n03.model.dto.FlorDTO;
import cat.itacademy.barcelonactiva.abdellaoi.fethi.s05.t01.n03.model.services.FlorService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/flor")
public class FlorController {	
	
    private FlorService florService;
    
    @PostMapping("/clientFlorsAdd")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FlorDTO> create(@RequestBody FlorDTO f) {
    	return florService.addFlor(f);
    }
    
    @PutMapping("/clientFlorsUpdate")
    @ResponseStatus(HttpStatus.OK)
    public Mono<FlorDTO> update(@RequestBody FlorDTO fe) {
        return florService.update(fe);
    }
    
    @DeleteMapping("clientFlorsDelete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> delete(@PathVariable("id") Integer id) {
    	return florService.delete(id);
    }
        
    @GetMapping("clientFlorsGetOne/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<FlorDTO> findById(@PathVariable("id") Integer id) {
        return florService.findById(id);
    }
    
    @GetMapping("/clientFlorsAll")
    @ResponseStatus(HttpStatus.OK)
    public Flux<FlorDTO> findAll() {
    	return florService.findAll();
    }
 
}
