import java.util.ArrayList;

public class Cena {
	static ArrayList<Palillo> palillos;
	int comensales;
	public Cena(int comensales) {
		this.comensales = comensales;
		palillos = new ArrayList<>();
		for (int i = 0; i < comensales; i++) {
			final Palillo palillo = new  Palillo(i);
			palillos.add(palillo);
		}
	}
	
	public Palillo getPalillo(int idPalillo) {
		return palillos.get(idPalillo);
	}
	public int getPalilloD(int posicion) {
		return posicion;
	}
	public int getPalilloI(int posicion) {
		return ((posicion + 1) + palillos.size()) % palillos.size();
	}
}
