package dam.list.ej2.pojo;

public class Cancion {
	
	public static final int MIN_DUR = 10;
	public static final int MAX_DUR = 600;
	
	private String titulo;
	private String grupo;
	private int duracion;
	
	public Cancion(String titulo, String grupo, int duracion) {
		this.titulo = titulo;
		this.grupo = grupo;
		this.duracion = duracion;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getGrupo() {
		return grupo;
	}

	public int getDuracion() {
		return duracion;
	}

	@Override
	public String toString() {
		return titulo + " de " + grupo + " " + duracion + " seg.";
	}
	
	

}
