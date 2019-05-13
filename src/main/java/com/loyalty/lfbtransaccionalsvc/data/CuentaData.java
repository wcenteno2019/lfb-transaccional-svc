package com.loyalty.lfbtransaccionalsvc.data;

import com.loyalty.lfbtransaccionalsvc.pojo.database.CuentasBancarias;
import com.loyalty.lfbtransaccionalsvc.pojo.database.PrestamosBancarios;
import com.loyalty.lfbtransaccionalsvc.pojo.database.TarjetasCreditos;
import com.loyalty.lfbtransaccionalsvc.pojo.response.Cuenta;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryCuenta;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryPrestamos;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryTarjeta;

import java.util.ArrayList;

public class CuentaData {
    private RepositoryCuenta repositoryCuenta;
    private RepositoryPrestamos repositoryPrestamos;
    private RepositoryTarjeta repositoryTarjeta;

    public CuentaData(RepositoryCuenta repositoryCuenta, RepositoryPrestamos repositoryPrestamos ,RepositoryTarjeta repositoryTarjeta)
    {
        this.repositoryCuenta= repositoryCuenta;
        this.repositoryPrestamos = repositoryPrestamos;
        this.repositoryTarjeta = repositoryTarjeta;
    }
    public ArrayList<Cuenta> getCuentas(int idUsuario){
        ArrayList<Cuenta> lstCuentas = new ArrayList<>();
        ArrayList<CuentasBancarias> cuentasBancarias= repositoryCuenta.getCuenta(idUsuario);
        if (cuentasBancarias.size()>0 || !cuentasBancarias.isEmpty()){
            for(CuentasBancarias ctb: cuentasBancarias){
                Cuenta cuenta= new Cuenta();
                cuenta.setId(ctb.getId());
                cuenta.setName(ctb.getCubNombre());
                cuenta.setTipo(ctb.getTipoProducto().getId());
                lstCuentas.add(cuenta);

            }
        }
        ArrayList<PrestamosBancarios> prestamosBancarios= repositoryPrestamos.getPrestamosBancarios(idUsuario);
        if (prestamosBancarios.size()>0 || !prestamosBancarios.isEmpty()){
            for(PrestamosBancarios ptb: prestamosBancarios){
                Cuenta cuenta= new Cuenta();
                cuenta.setId(ptb.getId());
                cuenta.setName(ptb.getPrbNombre());
                cuenta.setTipo(ptb.getTipoProducto().getId());
                lstCuentas.add(cuenta);

            }
        }
        ArrayList<TarjetasCreditos> tarjetasCreditos= repositoryTarjeta.getTarjeta(idUsuario);
        if (tarjetasCreditos.size()>0 || !tarjetasCreditos.isEmpty()){
            for(TarjetasCreditos tc: tarjetasCreditos){
                Cuenta cuenta= new Cuenta();
                cuenta.setId(tc.getId());
                cuenta.setName(tc.getTcNombre());
                cuenta.setTipo(tc.getTipoProducto().getId());
                lstCuentas.add(cuenta);

            }
        }
        return lstCuentas;

    }
}
