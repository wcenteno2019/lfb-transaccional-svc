package com.loyalty.lfbtransaccionalsvc.respository;

import com.loyalty.lfbtransaccionalsvc.pojo.database.CuentasBancarias;
import com.loyalty.lfbtransaccionalsvc.pojo.database.TarjetasCreditos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface RepositoryTarjeta extends JpaRepository<TarjetasCreditos, String> {
    @Query(value = "select * from lifebank_db.tarjeta_creditos " +
            "inner join lifebank_db.usuario on usr_id = tc_usr_id and usr_estado ='A' " +
            "where tc_usr_id = :usuario ", nativeQuery = true)
    ArrayList<TarjetasCreditos> getTarjeta(@Param("usuario") int usuario);

    @Query(value = "select * from lifebank_db.tarjeta_creditos " +
            "where tc_id =(:idTC) and tc_estado = 'A' and tc_usr_id =:idUser",nativeQuery = true)
    TarjetasCreditos getIdCuenta(@Param("idTC") String idTC, @Param("idUser") int idUser);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE lifebank_db.tarjeta_creditos SET tc_saldo=(:saldo) where tc_id = (:idCuenta) and tc_estado='A'", nativeQuery = true)
    @Transactional(rollbackFor = { Exception.class })
    int actualizarSaldo(@Param("saldo") Double saldo, @Param("idCuenta") String idCuenta);
}
