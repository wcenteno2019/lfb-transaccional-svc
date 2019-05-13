package com.loyalty.lfbtransaccionalsvc.respository;

import com.loyalty.lfbtransaccionalsvc.pojo.database.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryTransaccion extends JpaRepository<Transaccion, Integer> {
}
