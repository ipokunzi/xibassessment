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
public class AgentController {

	private final AgentRepository repository;
	
	AgentController(AgentRepository repository) {
	    this.repository = repository;
	}
	
	//GET /agents/ - Returns a list of all agents in the database in JSON format
	@GetMapping("/agents")
	List<Agent> getAllAgents() {
	    return repository.findAll();
	}

	//GET /agent/{{id}}/ - Returns a detail view of the specified agent in JSON format. This should include team details
    @GetMapping("/agent/{id}")
    Agent getAgent(@PathVariable Long id) {

      return repository.findById(id)
        .orElseThrow(() -> new AgentNotFoundException(id));
    }
    
    //POST /agent/ - Creates a new agent with the specified details - Expects a JSON body
    @PostMapping("/agent")
    Agent newAgent(@RequestBody Agent newAgent) {
      return repository.save(newAgent);
    }
    
    // GET /agents/ - Implement pagination and query parameters on this request. The agents identity number 
    @GetMapping("/agents?size=10&offset=0")
    List<Agent> getAllPaginatedAgents() {
    	return repository.findAll();
    }    
}
