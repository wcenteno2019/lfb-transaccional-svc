package com.loyalty.lfbtransaccionalsvc.respository;

import com.loyalty.lfbtransaccionalsvc.pojo.database.CuentasBancarias;
import com.loyalty.lfbtransaccionalsvc.pojo.response.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface RepositoryCuenta extends JpaRepository<CuentasBancarias, String> {
    @Query(value = "select * from lifebank_db.cuentas_bancarias " +
            "inner join lifebank_db.usuario on usr_id = cub_usr_id and usr_estado = 'A' " +
            "where cub_usr_id =:usuario ",nativeQuery = true)
    ArrayList<CuentasBancarias> getCuenta(@Param("usuario") int usuario);

    @Query(value = "select * from lifebank_db.cuentas_bancarias " +
            "where cub_id =(:idCuenta) and cub_estado = 'A' and cub_usr_id =:idUser",nativeQuery = true)
    CuentasBancarias getIdCuenta(@Param("idCuenta") String idCuenta, @Param("idUser") int idUser);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE lifebank_db.cuentas_bancarias SET cub_saldo=(:saldo) where cub_id = (:idCuenta) and cub_estado='A'", nativeQuery = true)
    @Transactional(rollbackFor = { Exception.class })
    int actualizarSaldo(@Param("saldo") Double saldo, @Param("idCuenta") String idCuenta);
}
