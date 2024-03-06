package it.dxc.demo.dto;

public class ContocorrenteReportDTO {
	int numeroConto;
	Double saldoConto;
	String nomeP;
	String cognomeP;
	Integer numeroMov;
	
	
	public ContocorrenteReportDTO() {
		super();
	}
	
	public ContocorrenteReportDTO(int numeroConto, Double saldoConto, String nomeP, String cognomeP, Integer numeroMov) {
		super();
		this.numeroConto = numeroConto;
		this.saldoConto = saldoConto;
		this.nomeP = nomeP;
		this.cognomeP = cognomeP;
		this.numeroMov = numeroMov;
	}
	
	public int getNumeroConto() {
		return numeroConto;
	}
	public void setNumeroConto(int numeroConto) {
		this.numeroConto = numeroConto;
	}
	public Double getSaldoConto() {
		return saldoConto;
	}
	public void setSaldoConto(Double saldoConto) {
		this.saldoConto = saldoConto;
	}
	public String getNomeP() {
		return nomeP;
	}
	public void setNomeP(String nomeP) {
		this.nomeP = nomeP;
	}
	public String getCognomeP() {
		return cognomeP;
	}
	public void setCognomeP(String cognomeP) {
		this.cognomeP = cognomeP;
	}
	public Integer getNumeroMov() {
		return numeroMov;
	}
	public void setNumeroMov(Integer numeroMov) {
		this.numeroMov = numeroMov;
	}

	@Override
	public String toString() {
		return "ContocorrenteReportDTO [numeroConto=" + numeroConto + ", saldoConto=" + saldoConto + ", nomeP=" + nomeP
				+ ", cognomeP=" + cognomeP + ", numeroMov=" + numeroMov + "]";
	}
	
	
	
}
