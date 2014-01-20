package orare.universitate;

public class Orar {
	private Facultate facultate;
	private Sectie sectie;
	private int semestru;
	private String table_name, an_semestru;
	private String[] 	ora 		= new String[13],
						luni 		= new String[13],
						marti 		= new String[13],
						miercuri 	= new String[13],
						joi 		= new String[13],
						vineri 		= new String[13],
						sambata 	= new String[13],
						duminica 	= new String[13];

	public Orar(Facultate facultate, Sectie sectie, int semestru){
		this.facultate = facultate;
		this.sectie = sectie;
		this.semestru = semestru;
		this.table_name = "orar_sectie"+sectie.getID()+"_sem"+semestru;
		switch(this.semestru) {
			case 1:
				this.an_semestru = "An 1, Semestrul 1";
				break;
			case 2:
				this.an_semestru = "An 1, Semestrul 2";
				break;
			case 3:
				this.an_semestru = "An 2, Semestrul 1";
				break;
			case 4:
				this.an_semestru = "An 2, Semestrul 2";
				break;
			case 5:
				this.an_semestru = "An 3, Semestrul 1";
				break;
			case 6:
				this.an_semestru = "An 3, Semestrul 2";
				break;
			case 7:
				this.an_semestru = "An 4, Semestrul 1";
				break;
			case 8:
				this.an_semestru = "An 4, Semestrul 2";
				break;
			case 9:
				this.an_semestru = "An 5, Semestrul 1";
				break;
			case 10:
				this.an_semestru = "An 5, Semestrul 2";
				break;
		}
		
	}
	
	public Orar(String table_name, String an_semestru){
		this.table_name = table_name;
		this.an_semestru = an_semestru;
	}
	public Facultate getFacultate() {
		return facultate;
	}
	public void setFacultate(Facultate facultate) {
		this.facultate = facultate;
	}
	public Sectie getSectie() {
		return sectie;
	}
	public void setSectie(Sectie sectie) {
		this.sectie = sectie;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	// ===============================================
	public String[] getOra() {
		return ora;
	}
	public void setOra(String[] ora) {
		this.ora = ora;
	}
	// -----------------------------------------------
	public String[] getLuni() {
		return luni;
	}
	public void setLuni(String[] luni) {
		this.luni = luni;
	}
	// -----------------------------------------------
	public String[] getMarti() {
		return marti;
	}
	public void setMarti(String[] marti) {
		this.marti = marti;
	}
	// -----------------------------------------------
	public String[] getMiercuri() {
		return miercuri;
	}
	public void setMiercuri(String[] miercuri) {
		this.miercuri = miercuri;
	}
	// -----------------------------------------------
	public String[] getJoi() {
		return joi;
	}
	public void setJoi(String[] joi) {
		this.joi = joi;
	}
	// -----------------------------------------------
	public String[] getVineri() {
		return vineri;
	}

	public void setVineri(String[] vineri) {
		this.vineri = vineri;
	}
	// -----------------------------------------------
	public String[] getSambata() {
		return sambata;
	}
	public void setSambata(String[] sambata) {
		this.sambata = sambata;
	}
	// -----------------------------------------------
	public String[] getDuminica() {
		return duminica;
	}
	public void setDuminica(String[] duminica) {
		this.duminica = duminica;
	}
	// ===============================================
	public int getSemestru() {
		return semestru;
	}

	public void setSemestru(int semestru) {
		this.semestru = semestru;
	}

	public String getAn_semestru() {
		return an_semestru;
	}

	public void setAn_semestru(String an_semestru) {
		this.an_semestru = an_semestru;
	}

	@Override
	public String toString() {
		return an_semestru;
	}
	
}