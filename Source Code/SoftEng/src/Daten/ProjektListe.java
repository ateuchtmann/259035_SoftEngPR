package Daten;

import java.util.ArrayList;
import java.util.List;

public class ProjektListe {
	
	private List<Projekt> projekte = new ArrayList<>();
	
	public void addProjekt(Projekt p) {
		projekte.add(p);
	}
	
	public List getProjekte() {
		return projekte;
	}

}
