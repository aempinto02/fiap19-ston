package br.com.fiap.ston.beans;

import java.sql.Date;

public class Sugestao {
	private String sugestao;
	private Date dataSugestao;
	
	public String getSugestao() {
		return sugestao;
	}
	public void setSugestao(String sugestao) {
		this.sugestao = sugestao;
	}
	public Date getDataSugestao() {
		return dataSugestao;
	}
	public void setDataSugestao(Date dataSugestao) {
		this.dataSugestao = dataSugestao;
	}
	
}
