package dad.javafx.retrogamesfx.reports;

import java.util.ArrayList;
import java.util.List;

public class AgendaDataProvider {

	public static List<HallOfFame> getPersonas() {
		List<HallOfFame> personas = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			HallOfFame p = new HallOfFame();
			p.Brickscore=1;
			p.Pongscore="Win";
			p.Snakescore=1;
			p.Lab=1;
			personas.add(p);
		}
		return personas;
	}

}
