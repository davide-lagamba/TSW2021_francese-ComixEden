package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class Utente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id_utente;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String telefono;
	private Date data_registrazione;
	private boolean admin;
	private boolean registrazione;
	
	public Utente() {
		this.id_utente = -1;
		this.nome = "";
		this.cognome = "";
		this.email = "";
		this.password = "";
		this.telefono = "";
		this.data_registrazione = null;
		this.admin = false;
	}
	
	public Utente(String nome, String cognome, String email, String password, String telefono) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.telefono=telefono;
		this.admin = false;
		long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
		this.data_registrazione=date;
		System.out.println(password);
	}
	
	public Utente(int id_utente, String nome, String cognome, String email, String password) {
		this.id_utente = id_utente;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.data_registrazione =  null;
		this.admin = false;
		long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
		this.data_registrazione=date;
	}

	public Utente(int id_utente, String nome, String cognome, String email, String password, String telefono,
			Date data_registrazione, boolean admin) {
		super();
		this.id_utente = id_utente;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.telefono = telefono;
		this.data_registrazione = data_registrazione;
		this.admin = admin;
	}
	
	public boolean getRegistrazione() {
		return registrazione;
	}
	

	public int getId_utente() {
		return id_utente;
	}

	public void setId_utente(int id_utente) {
		this.id_utente = id_utente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public void setRegistrazione(boolean registrazione) {
		this.registrazione=registrazione;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getData_registrazione() {
		return data_registrazione;
	}

	public void setData_registrazione(Date data_registrazione) {
		this.data_registrazione = data_registrazione;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (admin ? 1231 : 1237);
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((data_registrazione == null) ? 0 : data_registrazione.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id_utente;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
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
		Utente other = (Utente) obj;
		if (admin != other.admin)
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (data_registrazione == null) {
			if (other.data_registrazione != null)
				return false;
		} else if (!data_registrazione.equals(other.data_registrazione))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id_utente != other.id_utente)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Utente [id_utente=" + id_utente + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email
				+ ", password=" + password + ", telefono=" + telefono + ", data_registrazione=" + data_registrazione
				+ ", admin=" + admin + "]";
	}
	
	
	
	
	
}
