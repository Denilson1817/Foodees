package com.denilsonperez.foodees.Model;

public class Platillo {
    private String uid;
    private String plato;
    private boolean marca;

    public Platillo() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPlato() {
        return plato;
    }

    public void setPlato(String plato) {
        this.plato = plato;
    }

    public boolean esMarcado(){
        return marca;
    }
    public void setMarca(boolean marca){
        this.marca = marca;
    }

    @Override
    public String toString() {
        return plato;
    }
}
