package com.ww.child;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ChildRepository extends CrudRepository<Child, Long> {

}
