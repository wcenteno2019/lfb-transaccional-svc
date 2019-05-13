package com.loyalty.lfbtransaccionalsvc.respository;

import com.loyalty.lfbtransaccionalsvc.pojo.database.PrestamosBancarios;
import com.loyalty.lfbtransaccionalsvc.pojo.database.TarjetasCreditos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface RepositoryPrestamos extends JpaRepository<PrestamosBancarios, String> {
    @Query(value = "select * from lifebank_db.prestamos_bancarias " +
            "inner join lifebank_db.usuario on usr_id = prb_usr_id and usr_estado = 'A' "  +
            "where prb_usr_id =:usuario ", nativeQuery = true)
    ArrayList<PrestamosBancarios> getPrestamosBancarios(@Param("usuario") int usuario);

    @Query(value = "select * from lifebank_db.prestamos_bancarias " +
            "where prb_id =(:idPrestamo) and prb_estado = 'A' and prb_usr_id =:idUser",nativeQuery = true)
    PrestamosBancarios getIdCuenta(@Param("idPrestamo") String idPrestamo, @Param("idUser") int idUser);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE lifebank_db.prestamos_bancarias SET prb_saldo=(:saldo) where prb_id = (:idCuenta) and prb_estado='A'", nativeQuery = true)
    @Transactional(rollbackFor = { Exception.class })
    int actualizarSaldo(@Param("saldo") Double saldo, @Param("idCuenta") String idCuenta);
}
