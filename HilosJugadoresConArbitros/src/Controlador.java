

class Jugador extends Thread{
	private Arbitro arbitro;
	private int dorsal;
	
	public Jugador(int dorsal, Arbitro arbitro) {
		this.dorsal = dorsal;
		this.arbitro = arbitro;
	}
	
	@Override
	public void run() {
		while (!arbitro.seAcabo()) {
			arbitro.jugar(this.dorsal, (int) ((arbitro.MAXIMO * Math.random() +1)));
		}
		System.out.println("El jugador " + dorsal + " ha dejado de jugar");
	}
	
	
}

class Arbitro{
	private int numJugadores;
	private int turno;
	private int objetivo;
	private boolean acertado;
	public static final int MAXIMO = 1000;
	
	public Arbitro(int numJug) {
		this.numJugadores = numJug;
		this.objetivo = (int)((Math.random() * MAXIMO) + 1);
		this.turno = (int) ((numJugadores * Math.random())+1);
		this.acertado = false;
		System.out.println("Número a acertar: " + objetivo);
	}
	
	public int esTurnoDe() {
		return turno;
	}
	
	public boolean seAcabo() {
		return acertado;
	}
	
	public synchronized void jugar(int jugador, int jugada) {
		while (jugador != turno && !acertado) {
			try {
				System.out.println("[PRE] Jugador" + jugador + " y es turno de " + turno);
				wait();
				System.out.println("[POST] Jugador" + jugador + " y es turno de " + turno);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(!acertado) {
			System.out.println("El jugador " + jugador + " prueba con " + jugada);
			if(jugada == objetivo) {
				acertado = true;
				System.out.println("¡El jugador " + jugador + " ha ganado!");
			}
		
			else {
				turno = (turno + 1) % numJugadores;
			}
		}
		notifyAll();
	}
}

public class Controlador {

	public static void main(String[] args) {
		Arbitro arbitro = new Arbitro(5);
		Jugador j1 = new Jugador(0, arbitro);
		Jugador j2 = new Jugador(1, arbitro);
		Jugador j3 = new Jugador(2, arbitro);
		Jugador j4 = new Jugador(3, arbitro);
		Jugador j5 = new Jugador(4, arbitro);
		
		j1.start();
		j2.start();
		j3.start();
		j4.start();
		j5.start();

		try {
			j1.join();
			j2.join();
			j3.join();
			j4.join();
			j5.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
