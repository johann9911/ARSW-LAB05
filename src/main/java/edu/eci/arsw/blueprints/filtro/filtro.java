package edu.eci.arsw.blueprints.filtro;

import java.util.Set;

import edu.eci.arsw.blueprints.model.Blueprint;

public interface filtro {
	public Set<Blueprint> filterBluePrints(Set<Blueprint> set);
	public Blueprint filterBlueprint(Blueprint bp);
}
