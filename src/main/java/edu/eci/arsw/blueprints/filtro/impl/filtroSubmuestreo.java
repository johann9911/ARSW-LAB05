package edu.eci.arsw.blueprints.filtro.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.filtro.filtro;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

public class filtroSubmuestreo implements filtro {

	@Override
	public Set<Blueprint> filterBluePrints(Set<Blueprint> set) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blueprint filterBlueprint(Blueprint bp) {
		
		List<Point> list= bp.getPoints();
		List<Point> newList = new ArrayList<>();
		for(int i=0;i<list.size();i+=2) {
			Point p1 = list.get(i);
			newList.add(p1);
			
			
			
		}
		bp.setPoints(newList);	
		return bp;
	}
}