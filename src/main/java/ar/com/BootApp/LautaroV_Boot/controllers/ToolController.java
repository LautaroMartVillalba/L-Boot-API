package ar.com.BootApp.LautaroV_Boot.controllers;

import ar.com.BootApp.LautaroV_Boot.entities.tool.Tool;
import ar.com.BootApp.LautaroV_Boot.exceptions.tool.types.DuplicatedToolException;
import ar.com.BootApp.LautaroV_Boot.exceptions.tool.types.NullToolException;
import ar.com.BootApp.LautaroV_Boot.exceptions.tool.types.ToolEmptyDataBaseException;
import ar.com.BootApp.LautaroV_Boot.repositories.ToolRepository;
import ar.com.BootApp.LautaroV_Boot.services.ToolService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tool")
@AllArgsConstructor
public class ToolController {

    private ToolService service;
    private ToolRepository repository;

    @GetMapping("/all")
    public ResponseEntity<List<Tool>> getAllTools() throws ToolEmptyDataBaseException {
        List<Tool> result = service.findAllTools();
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<Optional<Tool>> getById(@PathVariable Long id){
        Optional<Tool> result = service.findByToolID(id);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<Tool>> findByName(@PathVariable String name){
        List<Tool> result = service.findToolByName(name);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-company/{companyName}")
    public ResponseEntity<List<Tool>> findByCompany(@PathVariable String companyName){
        List<Tool> result = service.findByCompany(companyName);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-price/{min}/{max}")
    public ResponseEntity<List<Tool>> findByPrice(@PathVariable double min, @PathVariable double max){
        List<Tool> result = service.findToolByPriceBetween(min, max);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-name&company/{name}/{companyName}")
    public ResponseEntity<List<Tool>> findByNameAndCompany(@PathVariable String name,@PathVariable String companyName){
        List<Tool> result = service.findToolByNameAndCompany(name, companyName);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-name&price/{name}/{min}/{max}")
    public ResponseEntity<List<Tool>> findByNameAndPrice(@PathVariable String name, @PathVariable double min, @PathVariable double max){
        List<Tool> result = service.findToolByNameAndPrice(name, min,max);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-company&price/{company}/{min}/{max}")
    public ResponseEntity<List<Tool>> findByCompanyAndPrince(@PathVariable String company, @PathVariable double min, @PathVariable double max){
        List<Tool> result = service.findToolByCompanyAndPrice(company, min, max);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-availableTrue")
    public ResponseEntity<List<Tool>> findByAvailableTrue(){
        List<Tool> result = repository.findByAvailableTrue();
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-availableFalse")
    public ResponseEntity<List<Tool>> findByAvailableFalse(){
        List<Tool> result = repository.findByAvailableFalse();
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/insert")
    public ResponseEntity<Tool> insertTool(@RequestBody Tool tool) throws DuplicatedToolException, NullToolException {
        boolean result = service.saveTool(tool);
        if (!result){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tool);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteTool(@PathVariable  Long id){
        boolean result = service.deleteToolByID(id);
        if (!result){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
