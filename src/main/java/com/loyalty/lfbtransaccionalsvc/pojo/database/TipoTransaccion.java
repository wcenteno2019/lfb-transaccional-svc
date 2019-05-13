package com.loyalty.lfbtransaccionalsvc.pojo.database;

import javax.persistence.*;

@Entity
@Table(name = "TIPO_TRANSACCION")
public class TipoTransaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trs_id")
    private int id;

    @Column(name="trs_nombre")
    private String nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
