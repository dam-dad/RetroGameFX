package dad.javafx.retrogamesfx.reports;


public class HallOfFame {

	public static String Pongscore;
	public static int Snakescore;
	public static int Brickscore;
	public static int Lab;
	public HallOfFame() {}

	public HallOfFame(String n, int s, int b, int lab) {
		super();
		this.Pongscore = n;
		this.Snakescore = s;
		this.Brickscore = b;
		this.Lab=lab;
	}

}
