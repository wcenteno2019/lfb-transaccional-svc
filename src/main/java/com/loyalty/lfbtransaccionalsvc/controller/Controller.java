package com.loyalty.lfbtransaccionalsvc.controller;

import com.loyalty.lfbtransaccionalsvc.pojo.prestamos.RequestPrestamo;
import com.loyalty.lfbtransaccionalsvc.pojo.response.CuentaResponse;
import com.loyalty.lfbtransaccionalsvc.pojo.tarjeta.RequestTarjeta;
import com.loyalty.lfbtransaccionalsvc.pojo.transfer.RequestTransfer;
import com.loyalty.lfbtransaccionalsvc.process.CuentaProcess;
import com.loyalty.lfbtransaccionalsvc.process.PrestamoProcess;
import com.loyalty.lfbtransaccionalsvc.process.TarjetaProcess;
import com.loyalty.lfbtransaccionalsvc.process.TransferProcess;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryCuenta;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryPrestamos;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryTarjeta;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryTransaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lfb/transacciones")
public class Controller {
    @Autowired
    private RepositoryCuenta repositoryCuenta;
    @Autowired
    private RepositoryPrestamos repositoryPrestamos;
    @Autowired
    private RepositoryTarjeta repositoryTarjeta;
    @Autowired
    private RepositoryTransaccion repositoryTransaccion;


    @Autowired
    private Environment env;
    @GetMapping("/obtener-cuentas/{idUsuario}")
    public CuentaResponse getCuentas(@PathVariable("idUsuario") String idUsuario){
        CuentaProcess cuentaProcess = new CuentaProcess(repositoryCuenta,  repositoryPrestamos,repositoryTarjeta,env);
        return cuentaProcess.processCuenta(idUsuario);

    }
    @PostMapping("/transfer")
    public Object transfer(@RequestBody RequestTransfer request){
        TransferProcess transferProcess = new TransferProcess(repositoryTransaccion,repositoryCuenta);
        return  transferProcess.processTransfer(request);
    }
    @PostMapping("/pago-tarjeta")
    public Object pagoTarjeta(@RequestBody RequestTarjeta request){
        TarjetaProcess tarjetaProcess = new TarjetaProcess(repositoryTransaccion,repositoryTarjeta,repositoryCuenta);
        return  tarjetaProcess.processPagoTarjeta(request);
    }
    @PostMapping("/pago-prestamo")
    public Object pagoTarjeta(@RequestBody RequestPrestamo request){
        PrestamoProcess prestamoProcess = new PrestamoProcess(repositoryTransaccion,repositoryPrestamos,repositoryCuenta);
        return  prestamoProcess.processPagoPrestamo(request);
    }
}
