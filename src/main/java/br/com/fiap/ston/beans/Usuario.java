package br.com.fiap.ston.beans;

public class Usuario {
	
	private String nome;
	private String email;
	private String telefone;
	private String celular;
	private String senha;
	private char admin;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public char getAdmin() {
		return admin;
	}
	public void setAdmin(char admin) {
		this.admin = admin;
	}
	
	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", celular=" + celular
				+ ", admin=" + admin + "]";
	}
	
}
