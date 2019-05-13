package com.loyalty.lfbtransaccionalsvc.process;

import com.loyalty.lfbtransaccionalsvc.data.TransaccionesData;
import com.loyalty.lfbtransaccionalsvc.pojo.database.CuentasBancarias;
import com.loyalty.lfbtransaccionalsvc.pojo.database.TarjetasCreditos;
import com.loyalty.lfbtransaccionalsvc.pojo.tarjeta.RequestTarjeta;
import com.loyalty.lfbtransaccionalsvc.pojo.tarjeta.ResponseTarjeta;
import com.loyalty.lfbtransaccionalsvc.pojo.transfer.RequestTransfer;
import com.loyalty.lfbtransaccionalsvc.pojo.transfer.ResponseTransfer;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryCuenta;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryTarjeta;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryTransaccion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TarjetaProcess {
    public RepositoryTransaccion repositoryTransaccion;
    private RepositoryTarjeta repositoryTarjeta;
    private RepositoryCuenta repositoryCuenta;
    private Logger log;

    public TarjetaProcess(RepositoryTransaccion repositoryTransaccion, RepositoryTarjeta repositoryTarjeta,RepositoryCuenta repositoryCuenta){
        this.repositoryTransaccion = repositoryTransaccion;
        this.repositoryTarjeta = repositoryTarjeta;
        this.repositoryCuenta = repositoryCuenta;
        this.log = LoggerFactory.getLogger(getClass());
    }

    public Object processPagoTarjeta(RequestTarjeta request){
        ResponseTarjeta response = new ResponseTarjeta();
        try {
            TarjetasCreditos tarjetasCreditos = repositoryTarjeta.getIdCuenta(request.getCuentaTarjeta(), request.getIdUsuario());
            //Cuenta de Destino
            CuentasBancarias cuentaDebitar = repositoryCuenta.getIdCuenta(request.getCuentaPersonal(), request.getIdUsuario());
            boolean flag = false;
            int updateSaldo = 0;
            TransaccionesData transaccionesData = new TransaccionesData(repositoryTransaccion);
            if ((tarjetasCreditos != null) && (cuentaDebitar != null)) {
                log.info("Cuentas Validas para pago de tarjeta");
                //Cuenta de Origen
                if (cuentaDebitar.getSaldo() > request.getMonto()) {
                    log.info("Cuenta con saldo suficiente para pago de tarjeta");
                    String numAutho = transaccionesData.getReferenceNumber();
                    flag = transaccionesData.saveTransaccion(request.getCuentaPersonal(), request.getMonto(), 2, numAutho,cuentaDebitar.getTipoProducto().getId(),"PAGO DE TARJETA PERSONAL-DEBITO");
                    updateSaldo = repositoryCuenta.actualizarSaldo(cuentaDebitar.getSaldo() - request.getMonto(), request.getCuentaPersonal());
                    flag = transaccionesData.saveTransaccion(request.getCuentaTarjeta(), request.getMonto(), 1, numAutho, tarjetasCreditos.getTipoProducto().getId(),"PAGO DE TARJETA PERSONAL-ABONO");
                    updateSaldo = repositoryTarjeta.actualizarSaldo(tarjetasCreditos.getSaldo() + request.getMonto(), request.getCuentaTarjeta());
                    log.info("Movientos realizado con exito en pago de tarjeta: " + numAutho);
                    response.setStatus("200");
                    response.setMessage("SUCESS");
                    response.setNumAutorizacion(numAutho);
                    return response;
                } else {
                    log.info("Saldo insufiente");
                    response.setStatus("409");
                    response.setMessage("SALDO INSUFICIENTE");
                    response.setNumAutorizacion("");
                    return response;
                }
            } else {
                log.info("Las cuentas no son del cliente reportado");
                response.setStatus("404");
                response.setMessage("ERROR EN CUENTAS");
                response.setNumAutorizacion("");
                return response;
            }
        }catch (Exception e){
            log.error("Exception in pago tarjeta endpoint, e: {}", e);
            response.setStatus("500");
            response.setMessage("EROOR");
            response.setNumAutorizacion("");
            return response;
        }

    }
}
