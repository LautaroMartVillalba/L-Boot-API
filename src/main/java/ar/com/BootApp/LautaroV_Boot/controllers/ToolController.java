package ar.com.BootApp.LautaroV_Boot.controllers;

import ar.com.BootApp.LautaroV_Boot.entities.tool.ToolEntiy;
import ar.com.BootApp.LautaroV_Boot.exceptions.tool.types.DuplicatedToolException;
import ar.com.BootApp.LautaroV_Boot.exceptions.tool.types.NullToolException;
import ar.com.BootApp.LautaroV_Boot.exceptions.tool.types.ToolEmptyDataBaseException;
import ar.com.BootApp.LautaroV_Boot.repositories.ToolRepository;
import ar.com.BootApp.LautaroV_Boot.services.ToolService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tool")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_MECHANIC', 'ROLE_ADMIN', 'ROLE_DEVELOPER')")
public class ToolController {

    private ToolService service;
    private ToolRepository repository;

    @GetMapping("/all")
    public ResponseEntity<List<ToolEntiy>> getAllTools() throws ToolEmptyDataBaseException {
        List<ToolEntiy> result = service.findAllTools();
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<Optional<ToolEntiy>> getById(@PathVariable Long id){
        Optional<ToolEntiy> result = service.findByToolID(id);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<ToolEntiy>> findByName(@PathVariable String name){
        List<ToolEntiy> result = service.findToolByName(name);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-company/{companyName}")
    public ResponseEntity<List<ToolEntiy>> findByCompany(@PathVariable String companyName){
        List<ToolEntiy> result = service.findByCompany(companyName);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-price/{min}/{max}")
    public ResponseEntity<List<ToolEntiy>> findByPrice(@PathVariable double min, @PathVariable double max){
        List<ToolEntiy> result = service.findToolByPriceBetween(min, max);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-name&company/{name}/{companyName}")
    public ResponseEntity<List<ToolEntiy>> findByNameAndCompany(@PathVariable String name,@PathVariable String companyName){
        List<ToolEntiy> result = service.findToolByNameAndCompany(name, companyName);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-name&price/{name}/{min}/{max}")
    public ResponseEntity<List<ToolEntiy>> findByNameAndPrice(@PathVariable String name, @PathVariable double min, @PathVariable double max){
        List<ToolEntiy> result = service.findToolByNameAndPrice(name, min,max);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-company&price/{company}/{min}/{max}")
    public ResponseEntity<List<ToolEntiy>> findByCompanyAndPrince(@PathVariable String company, @PathVariable double min, @PathVariable double max){
        List<ToolEntiy> result = service.findToolByCompanyAndPrice(company, min, max);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-availableTrue")
    public ResponseEntity<List<ToolEntiy>> findByAvailableTrue(){
        List<ToolEntiy> result = repository.findByAvailableTrue();
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-availableFalse")
    public ResponseEntity<List<ToolEntiy>> findByAvailableFalse(){
        List<ToolEntiy> result = repository.findByAvailableFalse();
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/insert")
    public ResponseEntity<ToolEntiy> insertTool(@RequestBody ToolEntiy tool) throws DuplicatedToolException, NullToolException {
        boolean result = service.saveTool(tool);
        if (!result){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tool);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DEVELOPER')")
    public ResponseEntity<Boolean> deleteTool(@PathVariable  Long id){
        boolean result = service.deleteToolByID(id);
        if (!result){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
