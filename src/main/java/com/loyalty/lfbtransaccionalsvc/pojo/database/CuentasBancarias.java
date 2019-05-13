package com.loyalty.lfbtransaccionalsvc.pojo.database;

import javax.persistence.*;

@Entity
@Table(name= "CUENTAS_BANCARIAS")
public class CuentasBancarias {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "cub_id")
    private String id;

    @Column(name = "cub_nombre")
    private String cubNombre;

    @OneToOne
    @JoinColumn(name = "cub_tip_id", referencedColumnName = "tip_id")
    private TipoProducto  tipoProducto;

    @ManyToOne
    @JoinColumn(name = "cub_usr_id", referencedColumnName = "usr_id")
    private Usuario  usuario;

    @Column(name = "cub_saldo")
    private Double saldo;

    @Column(name = "cub_estado")
    private String estado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCubNombre() {
        return cubNombre;
    }

    public void setCubNombre(String cubNombre) {
        this.cubNombre = cubNombre;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
