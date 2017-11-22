package com.ww.child;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepository extends BaseChildRepository<Child>, JpaRepository<Child, Long> {

}
