package com.loyalty.lfbtransaccionalsvc.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loyalty.lfbtransaccionalsvc.data.CuentaData;
import com.loyalty.lfbtransaccionalsvc.pojo.database.CuentasBancarias;
import com.loyalty.lfbtransaccionalsvc.pojo.response.Cuenta;
import com.loyalty.lfbtransaccionalsvc.pojo.response.CuentaResponse;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryCuenta;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryPrestamos;
import com.loyalty.lfbtransaccionalsvc.respository.RepositoryTarjeta;
import jdk.nashorn.internal.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.util.ArrayList;

public class CuentaProcess {
    private RepositoryCuenta repositoryCuenta;
    private RepositoryPrestamos repositoryPrestamos;
    private RepositoryTarjeta repositoryTarjeta;
    private Environment env;
    private Logger log;
    ObjectMapper mapper = new ObjectMapper();

    public CuentaProcess(RepositoryCuenta repositoryCuenta, RepositoryPrestamos repositoryPrestamos ,RepositoryTarjeta repositoryTarjeta, Environment env){
        this.repositoryCuenta = repositoryCuenta;
        this.repositoryPrestamos = repositoryPrestamos;
        this.repositoryTarjeta = repositoryTarjeta;
        this.env = env;
        this.log = LoggerFactory.getLogger(getClass());
    }

    public CuentaResponse processCuenta(String idUsuario){
        CuentaResponse cuentaResponse = new CuentaResponse();
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        try {
            CuentaData cuentaData = new CuentaData(repositoryCuenta, repositoryPrestamos, repositoryTarjeta);
            cuentas = cuentaData.getCuentas(Integer.parseInt(idUsuario));
            if (cuentas.size() > 0 && cuentaData != null) {
                cuentaResponse.setCuenta(cuentas);
                cuentaResponse.setStatus(200);
                cuentaResponse.setMessage("SUCCES");
            } else {
                cuentaResponse.setStatus(201);
                cuentaResponse.setCuenta(cuentas);
                cuentaResponse.setMessage("NO DATA");
            }
            log.info("response: " + mapper.writeValueAsString(cuentaResponse));
            return cuentaResponse;
        }catch (Exception e){
            cuentaResponse.setStatus(202);
            cuentaResponse.setCuenta(cuentas);
            cuentaResponse.setMessage("NO DATA");
            return cuentaResponse;

        }
    }
}
