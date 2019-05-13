package com.loyalty.lfbtransaccionalsvc.pojo.database;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRESTAMOS_BANCARIOS")
public class PrestamosBancarios {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "prb_id")
    private String  id;
    @Column(name = "prb_nombre")
    private String prbNombre;

    @OneToOne
    @JoinColumn(name = "prb_tip_id", referencedColumnName = "tip_id")
    private TipoProducto  tipoProducto;

    @ManyToOne
    @JoinColumn(name = "prb_usr_id", referencedColumnName = "usr_id")
    private Usuario  usuario;
    @Column(name = "prb_saldo")
    private Double saldo;

    @Column(name = "prb_interes_mensual")
     private Double interesMesual;

    @Column(name = "prb_total_interes")
    private Double interesTotal;

    @Column(name = "prb_periodo_meses")
    private int periodoPrestamo;

    @Column(name = "prb_estado")
    private String estado;

    @Column(name = "prb_fecha_inicio")
    private LocalDateTime fechaInicio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrbNombre() {
        return prbNombre;
    }

    public void setPrbNombre(String prbNombre) {
        this.prbNombre = prbNombre;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getInteresMesual() {
        return interesMesual;
    }

    public void setInteresMesual(Double interesMesual) {
        this.interesMesual = interesMesual;
    }

    public Double getInteresTotal() {
        return interesTotal;
    }

    public void setInteresTotal(Double interesTotal) {
        this.interesTotal = interesTotal;
    }

    public int getPeriodoPrestamo() {
        return periodoPrestamo;
    }

    public void setPeriodoPrestamo(int periodoPrestamo) {
        this.periodoPrestamo = periodoPrestamo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
