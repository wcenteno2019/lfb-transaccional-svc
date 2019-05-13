package com.loyalty.lfbtransaccionalsvc.pojo.database;

import javax.persistence.*;

@Entity
@Table(name = "TIPO_PRODUCTO")
public class TipoProducto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "tip_id")
    private int id;

    @Column(name = "tip_nombre")
    private String tipNombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipNombre() {
        return tipNombre;
    }

    public void setTipNombre(String tipNombre) {
        this.tipNombre = tipNombre;
    }
}
