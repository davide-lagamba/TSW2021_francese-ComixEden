package model;
import java.io.Serializable;

public class Categoria implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	int id;
	String nome;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "id " + id + " nome " + nome;
	}
}
