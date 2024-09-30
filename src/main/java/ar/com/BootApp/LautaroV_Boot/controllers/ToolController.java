package ar.com.BootApp.LautaroV_Boot.controllers;

import ar.com.BootApp.LautaroV_Boot.entities.tool.ToolEntiy;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.EmptyDataBaseException;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.ExistingObjectException;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.NullObjectException;
import ar.com.BootApp.LautaroV_Boot.repositories.ToolRepository;
import ar.com.BootApp.LautaroV_Boot.services.ToolService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public ResponseEntity<Page<ToolEntiy>> getAllTools(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "20") int size)
                                                        throws EmptyDataBaseException {
        Page<ToolEntiy> result = service.findAllTools(page, size);
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
    public ResponseEntity<Page<ToolEntiy>> findByName(@PathVariable String name,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "20") int size){
        Page<ToolEntiy> result = service.findToolByName(name, page, size);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-company/{companyName}")
    public ResponseEntity<Page<ToolEntiy>> findByCompany(@PathVariable String companyName,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "20") int size){
        Page<ToolEntiy> result = service.findByCompany(companyName, page, size);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-price/{min}/{max}")
    public ResponseEntity<Page<ToolEntiy>> findByPrice(@PathVariable double min, @PathVariable double max,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "20") int size){
        Page<ToolEntiy> result = service.findToolByPriceBetween(min, max, page, size);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-name&company/{name}/{companyName}")
    public ResponseEntity<Page<ToolEntiy>> findByNameAndCompany(@PathVariable String name,
                                                                @PathVariable String companyName,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "20") int size){
        Page<ToolEntiy> result = service.findToolByNameAndCompany(name, companyName, page, size);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-name&price/{name}/{min}/{max}")
    public ResponseEntity<Page<ToolEntiy>> findByNameAndPrice(@PathVariable String name,
                                                              @PathVariable double min,
                                                              @PathVariable double max,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "20") int size){
        Page<ToolEntiy> result = service.findToolByNameAndPrice(name, min, max, page, size);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-company&price/{company}/{min}/{max}")
    public ResponseEntity<Page<ToolEntiy>> findByCompanyAndPrince(@PathVariable String company,
                                                                  @PathVariable double min,
                                                                  @PathVariable double max,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "20") int size){
        Page<ToolEntiy> result = service.findToolByCompanyAndPrice(company, min, max, page, size);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-availableTrue")
    public ResponseEntity<Page<ToolEntiy>> findByAvailableTrue(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "20") int size){
        PageRequest request = PageRequest.of(page, size);
        Page<ToolEntiy> result = repository.findByAvailableTrue(request);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-availableFalse")
    public ResponseEntity<Page<ToolEntiy>> findByAvailableFalse(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "20") int size){
        PageRequest request = PageRequest.of(page, size);
        Page<ToolEntiy> result = repository.findByAvailableFalse(request);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/insert")
    public ResponseEntity<ToolEntiy> insertTool(@RequestBody ToolEntiy tool) throws ExistingObjectException, NullObjectException {
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
