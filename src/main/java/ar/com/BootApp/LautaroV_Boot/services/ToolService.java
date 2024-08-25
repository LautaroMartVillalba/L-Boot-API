package ar.com.BootApp.LautaroV_Boot.services;

import ar.com.BootApp.LautaroV_Boot.entities.tool.ToolEntiy;
import ar.com.BootApp.LautaroV_Boot.exceptions.tool.types.DuplicatedToolException;
import ar.com.BootApp.LautaroV_Boot.exceptions.tool.types.NullToolException;
import ar.com.BootApp.LautaroV_Boot.exceptions.tool.types.ToolEmptyDataBaseException;
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
     * @return List af all tools in DataBase List if it has some registers.
     */
    public List<ToolEntiy> findAllTools() throws ToolEmptyDataBaseException {
        List<ToolEntiy> result = repository.findAll();
        if (result.isEmpty()) {
            throw new ToolEmptyDataBaseException();
        }
        return result;
    }

    /**
     * Search a tool in DataBase by his ID number (long type).
     *
     * @param id Tool's identification.
     * @return Optional of tool if it can find a register. Optional.Empty if it can't.
     */
    public Optional<ToolEntiy> findByToolID(Long id) {
        return repository.findById(id);
    }

    /**
     * Save a tool in DataBase, using a first validation by all tool's parameters.
     *
     * @param tool Tool Object to persist in DataBase.
     */
    public boolean saveTool(ToolEntiy tool) throws NullToolException, DuplicatedToolException {
        if (!validateTool(tool)) {
            throw new NullToolException();
        }
        Optional<ToolEntiy> toolRepo = repository.findByCompanyAndNameAndPriceBetween(tool.getCompany(), tool.getName(), tool.getPrice() - 1, tool.getPrice() + 1);
        if (toolRepo.isPresent()) {
            throw new DuplicatedToolException();
        }
        repository.save(tool);
        return true;
    }

    /**
     * Search one tool in DataBase witch matches with one existing. If it can find, delete the found car.
     *
     * @param id Tool's identification number (long type).
     * @return False if it can't find a tool's id witch matches in DataBase. True if it can find and delete.
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

    public List<ToolEntiy> findToolByName(String name) {
        if (!Objects.equals(name, "")) {
            return repository.findByNameContaining(name);
        }
        return new ArrayList<>();
    }

    public List<ToolEntiy> findByCompany(String company) {
        if (!Objects.equals(company, "")) {
            return repository.findByCompanyContaining(company);
        }
        return new ArrayList<>();
    }

    public List<ToolEntiy> findToolByPriceBetween(double min, double max) {
        if (min > 0 && max > min) {
            return repository.findByPriceBetween(min, max);
        }
        return new ArrayList<>();
    }

    public List<ToolEntiy> findToolByNameAndCompany(String name, String company) {
        if (!Objects.equals(name, "") && !Objects.equals(company, "")) {
            return repository.findByNameContainingAndCompanyContaining(name, company);
        }
        return new ArrayList<>();
    }

    public List<ToolEntiy> findToolByNameAndPrice(String name, double min, double max) {
        if (!Objects.equals(name, "") && min > 0 && max > min) {
            return repository.findByNameContainingAndPriceBetween(name, min, max);
        }
        return new ArrayList<>();
    }

    public List<ToolEntiy> findToolByCompanyAndPrice(String company, double min, double max) {
        if (!Objects.equals(company, "") || min <= 0 || max < min) {
            return repository.findByCompanyContainingAndPriceBetween(company, min, max);
        }
        return new ArrayList<>();
    }
    
}
