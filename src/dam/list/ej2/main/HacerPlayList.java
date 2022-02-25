package dam.list.ej2.main;

import java.util.ArrayList;
import java.util.Scanner;

import dam.list.ej2.pojo.Cancion;

public class HacerPlayList {
	
	static final String[] OPCIONES = {
			"Salir de la playlist",
			"Añadir una canción a la playlist",
			"Eliminar una canción por titulo y grupo/solista",
			"Mostrar las canciones de la lista", 
			"Reproducir una canción por posición",
			"Reproducir toda la lista",
			"Indicar tiempo total de la playlist"
	};
	
	static ArrayList<Cancion> playlist;
	static Scanner sc;

	public static void main(String[] args) {
		
		playlist = new ArrayList<Cancion>();
		sc = new Scanner(System.in);
		
		int opcion = 1;
		
		System.out.println("** Bienvenido a la aplicación TU PLAYLIST **");
		
		while (opcion != 0) {
			
			opcion = solicitarOpcionMenu();
			
			switch (opcion) {
			case 0:
				System.out.println("\n** CERRANDO LA PLAYLIST **");
				break;
			case 1:
				addCancion();
				break;
			case 2:
				eliminarCancion();
				break;
			case 3:
				mostrarPlaylist();
				break;
			case 4:
				reproducirCancionPos();
				break;
			case 5:
				reproducirPlaylist();
				break;
			case 6:
				calcularDuracionTotal();
				break;
			}
		}
		
		sc.close();

	}
	
	private static void calcularDuracionTotal() {
		int durTotal = 0;
		
		for (Cancion cancion : playlist) {
			durTotal += cancion.getDuracion();
		}
		
		if (durTotal == 0) {
			System.out.println("\nLa lista está vacía");
		} else {
			System.out.println("** DURACIÓN DE LA PLAYLIST: " + durTotal + " seg.");
		}
		
	}

	private static void reproducirPlaylist() {
		if (playlist.isEmpty()) {
			System.out.println("\nLa playlist está vacía");
		} else {
			System.out.println();
			for (Cancion cancion : playlist) {
				System.out.println("** SE ESTÁ REPRODUCIENDO: " + cancion);
			}
		}
		
	}

	private static void reproducirCancionPos() {
		int pos = solicitarPosicion() - 1;
		
		System.out.println("** SE ESTÁ REPRODUCIENDO: " + playlist.get(pos));
		
	}

	private static int solicitarPosicion() {
		int pos = 0;
		boolean valNoVal = true;
		
		while (valNoVal) {
			try {
				System.out.println("\nIntroduce la posición de la canción que deseas reproducir");
				pos = Integer.parseInt(sc.nextLine());
				
				if (pos >= 1 && pos <= playlist.size()) {
					valNoVal = false;
				} else {
					throw new NumberFormatException();
				}
				
			} catch (NumberFormatException e) {
				System.out.println("El valor debe ser un entero entre 1 y " 
						+ playlist.size());
			}
			
		}	
		
		return pos;
	}

	private static void eliminarCancion() {
		
		String titulo = solicitarCanenaNoVacia("\nIntroduce el título de la canción que deseas eliminar");
		String grupo = solicitarCanenaNoVacia("\nIntroduce el grupo o artista");
		
		int pos = buscarCancion(titulo, grupo);
		
		if (pos == -1) {
			System.out.println("\nLA CANCIÓN INDICADA NO SE ENCUENTRA EN LA LISTA");
		} else {
			playlist.remove(pos);
			
			System.out.println("CANCIÓN ELIMINADA, CANCIONES EN LA LISTA: " + playlist.size());
		}
		
	}

	private static int buscarCancion(String titulo, String grupo) {
		int pos = -1;
		for (int i = 0; i < playlist.size() && pos == -1; i++) {
			if (playlist.get(i).getTitulo().equals(titulo) && playlist.get(i).getGrupo().equals(grupo)) {
				pos = i;
			}
		}
		
		return pos;
	}

	private static void addCancion() {
		String titulo = solicitarCanenaNoVacia("\nIntroduce el título de la canción");
		String grupo = solicitarCanenaNoVacia("\nIntroduce el grupo o artista");
		int duracion = solicitarDuracion();
		
		playlist.add(new Cancion(titulo, grupo, duracion));
		
		System.out.println("CANCIÓN AÑADIDA, CANCIONES EN LA LISTA: " + playlist.size());
		
	}

	private static int solicitarDuracion() {
		int dur = 0;
		boolean valNoVal = true;
		
		while (valNoVal) {
			try {
				System.out.println("\nIntroduce la duración");
				dur = Integer.parseInt(sc.nextLine());
				
				if (dur >= Cancion.MIN_DUR && dur <= Cancion.MAX_DUR) {
					valNoVal = false;
				} else {
					throw new NumberFormatException();
				}
				
			} catch (NumberFormatException e) {
				System.out.println("El valor debe ser un entero entre " + Cancion.MIN_DUR + " y " 
						+ Cancion.MAX_DUR);
			}
			
		}	
		
		return dur;
	}

	private static String solicitarCanenaNoVacia(String mensaje) {
		String cadena = "";
		
		while (cadena.isEmpty()) {
			System.out.println(mensaje);
			cadena = sc.nextLine().trim().toUpperCase();
			
			if (cadena.isEmpty()) {
				System.out.println("Debe introducir una texto distinto de vacío");
			}
		}
		
		return cadena;
	}

	private static void mostrarPlaylist() {
		if (playlist.isEmpty()) {
			System.out.println("\nLa playlist está vacía");
		} else {
			System.out.println("\n** Canciones de la lista **");
			for (int i = 0; i < playlist.size(); i++) {
				System.out.println((i+1) + " " + playlist.get(i));
			}
		}
		
	}

	private static int solicitarOpcionMenu() {
		int opc = 0;
		boolean valNoVal = true;
		
		while (valNoVal) {
			try {
				System.out.println("\nIntroduce una de las siguientes opciones");
				for (int i = 0; i < OPCIONES.length; i++) {
					System.out.println(i + " - " + OPCIONES[i]);
				}
				
				opc = Integer.parseInt(sc.nextLine());
				
				if (opc >= 0 && opc < OPCIONES.length) {
					valNoVal = false;
				} else {
					throw new NumberFormatException();
				}
				
			} catch (NumberFormatException e) {
				System.out.println("El valor debe ser un entero entre 0 y " + (OPCIONES.length - 1));
			}
			
		}	
		
		return opc;
	}

}
