package dad.javafx.retrogamesfx.games.labyrinth;


public class Mapa {
	String[] Laberinto = {
			// 123456789012345678
			"PPPPPPPPPPPPPPPPPP", // 1
			"PCCPCCCCPCCCPCCCCP", // 2
			"PPCPCPPPPPPCPCPPPP", // 3
			"PPCCCPCCCPPCCCPPCP", // 4
			"PPCPCPCPCPPCPCPCCP", // 5
			"PICPCPPPCCCCPCPCPP", // 6
			"PPCPCCCPCPPCPCPCFP", // 7
			"PPCPPCPPCPPPPCPCPP", // 8
			"PCCPCCCCCCCPCCCCCP", // 9
			"PCPPPPPPPPCPPPPPPP", // 10
			"PCPPCCCCCPCPCCCCCP", // 11
			"PCCCCPPPCPCCCPPPCP", // 12
			"PPPPPPCCCPPPPPCCCP", // 13
			"PPPPPPPPPPPPPPPPPP"// 14
	};
	String[] Semilla1 = {
			// 123456789012345678
			"PPPPPPPPPPPPPPPPPP", // 1
			"P................P", // 2
			"P................P", // 3
			"P.CCC......CCC...P", // 4
			"P.C.C......C.C...P", // 5
			"PIC.C...CCCC.C...P", // 6
			"P...CC..C....C.CFP", // 7
			"P....C..C....C.C.P", // 8
			"P....C..C....CCC.P", // 9
			"P....CCCC........P", // 10
			"P................P", // 11
			"P................P", // 12
			"P................P", // 13
			"PPPPPPPPPPPPPPPPPP"// 14
	};
	String[] Semilla2 = {
			// 123456789012345678
			"PPPPPPPPPPPPPPPPPP", // 1
			"P................P", // 2
			"P.............CCFP", // 3
			"P.............C..P", // 4
			"P.............C..P", // 5
			"P.CCCCCCC.CCC.C..P", // 6
			"P.C.....C.C.C.C..P", // 7
			"P.CCCCC.C.C.CCC..P", // 8
			"P.....C.C.C......P", // 9
			"P.CCC.C.C.C......P", // 10
			"P.C.C.C.C.C......P", // 11
			"PIC.CCC.CCC......P", // 12
			"P................P", // 13
			"PPPPPPPPPPPPPPPPPP"// 14
	};
	
	String[] Semilla3 = {
			// 123456789012345678
			"PPPPPPPPPPPPPPPPPP", // 1
			"PICCC............P", // 2
			"P...C............P", // 3
			"P...C.CCC..CCCC..P", // 4
			"P...CCC.C..C..CCFP", // 5
			"P.......C..C.....P", // 6
			"P.......CCCC.....P", // 7
			"P................P", // 8
			"P................P", // 9
			"P................P", // 10
			"P................P", // 11
			"P................P", // 12
			"P................P", // 13
			"PPPPPPPPPPPPPPPPPP"// 14
	};
	String[] Semilla4 = {
            // 123456789012345678
            "PPPPPPPPPPPPPPPPPP", // 1
            "P................P", // 2
            "P.CCC.....CCCC...P", // 3
            "PIC.C.....C..C...P", // 4
            "P...C..CCCC..C...P", // 5
            "P.CCC..C.....C...P", // 6
            "P.C....C.....C...P", // 7
            "P.CCCCCC.....C...P", // 8
            "P............C...P", // 9
            "P....CCCCCCCCC...P", // 10
            "P....C.........CFP", // 11
            "P....CCCCCCCCCCC.P", // 12
            "P................P", // 13
            "PPPPPPPPPPPPPPPPPP"// 14
};

	public String[] getSemilla(int aux) {
		String[] aux2 = null;
		switch (aux) {

		case 2:
			aux2 = Semilla4;
			break;
		case 3:aux2 = Semilla3;
			break;
		case 4:aux2 = Semilla2;
			break;
		case 5:aux2 = Semilla1;
			break;
		}
		return aux2;
	}

	

	public String[] getLaberinto() {
		return Laberinto;
	}

	public void setLaberinto(String[] laberinto) {
		Laberinto = laberinto;
	}

	// ---------------------------------------------------------------------------------
//	private static final int width = 800;// 18x44.44 X
//	private static final int height = 600;// 12x42.85 Y

	int score = 0;

}
