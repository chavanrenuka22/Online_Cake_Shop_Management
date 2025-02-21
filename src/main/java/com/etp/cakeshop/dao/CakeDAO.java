package com.etp.cakeshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etp.cakeshop.entity.Cake;

/**
 * @author Swapnil
 *
 */
public interface CakeDAO extends JpaRepository<Cake, Long> {

}
