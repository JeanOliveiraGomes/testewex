package com.wex.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wex.test.model.Transaction;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	

}
