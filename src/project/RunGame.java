package project;

public class RunGame {
	public static void main(String args[]){
		TableTop t = new TableTop(new TexasHoldem2(3));
		//TableTop t = new TableTop(new War());
		
		//TableTop t = new TableTop("Poke");
	}
}
