package com.tansha.library.bookshelf.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.tansha.library.bookshelf.admin.model.PaymentConfirm;
import com.tansha.library.bookshelf.admin.model.Role;


import org.springframework.data.repository.CrudRepository;
@Transactional
public interface PaymentConfirmRepository extends CrudRepository<Role,String> {

	
	
	
}

