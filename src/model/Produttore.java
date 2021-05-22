package model;
import java.io.Serializable;

public class Produttore implements Serializable{
	int id_produttore;
	String partita_iva;
	String nome;

    public Produttore() {
        id_produttore = -1;
        partita_iva = "";
        nome = "";
    }
    
    public Produttore(int id_produttore, String partita_iva, String nome) {
        this.id_produttore = id_produttore;
        this.partita_iva = partita_iva;
        this.nome = nome;
    }

    public int getId_produttore() {
        return id_produttore;
    }

    public void setId_produttore(int id_produttore) {
        this.id_produttore = id_produttore;
    }

    public String getPartita_iva() {
        return partita_iva;
    }

    public void setPartita_iva(String partita_iva) {
        this.partita_iva = partita_iva;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id_produttore;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((partita_iva == null) ? 0 : partita_iva.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produttore other = (Produttore) obj;
        if (id_produttore != other.id_produttore)
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (partita_iva == null) {
            if (other.partita_iva != null)
                return false;
        } else if (!partita_iva.equals(other.partita_iva))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Produttore [id_produttore=" + id_produttore + ", nome=" + nome + ", partita_iva=" + partita_iva + "]";
    }

    
	
	
}
