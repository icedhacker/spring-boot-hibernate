package com.ww.child;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentSummaryRepository extends JpaRepository<ParentSummary, Long> {
}
