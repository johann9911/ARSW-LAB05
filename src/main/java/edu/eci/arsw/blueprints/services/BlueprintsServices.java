/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.filtro.filtro;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServices {
   
    @Autowired
    BlueprintsPersistence bpp=null;
    
    @Autowired
    filtro fil=null;
    
    public Blueprint addBlueprintWithFiltreR(Blueprint bp) {
    	return fil.filterBlueprint(bp);
    }
    
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException{
        bpp.saveBlueprint(bp);
    }
    
    public Set<Blueprint> getAllBlueprints() throws BlueprintPersistenceException{
        try {
			return bpp.getAllBlueprints();
		} catch (BlueprintNotFoundException e) {
			new BlueprintPersistenceException("No se encontro");
		}
		return null;
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintPersistenceException{
        try {
			return bpp.getBlueprint(author, name);
		} catch (BlueprintNotFoundException e) {
			
			throw new BlueprintPersistenceException("Error: no se encontro blueprint");
		}
    }
    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        return bpp.getBlueprintsByAuthor(author);
    }

	public void updateBlueprint(String author, String bp, Blueprint blueprint) throws BlueprintPersistenceException {
		try {
			bpp.updateBlueprint(author, bp, blueprint);
		} catch (BlueprintNotFoundException e) {
			throw new BlueprintPersistenceException("Error: no se encontro blueprint");
		}
	}
    
}
