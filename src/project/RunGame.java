package project;

public class RunGame {
	public static void main(String args[]){
		TableTop t = new TableTop(new TexasHoldem(6));
		//TableTop t = new TableTop(new War());
		
		//TableTop t = new TableTop("Poke");
	}
}
