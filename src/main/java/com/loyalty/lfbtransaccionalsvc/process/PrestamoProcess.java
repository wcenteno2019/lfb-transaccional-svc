package com.loyalty.lfbtransaccionalsvc.process;

import com.loyalty.lfbtransaccionalsvc.data.TransaccionesData;
import com.loyalty.lfbtransaccionalsvc.pojo.database.CuentasBancarias;
import com.loyalty.lfbtransaccionalsvc.pojo.database.PrestamosBancarios;
import com.loyalty.lfbtransaccionalsvc.pojo.database.TarjetasCreditos;
import com.loyalty.lfbtransaccionalsvc.pojo.prestamos.RequestPrestamo;
import com.loyalty.lfbtransaccionalsvc.pojo.tarjeta.RequestTarjeta;
import com.loyalty.lfbtransaccionalsvc.pojo.tarjeta.ResponseTarjeta;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryCuenta;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryPrestamos;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryTarjeta;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryTransaccion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrestamoProcess {
    public RepositoryTransaccion repositoryTransaccion;
    private RepositoryPrestamos repositoryPrestamos;
    private RepositoryCuenta repositoryCuenta;
    private Logger log;

    public PrestamoProcess(RepositoryTransaccion repositoryTransaccion, RepositoryPrestamos repositoryPrestamos,RepositoryCuenta repositoryCuenta){
        this.repositoryTransaccion = repositoryTransaccion;
        this.repositoryPrestamos = repositoryPrestamos;
        this.repositoryCuenta = repositoryCuenta;
        this.log = LoggerFactory.getLogger(getClass());
    }

    public Object processPagoPrestamo(RequestPrestamo request){
        ResponseTarjeta response = new ResponseTarjeta();
        try {
            PrestamosBancarios prestamosBancarios = repositoryPrestamos.getIdCuenta(request.getCuentaPrestamo(), request.getIdUsuario());
            //Cuenta de Destino
            CuentasBancarias cuentaDebitar = repositoryCuenta.getIdCuenta(request.getCuentaPersonal(), request.getIdUsuario());
            boolean flag = false;
            int updateSaldo = 0;
            TransaccionesData transaccionesData = new TransaccionesData(repositoryTransaccion);
            if ((prestamosBancarios != null) && (cuentaDebitar != null)) {
                log.info("Cuentas Validas para pago de prestamo");
                //Cuenta de Origen
                if (cuentaDebitar.getSaldo() > request.getMonto()) {
                    log.info("Cuenta con saldo suficiente para pago de prestamo");
                    String numAutho = transaccionesData.getReferenceNumber();
                    flag = transaccionesData.saveTransaccion(request.getCuentaPersonal(), request.getMonto(), 2, numAutho,cuentaDebitar.getTipoProducto().getId(),"PAGO DE PRESTAMO PERSONAL-DEBITO");
                    updateSaldo = repositoryCuenta.actualizarSaldo(cuentaDebitar.getSaldo() - request.getMonto(), request.getCuentaPersonal());
                    flag = transaccionesData.saveTransaccion(request.getCuentaPrestamo(), request.getMonto(), 1, numAutho, prestamosBancarios.getTipoProducto().getId(),"PAGO DE PRESTAMO PERSONAL-ABONO");
                    updateSaldo = repositoryPrestamos.actualizarSaldo(prestamosBancarios.getSaldo() - request.getMonto(), request.getCuentaPrestamo());
                    log.info("Movientos realizado con exito en el pago de prestamo: " + numAutho);
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
            log.error("Exception in pago de prestamo endpoint, e: {}", e);
            response.setStatus("500");
            response.setMessage("EROOR");
            response.setNumAutorizacion("");
            return response;
        }

    }
}
