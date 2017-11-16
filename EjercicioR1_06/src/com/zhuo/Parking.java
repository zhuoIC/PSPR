package com.zhuo;

public class Parking {
	int nPlazas;
	int nCoches;
	Plaza[] plazas;
	boolean libre;
	int turno;
	
	public Parking(int nPlazas, int nCoches) {
		super();
		this.nPlazas = nPlazas;
		this.nCoches = nCoches;
		this.turno = 1;
		generarPlazas(nPlazas);
	}

	private void generarPlazas(int nPlazas) {
		plazas = new Plaza[nPlazas];
		for (int i = 0; i < plazas.length; i++) {
			plazas[i] = new Plaza(i);
		}
	}
	
	public void plazasLibres(ControlParking controlParking) {
		Plaza plazaSolicitada = null;
		for (Plaza plaza : plazas) {
			if(plaza.isLibre()) {
				plazaSolicitada = plaza;
				break;
			}
		}
	}
	
	
}
