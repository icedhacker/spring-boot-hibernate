package com.ww.child;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
interface BaseChildRepository<T extends Child> extends JpaRepository<T, Long> {

}
