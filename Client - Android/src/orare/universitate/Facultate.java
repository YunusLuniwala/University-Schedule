package orare.universitate;

public class Facultate {
	
	private int ID;
	private String nume;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	
	@Override
	public String toString() {
		return nume;
	}
	
	
}
