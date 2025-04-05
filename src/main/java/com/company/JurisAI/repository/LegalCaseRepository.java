package com.company.JurisAI.repository;

import com.company.JurisAI.entities.LegalCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LegalCaseRepository extends JpaRepository<LegalCase, Long> {

    // Full-Text Search (MySQL MATCH AGAINST)
    @Query(value = "SELECT * FROM legal_cases WHERE MATCH(title, description) AGAINST(:keyword IN BOOLEAN MODE)",
            nativeQuery = true)
    List<LegalCase> searchByKeyword(@Param("keyword") String keyword);

    // Search with Filters
    @Query("SELECT lc FROM LegalCase lc " +
            "WHERE (:courtName IS NULL OR lc.courtName = :courtName) " +
            "AND (:caseStatus IS NULL OR lc.caseStatus = :caseStatus) " +
            "AND (:filedDate IS NULL OR lc.filedDate = :filedDate)")
    List<LegalCase> filterCases(@Param("courtName") String courtName,
                                @Param("caseStatus") String caseStatus,
                                @Param("filedDate") LocalDate filedDate);

    List<LegalCase> findByJudge(String judgeName);

}
