package com.example.AppProj;

import com.example.AppProj.Practice;

import com.example.AppProj.PracticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.AppProj.BloodBank;

import java.util.List;
import java.util.Optional;
@Service
public class PracticeService {

    @Autowired
    private PracticeRepository repository;// Only Id, Username, Password
    @Autowired
    private BlooDetailsRepository repositorydets;// The other customer details
    
    public List<BloodBank> findAll() {
        return repository.findAll();
    }
    public void saveInBank(BloodBank bank) {
        repository.save(bank);
    }
    /*public Optional<String> authenticate(String username, String password) {
    	Optional<BloodBank> bloodBank = repository.findByUsername(username);
        if (bloodBank.isPresent() && bloodBank.get().getPass().equals(password)) {
            return Optional.of(bloodBank.get().getName());
        }
        return Optional.empty();
    }*/
    /*public Optional<String> authenticate(String username, String password) {    
        Optional<BloodBank> bloodBank = repository.findByUsername(username);    
        if (bloodBank.isPresent()) {
            System.out.println("BloodBank found: " + bloodBank.get().getName());
            if (bloodBank.get().getPass().equals(password)) {
                return Optional.of(bloodBank.get().getName());
            } else {
                System.out.println("Password mismatch.");
            }
        } else {
            System.out.println("BloodBank not found.");
        }
        return Optional.empty();
    } */   
	   public Optional<BloodBank> authenticate(String username, String password) {
		    Optional<BloodBank> bloodBankOpt = repository.findByUsername(username); 
		    
		    if (bloodBankOpt.isPresent()) {
		        BloodBank bloodBank = bloodBankOpt.get(); // Getting the BloodBank instance
		        
		        // Accessing BlooDetails through BloodBank
		        BlooDetails personDetails = bloodBank.getBloodetails(); 
		        
		        if (personDetails != null) {
		            System.out.println("BloodBank found: " + personDetails.getName()); // Access name from BlooDetails
		        }

		        if (bloodBank.getPass().equals(password)) {
		            return bloodBankOpt; // Return the original Optional
		        } else {
		            System.out.println("Password mismatch.");
		        }
		    } else {
		        System.out.println("BloodBank not found.");
		    }
		    
		    return Optional.empty(); 
		}

       /* BlooDetails details = bloodBank.getBloodetails();
        if (bloodBank.isPresent()) {
            System.out.println("BloodBank found: " + bloodBank.get().getName());
            if (bloodBank.get().getPass().equals(password)) {
                return bloodBank;
            } else {
                System.out.println("Password mismatch.");
            }
        } else {
            System.out.println("BloodBank not found.");
        }
        return Optional.empty();
    }  */
    public List<BlooDetails> findByBloodGroup(String bloodGroup) {
        return repositorydets.findByBGroup(bloodGroup);
    }
    public Optional<BloodBank> findWhere(Long id) {
        return repository.findById(id);
    }
}