package com.wildcodeschool.fco.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Encounter {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date matchDate;
	private String localTeam;
	private String division;
	private String visiteurName;
	private String visiteurLogo;
	
	
	public Encounter() {}
	
	
	public Encounter(Date matchDate, String localTeam, String division, String visiteurName, String visiteurLogo) {
		super();
		this.matchDate = matchDate;
		this.localTeam = localTeam;
		this.division = division;
		this.visiteurName = visiteurName;
		this.visiteurLogo = visiteurLogo;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}
	public String getLocalTeam() {
		return localTeam;
	}
	public void setLocalTeam(String localTeam) {
		this.localTeam = localTeam;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getVisiteurName() {
		return visiteurName;
	}
	public void setVisiteurName(String visiteurName) {
		this.visiteurName = visiteurName;
	}
	public String getVisiteurLogo() {
		return visiteurLogo;
	}
	public void setVisiteurLogo(String visiteurLogo) {
		this.visiteurLogo = visiteurLogo;
	}
}
