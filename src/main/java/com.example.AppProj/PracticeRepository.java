
package com.example.AppProj;
import java.util.Optional;

//import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracticeRepository extends JpaRepository<BloodBank, Long> {
	Optional<BloodBank> findByUsername(String username);
}
