package model;

import java.io.Serializable;

public class Opstine implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private int id;
    private int opstina_id;
    private int region_id;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOpstina_id() {
        return opstina_id;
    }

    public void setOpstina_id(int opstina_id) {
        this.opstina_id = opstina_id;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

  }
