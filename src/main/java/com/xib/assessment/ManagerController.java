package com.xib.assessment;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {

	private final ManagerRepository repository;
	
	ManagerController(ManagerRepository repository) {
	    this.repository = repository;
	}
	
	//GET /managers/ - Returns a list of all managers in the database in JSON format
	@GetMapping("/managers")
	List<Manager> getAllManagers() {
	    return repository.findAll();
	}

    //POST /manager/ - Creates a new manager with the specified details - Expects a JSON body
    @PostMapping("/manager")
    Manager newManager(@RequestBody Manager newManager) {
      return repository.save(newManager);
    }
}