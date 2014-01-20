package orare.universitate;

public class Sectie {
	
	private int ID;
	private int facultate_ID;
	private String nume;
	private String forma;
	private int ani;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getFacultate_ID() {
		return facultate_ID;
	}
	public void setFacultate_ID(int facultate_ID) {
		this.facultate_ID = facultate_ID;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getForma() {
		return forma;
	}
	public void setForma(String forma) {
		this.forma = forma;
	}
	public int getAni() {
		return ani;
	}
	public void setAni(int ani) {
		this.ani = ani;
	}
	@Override
	public String toString() {
		return nume + " - " + forma;
	}
	
	
}
