package com.loyalty.lfbtransaccionalsvc.pojo.database;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACCIONES")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tra_id")
    private int id;

    @Column(name = "tra_id_cuenta")
    private String idCuenta;

    @Column(name="tra_cantidad")
    private double cantidad;

    @Column(name = "tra_tipo_cuenta")
    private int tipoCuenta;

    @Column(name = "tra_fecha")
    private LocalDateTime fechaTrans;


    @Column(name = "tra_tipo")
    private int tipoTransaccion;

    @Column(name = "tra_descripcion")
    private String descripcion;

    @Column(name = "tra_referencia")
     private String refereciaNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaTrans() {
        return fechaTrans;
    }

    public void setFechaTrans(LocalDateTime fechaTrans) {
        this.fechaTrans = fechaTrans;
    }

    public int getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(int tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getRefereciaNumber() {
        return refereciaNumber;
    }

    public void setRefereciaNumber(String refereciaNumber) {
        this.refereciaNumber = refereciaNumber;
    }

    public int getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(int tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
