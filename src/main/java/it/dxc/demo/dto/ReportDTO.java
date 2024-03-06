package it.dxc.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class ReportDTO {
	List<ContocorrenteReportDTO> conti=new ArrayList<>();
	Double saldoComplessivo;
	
	
	public ReportDTO() {
		super();
	}
	
	public ReportDTO(Double saldoComplessivo) {
		super();
		this.saldoComplessivo = saldoComplessivo;
	}
	public List<ContocorrenteReportDTO> getConti() {
		return conti;
	}
	public void addConti(ContocorrenteReportDTO conto) {
		this.conti.add(conto);
	}
	public Double getSaldoComplessivo() {
		return saldoComplessivo;
	}
	public void setSaldoComplessivo(Double saldoComplessivo) {
		this.saldoComplessivo = saldoComplessivo;
	}

	@Override
	public String toString() {
		return "ReportDTO [conti=" + conti + ", saldoComplessivo=" + saldoComplessivo + "]";
	}
	
	
	
}
