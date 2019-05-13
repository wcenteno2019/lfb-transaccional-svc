package com.loyalty.lfbtransaccionalsvc.pojo.database;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TARJETA_CREDITO")
public class TarjetasCreditos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tc_id")
    private String id;

    @Column(name = "tc_nombre")
    private String tcNombre;

    @OneToOne
    @JoinColumn(name = "tc_tip_id", referencedColumnName = "tip_id")
    private TipoProducto  tipoProducto;

    @ManyToOne
    @JoinColumn(name = "tc_usr_id", referencedColumnName = "usr_id")
    private Usuario  usuario;

    @Column(name = "tc_limite")
    private Double limiteSaldo;

    @Column(name = "tc_saldo")
    private Double saldo;

    @Column(name = "tc_costo_membresia")
    private int costoMembresia;

    @Column(name = "tc_interes_mensual")
    private Double interesMensual;

    @Column(name = "tc_total_interes")
    private Double interesTotal;

    @Column(name = "tc_estado")
    private String estado;

    @Column(name = "tc_fecha_corte")
    private int fechaCorte;

    @Column(name = "tc_fecha_pago")
    private int fechaPago;

    @Column(name = "tc_fecha_cancelacion")
    private LocalDateTime fechaCancelacion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTcNombre() {
        return tcNombre;
    }

    public void setTcNombre(String tcNombre) {
        this.tcNombre = tcNombre;
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

    public Double getLimiteSaldo() {
        return limiteSaldo;
    }

    public void setLimiteSaldo(Double limiteSaldo) {
        this.limiteSaldo = limiteSaldo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public int getCostoMembresia() {
        return costoMembresia;
    }

    public void setCostoMembresia(int costoMembresia) {
        this.costoMembresia = costoMembresia;
    }

    public Double getInteresMensual() {
        return interesMensual;
    }

    public void setInteresMensual(Double interesMensual) {
        this.interesMensual = interesMensual;
    }

    public Double getInteresTotal() {
        return interesTotal;
    }

    public void setInteresTotal(Double interesTotal) {
        this.interesTotal = interesTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(int fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public int getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(int fechaPago) {
        this.fechaPago = fechaPago;
    }

    public LocalDateTime getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(LocalDateTime fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }
}
