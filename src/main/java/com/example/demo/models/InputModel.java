package com.example.demo.models;

public class InputModel {
	
	private Double amount;
	private Integer terms;
	private Double rate;
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getTerms() {
		return terms;
	}
	public void setTerms(Integer terms) {
		this.terms = terms;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	@Override
	public String toString() {
		return "InputModel [amount=" + amount + ", terms=" + terms + ", rate=" + rate + "]";
	}
    
}
