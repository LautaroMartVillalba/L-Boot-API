package ar.com.BootApp.LautaroV_Boot.services;

import ar.com.BootApp.LautaroV_Boot.entities.tool.ToolEntiy;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.EmptyDataBaseException;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.ExistingObjectException;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.NullObjectException;
import ar.com.BootApp.LautaroV_Boot.repositories.ToolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ToolService {

    ToolRepository repository;

    /**
     * Validate the Tool's info to know if all data was received.
     *
     * @param tool Tool object.
     * @return False if any parameter of 'tool' was null. True if all is correct.
     */
    private boolean validateTool(ToolEntiy tool) {
        if (Objects.equals(tool.getCompany(), "") || Objects.equals(tool.getName(), "") || tool.getPrice() <= 0) {
            return false;
        }
        return true;
    }

    /*--------------Default Methods--------------*/

    /**
     * Search and return a List of all tools in DataBase.
     *
     * @return List of ToolEntity.
     */
    public List<ToolEntiy> findAllTools() throws EmptyDataBaseException {
        List<ToolEntiy> result = repository.findAll();
        if (result.isEmpty()) {
            throw new EmptyDataBaseException();
        }
        return result;
    }

    /**
     * Search a tool in DataBase by his ID number (long type).
     *
     * @param id Tool's identification.
     * @return Optional of ToolEntity. Optional.Empty if it can't.
     */
    public Optional<ToolEntiy> findByToolID(Long id) {
        return repository.findById(id);
    }

    /**
     * Save a tool in DataBase, using a first validation by all tool's parameters.
     *
     * @param tool Tool Object to persist in DataBase.
     */
    public boolean saveTool(ToolEntiy tool) throws NullObjectException, ExistingObjectException {
        if (!validateTool(tool)) {
            throw new NullObjectException();
        }
        Optional<ToolEntiy> toolRepo = repository.findByCompanyAndNameAndPriceBetween(tool.getCompany(), tool.getName(), tool.getPrice() - 1, tool.getPrice() + 1);
        if (toolRepo.isPresent()) {
            throw new ExistingObjectException();
        }
        repository.save(tool);
        return true;
    }

    /**
     * Searches the database for a tool that matches an existing one. If found, deletes the corresponding tool.
     *
     * @param id The tool's identification number (of type long).
     * @return False if no matching tool is found in the database. True if a matching tool is found and deleted.
     */

    public boolean deleteToolByID(Long id) {
        Optional<ToolEntiy> toolFind = repository.findById(id);
        if (toolFind.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    /*-------------------Custom Methods-------------------*/

    /**
     *  Searches the database for tools by their name.
     * @param name Tool's name.
     * @return List of ToolEntity or empty list.
     */
    public List<ToolEntiy> findToolByName(String name) {
        if (!Objects.equals(name, "")) {
            return repository.findByNameContaining(name);
        }
        return new ArrayList<>();
    }

    /**
     *  Searches the database for tools by their manufacturer's company.
     * @param company Tool's manufacturer Company.
     * @return List of ToolEntity or empty list.
     */
    public List<ToolEntiy> findByCompany(String company) {
        if (!Objects.equals(company, "")) {
            return repository.findByCompanyContaining(company);
        }
        return new ArrayList<>();
    }

    /**
     * Searches the database for tools with a price between the specified minimum and maximum values.
     * @param min Min value.
     * @param max Max value.
     * @return List of ToolEntity or empty list.
     */
    public List<ToolEntiy> findToolByPriceBetween(double min, double max) {
        if (min > 0 && max > min) {
            return repository.findByPriceBetween(min, max);
        }
        return new ArrayList<>();
    }

    /**
     *  Searches the database for tools that match the specified name and company.
     * @param name Tool's name.
     * @param company Tool's manufacturer company.
     * @return List of ToolEntity or empty list.
     */
    public List<ToolEntiy> findToolByNameAndCompany(String name, String company) {
        if (!Objects.equals(name, "") && !Objects.equals(company, "")) {
            return repository.findByNameContainingAndCompanyContaining(name, company);
        }
        return new ArrayList<>();
    }

    /**
     *  Searches the database for tools with a price between the specified minimum and maximum values.
     * @param name Tool's name.
     * @param min Minimum value.
     * @param max Maximum value.
     * @return List of ToolEntity or empty list.
     */
    public List<ToolEntiy> findToolByNameAndPrice(String name, double min, double max) {
        if (!Objects.equals(name, "") && min > 0 && max > min) {
            return repository.findByNameContainingAndPriceBetween(name, min, max);
        }
        return new ArrayList<>();
    }

    /**
     * Searches the database for tools by their manufacturer's name and price between the specified minimum and maximum values.
     * @param company Tool's manufacturer company name.
     * @param min Minimum value.
     * @param max Maximum value.
     * @return List of ToolEntity or empty list.
     */
    public List<ToolEntiy> findToolByCompanyAndPrice(String company, double min, double max) {
        if (!Objects.equals(company, "") || min <= 0 || max < min) {
            return repository.findByCompanyContainingAndPriceBetween(company, min, max);
        }
        return new ArrayList<>();
    }
    
}