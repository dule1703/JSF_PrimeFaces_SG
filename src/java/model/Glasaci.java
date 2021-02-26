package model;

import java.io.Serializable;


public class Glasaci implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private int id;
    private String ime;
    private String prezime;
    private String adresa;
    private String mesto;
    private String biracko_mesto;
    private String broj_telefona;
    private String datum;
    private String jmbg;
    private String datum_rodj;
    private String nosilac_glasova;
    private String ime_nosioca_glasova;
    private String opstinski_poverenik;
    private String regionalni_poverenik;
    private String republicki_poverenik;
    private String username;
    private String password;
   
    private String status;

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
    
    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getBiracko_mesto() {
        return biracko_mesto;
    }

    public void setBiracko_mesto(String biracko_mesto) {
        this.biracko_mesto = biracko_mesto;
    }

    public String getBroj_telefona() {
        return broj_telefona;
    }

    public void setBroj_telefona(String broj_telefona) {
        this.broj_telefona = broj_telefona;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getDatum_rodj() {
        return datum_rodj;
    }

    public void setDatum_rodj(String datum_rodj) {
        this.datum_rodj = datum_rodj;
    }

    public String getNosilac_glasova() {
        return nosilac_glasova;
    }

    public void setNosilac_glasova(String nosilac_glasova) {
        this.nosilac_glasova = nosilac_glasova;
    }

    public String getIme_nosioca_glasova() {
        return ime_nosioca_glasova;
    }

    public void setIme_nosioca_glasova(String ime_nosioca_glasova) {
        this.ime_nosioca_glasova = ime_nosioca_glasova;
    }

    public String getOpstinski_poverenik() {
        return opstinski_poverenik;
    }

    public void setOpstinski_poverenik(String opstinski_poverenik) {
        this.opstinski_poverenik = opstinski_poverenik;
    }

    public String getRegionalni_poverenik() {
        return regionalni_poverenik;
    }

    public void setRegionalni_poverenik(String regionalni_poverenik) {
        this.regionalni_poverenik = regionalni_poverenik;
    }

    public String getRepublicki_poverenik() {
        return republicki_poverenik;
    }

    public void setRepublicki_poverenik(String republicki_poverenik) {
        this.republicki_poverenik = republicki_poverenik;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
    
}
