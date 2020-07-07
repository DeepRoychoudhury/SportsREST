package com.restfulapi.pojo;

public class SportsObject {
private String country;
private String sportsplayed;
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getSportsplayed() {
	return sportsplayed;
}
public void setSportsplayed(String sportsplayed) {
	this.sportsplayed = sportsplayed;
}
@Override
public String toString() {
	return "SportsObject [country=" + country + ", sportsplayed=" + sportsplayed + "]";
}
}
