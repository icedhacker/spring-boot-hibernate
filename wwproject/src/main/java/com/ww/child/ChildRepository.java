package com.ww.child;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildRepository extends BaseChildRepository<Child>, JpaRepository<Child, Long> {

    @Query(value = "SELECT person_id, count(*) FROM child GROUP BY person_id", nativeQuery = true)
    List<Object[]> getChildCountByParent();
}
