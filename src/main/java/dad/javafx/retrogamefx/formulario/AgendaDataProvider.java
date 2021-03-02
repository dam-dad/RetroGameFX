package dad.javafx.retrogamefx.formulario;

import java.util.ArrayList;
import java.util.List;

public class AgendaDataProvider {

	public static List<Myscores> getPersonas() {
		List<Myscores> personas = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			Myscores p = new Myscores();
			p.Brickscore=1;
			p.Pongscore="Win";
			p.Snakescore=1;
			p.Lab=1;
			personas.add(p);
		}
		return personas;
	}

}
