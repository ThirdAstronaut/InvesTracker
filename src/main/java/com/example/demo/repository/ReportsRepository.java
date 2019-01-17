package com.example.demo.repository;

import com.example.demo.domain.Reports;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Reports entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportsRepository extends JpaRepository<Reports, Long> {

}
