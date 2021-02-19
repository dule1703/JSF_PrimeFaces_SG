package model;

import java.io.Serializable;


public class SpisakOpstina implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private int id;
    private String naziv_opstine;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv_opstine() {
        return naziv_opstine;
    }

    public void setNaziv_opstine(String naziv_opstine) {
        this.naziv_opstine = naziv_opstine;
    }
}
