package com.loyalty.lfbtransaccionalsvc.process;

import com.loyalty.lfbtransaccionalsvc.data.TransaccionesData;
import com.loyalty.lfbtransaccionalsvc.pojo.database.CuentasBancarias;
import com.loyalty.lfbtransaccionalsvc.pojo.transfer.RequestTransfer;
import com.loyalty.lfbtransaccionalsvc.pojo.transfer.ResponseTransfer;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryCuenta;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryTransaccion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferProcess {
    public RepositoryTransaccion repositoryTransaccion;
    private RepositoryCuenta repositoryCuenta;
    private Logger log;

    public TransferProcess(RepositoryTransaccion repositoryTransaccion, RepositoryCuenta repositoryCuenta){
        this.repositoryTransaccion = repositoryTransaccion;
        this.repositoryCuenta = repositoryCuenta;
        this.log = LoggerFactory.getLogger(getClass());
    }

    public Object processTransfer(RequestTransfer request){
        ResponseTransfer  response = new ResponseTransfer();
        try {
            CuentasBancarias cuentaOrigen = repositoryCuenta.getIdCuenta(request.getCuentaOrigen(), request.getIdUsuario());
            //Cuenta de Destino
            CuentasBancarias cuentaDestino = repositoryCuenta.getIdCuenta(request.getCuentaDestino(), request.getIdUsuario());
            boolean flag = false;
            int updateSaldo = 0;
            TransaccionesData transaccionesData = new TransaccionesData(repositoryTransaccion);
            if ((cuentaOrigen != null) && (cuentaDestino != null)) {
                log.info("Cuentas Validas para transferencia");
                //Cuenta de Origen
                if (cuentaOrigen.getSaldo() > request.getMonto()) {
                    log.info("Cuenta con saldo suficiente para transferencia");
                    String numAutho = transaccionesData.getReferenceNumber();
                    flag = transaccionesData.saveTransaccion(request.getCuentaOrigen(), request.getMonto(), 2, numAutho,cuentaOrigen.getTipoProducto().getId(),"TRANSFERENCIA ENTRE CUENTAS PROPIAS-DEBITO");
                    updateSaldo = repositoryCuenta.actualizarSaldo(cuentaOrigen.getSaldo() - request.getMonto(), request.getCuentaOrigen());
                    flag = transaccionesData.saveTransaccion(request.getCuentaDestino(), request.getMonto(), 1, numAutho, cuentaDestino.getTipoProducto().getId(),"TRANSFERENCIA ENTRE CUENTAS PROPIAS-ABONO");
                    updateSaldo = repositoryCuenta.actualizarSaldo(cuentaDestino.getSaldo() + request.getMonto(), request.getCuentaDestino());
                    log.info("Movientos realizado con exito en la transferencia: " + numAutho);
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
                response.setMessage("EROOR EN CUENTAS");
                response.setNumAutorizacion("");
                return response;
            }
        }catch (Exception e){
            log.error("Exception in transferencia endpoint, e: {}", e);
            response.setStatus("500");
            response.setMessage("EROOR");
            response.setNumAutorizacion("");
            return response;
        }

    }
}
