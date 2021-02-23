/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

/**
 *
 * @author hcadavid
 */
@Service
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {
	
	@Autowired
	BlueprintsServices services;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> manejadorGetRecursoAllBlueprints() {
		// obtener datos que se enviarán a través del API
		try {
			return new ResponseEntity<>(services.getAllBlueprints(), HttpStatus.ACCEPTED);
		} catch (BlueprintPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@GetMapping("/{author}/{bpname}")
	public ResponseEntity<?> manejadorGetRecursoToAuthorBlueprints(@PathVariable String author, @PathVariable String bpname) throws ResourceNotFoundException {
		// obtener datos que se enviarán a través del API
	
			try {
				return new ResponseEntity<>(services.getBlueprint(author, bpname), HttpStatus.ACCEPTED);
			} catch (BlueprintPersistenceException e) {
				throw new ResourceNotFoundException();
			}
			
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> manejadorPostRecursoXX(@RequestBody Blueprint bp){
	    try {
	        //registrar dato
	    	
	    	services.addNewBlueprint(bp);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (BlueprintPersistenceException ex) {
	        Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Error bla bla bla",HttpStatus.FORBIDDEN);            
	    }        

	}
	
	@RequestMapping(value="/{author}/{bpname}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBlueprint(@RequestBody Blueprint blueprint, @PathVariable String author, @PathVariable String bpname){
	    try {
	        //actualizar dato
	    	services.updateBlueprint(author, bpname, blueprint);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (BlueprintPersistenceException ex) {
	        Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("No es posible encontrar el plano para realizar el cambio.",HttpStatus.NO_CONTENT);            
	    }
	}
	

}
