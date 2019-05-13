package com.loyalty.lfbtransaccionalsvc.data;

import com.loyalty.lfbtransaccionalsvc.pojo.database.TipoTransaccion;
import com.loyalty.lfbtransaccionalsvc.pojo.database.Transaccion;
import com.loyalty.lfbtransaccionalsvc.process.TransferProcess;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryTarjeta;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryTransaccion;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class TransaccionesData {
    private RepositoryTransaccion repositoryTransaccion;

    public TransaccionesData(RepositoryTransaccion repositoryTransaccion){
        this.repositoryTransaccion = repositoryTransaccion;
    }

    public boolean saveTransaccion(String idCuente,Double cantidad, int tipoTrans,String refNumber, int tipoCuenta, String descripcion){
        Transaccion transaccion = new Transaccion();
        transaccion.setIdCuenta(idCuente);
        transaccion.setCantidad(cantidad);
        transaccion.setTipoCuenta(tipoCuenta);
        transaccion.setDescripcion(descripcion);
        transaccion.setFechaTrans(LocalDateTime.now());
        transaccion.setTipoTransaccion(tipoTrans);
        transaccion.setRefereciaNumber(refNumber);
        repositoryTransaccion.save(transaccion);
      return true;
    }
    public String getReferenceNumber(){
        int numero = ThreadLocalRandom.current().nextInt(1, 1000000000 + 1);
        return "LFB" + String.valueOf(numero);
    }
}
