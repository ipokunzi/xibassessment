package com.xib.assessment;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.jpa.domain.Specification;

@RestController
public class TeamController {
	private final TeamRepository repository;
	
	TeamController(TeamRepository repository) {
	    this.repository = repository;
	}
	
	//GET /teams/ - Returns a list of teams in the database in JSON format
	@GetMapping("/teams")
		List<Team> getAllTeams() {
	    return repository.findAll();
	}
	
	//GET /team/{{id}}/ - Returns a detail view of the specified team in JSON format
    @GetMapping("/team/{id}")
    Team getTeam(@PathVariable Long id) {

      return repository.findById(id)
        .orElseThrow(() -> new TeamNotFoundException(id));
    }
    
    //POST /team/ - Creates a new team with the specified details - Expects a JSON body
    @PostMapping("/team")
    Team newTeam(@RequestBody Team newTeam) {
    	return repository.save(newTeam);
    }
    
    //PUT /team/{{id}}/agent - Assigns an agent to the specified team
//    @PutMapping("/team/{teamid}/agent/{agentid}")
//    Team replaceTeam(@PathVariable Long teamid, @PathVariable Long agentid) {
//    	Team t = new Team();
//        t.setId(teamid);
//        t.setName(t.getName());
//    	Agent a = new Agent();
//        a.setId(agentid);
//        t.getAgent().add(a);
//        t.setManager(t.getManager());
//        return repository.save(t);
//    }
    //PUT /team/{{id}}/agent - Assigns an agent to the specified team
    @PutMapping("/team/{teamid}/agent/{agentid}")
    Team replaceTeam(@PathVariable Long teamid, @PathVariable Long agentid) {
    	Team t = new Team();
    	t.setId(teamid);
    	
    	Agent a = new Agent();
    	a.setId(agentid);
    	
	    return repository.findById(teamid)
	        .map(team -> {
	          team.setName(team.getName());
	          team.getAgent().add(a);
	          team.setManager(team.getManager());
	          return repository.save(team);
	        })
	        .orElseGet(() -> {
	          t.setId(teamid);
	          return repository.save(t);
	    });
    }
    
    //GET /teams/ - Return a list of all empty teams (i.e teams with no agents or managers)
  	@GetMapping("/emptyteams?agent=null&manager=null")
  		List<Team> getEmptyTeams(Specification<Team> specs) {
  	    return repository.findAll(Specification.where(specs));
  	}
    
}
