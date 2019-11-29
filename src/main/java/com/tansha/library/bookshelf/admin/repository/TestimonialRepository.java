package com.tansha.library.bookshelf.admin.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import com.tansha.library.bookshelf.admin.model.Testimonial;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
@Transactional
public interface TestimonialRepository extends CrudRepository<Testimonial,String> {

	Testimonial findById(int id);
	Testimonial findByIdAndUserid(int id,int userid);
	Optional<Testimonial> findByUserid(int userid);
	
	@Query("SELECT testimoni.userid,testimoni.testimonial,testimoni.isApproved,testimoni.id,testimoni.isActive from Testimonial testimoni WHERE testimoni.isApproved = 0  ")
	List<Object[]> getTestimonials();
	
	
	 
}