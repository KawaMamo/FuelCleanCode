package org.example.repositories;

import org.example.entities.ClientPayment;
import org.example.model.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface ClientPaymentRepository extends JpaRepository<ClientPayment, Long>,
        PagingAndSortingRepository<ClientPayment, Long>, JpaSpecificationExecutor<ClientPayment> {
    @Query("SELECT c.priceCurrency, SUM(c.priceAmount) FROM ClientPayment c JOIN c.gasStation g  WHERE g.id = ?1 GROUP BY c.priceCurrency")
    List<String[]> getTotalPayments(Long gasStationId);

    @Query("SELECT c FROM ClientPayment c JOIN c.gasStation g  WHERE g.id = ?1 AND c.createdAt BETWEEN ?2 AND ?3 ")
    List<ClientPayment> getPaymentsList(Long clientId, LocalDateTime start, LocalDateTime end);
}
