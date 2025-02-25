package com.example.AppProj;
//import java.util.Optional;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlooDetailsRepository extends JpaRepository<BlooDetails, Long> {
	List<BlooDetails> findByBGroup(String bloodGroup); 
}