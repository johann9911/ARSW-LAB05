/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final ConcurrentHashMap<Tuple<String,String>,Blueprint> blueprints=new ConcurrentHashMap<>();
    
    public InMemoryBlueprintPersistence() {
        //load stub data
    	
    	
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp=new Blueprint("lina", "plano1",pts);
        
        Point[] pts2=new Point[]{new Point(140, 140),new Point(110, 115)};
        Blueprint bp2=new Blueprint("johann", "plano2",pts2);
        
        Point[] pts3=new Point[]{new Point(140, 50),new Point(115, 20)};
        Blueprint bp3=new Blueprint("johann", "plano3",pts3);
        
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);
        
        
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
        	
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
    	Blueprint res = blueprints.get(new Tuple<>(author, bprintname));
    	if(res==null) {
    		throw new BlueprintNotFoundException("No se encontro recurso");
    	}
        return res;

    }

	@Override
	public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
		Set<Tuple<String,String>> t = blueprints.keySet();
		Set<Blueprint> allBlueprints= new HashSet<Blueprint>();
		
		for(Tuple<String,String> i:t) {
			if(i.getElem1().equals(author)) {
				allBlueprints.add(blueprints.get(i));
			}
		}
		
		return allBlueprints;
	}
	
	@Override
	public Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException{
		Set<Blueprint> allBlueprints= new HashSet<Blueprint>();
		
		for(Tuple<String,String> i:blueprints.keySet()) {
			allBlueprints.add(blueprints.get(i));
		}
		return allBlueprints;
	}
	
	@Override
	public void updateBlueprint(String author, String bp, Blueprint blueprint) throws BlueprintNotFoundException {
		boolean encontro=false;
		List<Point> points = blueprint.getPoints();
		for (Tuple<String,String> bps : blueprints.keySet()) {
			if (bps.getElem1().equals(author) && bps.getElem2().equals(bp) ) {
				blueprints.get(bps).setPoints(points);
				encontro=true;
			}
		}
		if(!encontro) {
			throw new BlueprintNotFoundException("No se encontro recurso");
		}
	}
    
    
    
}
