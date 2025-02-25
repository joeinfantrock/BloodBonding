package com.example.AppProj;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.SessionAttributes;
	
	import java.util.stream.Collectors;
	import java.util.Optional;
	import java.util.List;
	import java.util.ArrayList;

	@Controller
	@SessionAttributes({"name","bGroup", "custid","city","email"})
	public class PracticeController {

	    @Autowired //Autowired (Dependency Injection)which means object will automatically be created as beans-no 'new' keyword)
	    private PracticeService practiceService;
	    @Autowired
	    private EmailService emailService;
	    @GetMapping("/")
	    public String getPractices(Model model) {
	        /*model.addAttribute("practices", practiceService.findAll());*/
	     //"practices" – This is the name of the attribute. It is a key that will be used in the Thymeleaf template to access the data. It returns practice objects
	        return "practices";// name of html page
	    }
	    @GetMapping("/error")
	    public String handleError(Model model) {
	        
	        model.addAttribute("error", "An unexpected error occurred.");
	        return "error"; // Make sure `error.html` exists in templates
	    }
	    
	    /*@PostMapping("/submitUser")
	    public String submitForm(@RequestParam("ID") Long id, @RequestParam("name") String name) {
	        Practice user = new Practice();
	        user.setId(id);
	        user.setName(name);
	        practiceService.savePractice(user);
	        //return "redirect:/userForm";
	        return "DonOrRec";
	    }*/
	    
	    @PostMapping("/submitUser")
	    public String submitForm(Model model,@RequestParam("User") String User, @RequestParam("Pass") String Pass) {
	    	/*model.addAttribute("pass",Pass);
	    	model.addAttribute("user",User);
	    	return "DonOrRec"; */
	    	/*BloodBank person = new BloodBank();
	        person.setUser(User);
	        person.setPass(Pass);
	        Optional <String> check = practiceService.authenticate(User,Pass);
	        //return "redirect:/userForm";
	        if (check.isPresent()) {
	            model.addAttribute("name", check.get());
	            model.addAttribute("bloodGroup", person.getBGroup());
	            return "DonOrRec";
	        } else {
	            return "error";
	        }*/
	    	Optional<BloodBank> personOpt = practiceService.authenticate(User, Pass);
	    	if (personOpt.isPresent()) {
	    	    BloodBank person = personOpt.get();
		    	BlooDetails persondetails = person.getBloodetails();
		        
		        // Set model attributes for BloodBank
		        model.addAttribute("custid", person.getId());
		        model.addAttribute("bGroup", persondetails.getBGroup());
		        model.addAttribute("name", persondetails.getName());
		        model.addAttribute("city", persondetails.getCity());
		        model.addAttribute("email",persondetails.getEmail());
		        return "DonOrRec";   
	    	}
	    	else {
	    		model.addAttribute("error", "Invalid username or password.");
	            return "error";
	    	}
	        //FINAL
	        /*if (personOpt.isPresent()) {
	            BloodBank person = personOpt.get(); 
	            model.addAttribute("bloodGroup", person.getBGroup());
	            model.addAttribute("name", person.getName());
	            model.addAttribute("custid",person.getId());
	            model.addAttribute("city",person.getCity());
	            return "DonOrRec";
	        } else {
	            model.addAttribute("error", "Invalid username or password.");
	            return "error";
	        } */
	    	
	    	/*Optional<String> name = practiceService.authenticate(User, Pass);
	        if (name.isPresent()) {
	            model.addAttribute("name", name.get());
	            return "DonOrRec"; // Redirect to a welcome page
	        } else { 
	            model.addAttribute("error", "Invalid username or password");
	            return "error"; // Return to the login form with an error
	        } */
	    }
	    
	    @PostMapping("/Reg")
	    public String receiveSuc() {
	    	
	        return "regis"; // Render the success view
	    }
	    @GetMapping("/Donate")
	    public String donate(Model model) {
	    	Long currentId = (Long) model.getAttribute("custid"); 
	    	System.out.println("I have id " + currentId);
	    	Optional <BloodBank> receiverDetails = practiceService.findWhere(currentId);
	    	BlooDetails personDetails = (receiverDetails.get()).getBloodetails(); 
	    	if(receiverDetails.isEmpty()) {
	    		System.out.println("Empty Bro");
	    	}
	    	else {
	    		System.out.println("working bro");
	    	}
	    	model.addAttribute("user",personDetails);
	    
	    	return "don";
	    }
	    @GetMapping("/DonCerti")
	    public String donSuc(Model model) {
	    	String donname = (String) model.getAttribute("name"); 
	    	model.addAttribute("name",donname);
	       
	    	String to = (String) model.getAttribute("email"); 
	        String subject = "A person is about to be saved because of you.";
	        String text = "Hello "+donname+",\n"+ "Thank you for bleeding for us and donating. You are going to save many lives. \nPlease wait 1-2 months before you donate again. Thank you.";
	        
	        try {
	            emailService.sendEmail(to, subject, text);
	            
	        } catch (Exception e) {
	        	System.out.println("Couldnt send email bro");
	        	
	        }
	        return "donsuc"; 
	    }
	    
	    @GetMapping("/Receive")
	    public String receive(Model model) {
	    	String bloodGroup = (String) model.getAttribute("bGroup"); // Retrieve from session
	    	System.out.println("BloodGroup is "+ bloodGroup);
	        if (bloodGroup != null) {
	            List<BlooDetails> potentialMatches = practiceService.findByBloodGroup(bloodGroup);
	            System.out.println("Got list<BlooDetails>");
	            Long currentId = (Long) model.getAttribute("custid"); 
	            String city = (String) model.getAttribute("city");
	            System.out.println("Retrieved more from ses");
	            List<BlooDetails> filteredMatches = potentialMatches.stream()
	                    .filter(donor -> !donor.getId().equals(currentId))
	                    .collect(Collectors.toList());
	            List<BlooDetails> sameCity = new ArrayList<>();
	            List<BlooDetails> otherCities = new ArrayList<>();
	            
	            for (BlooDetails donor : filteredMatches) {
	                if (donor.getCity().equals(city)) {
	                    sameCity.add(donor);
	                } else {
	                    otherCities.add(donor);
	                }
	            }
	            
	            if(sameCity.isEmpty()) {
	            	
	            }
	            model.addAttribute("sameMatches", sameCity);
	            model.addAttribute("difMatch", otherCities);
	            System.out.println("Blood Group: " + bloodGroup);
	            System.out.println("Current ID: " + currentId);
	            System.out.println("City: " + city);
	            for (BlooDetails donor : otherCities) {
	                System.out.println("Donor Phone: " + donor.getPhone());
	            }
	            Optional <BloodBank> receiverDetails = practiceService.findWhere(currentId); 
	            BlooDetails recDetails = (receiverDetails.get()).getBloodetails(); 
		    	model.addAttribute("usedet",recDetails);//Adding the User's details
	            return "RecList"; 
	        } else {
	        	
	            return "error"; 
	        }
	    }
	    
	    @PostMapping("/RecSuc")
	    public String receiveSuc(Model model,@RequestParam("donorId") Long donorId) {
	    	System.out.println("Im about to print");
	    	System.out.println("I have printed" + donorId);
	    	Optional<BloodBank> donorDetailsOpt = practiceService.findWhere(donorId);
	    	Long currentId = (Long) model.getAttribute("custid");
	    	
	        if (!donorDetailsOpt.isPresent()) {
	            model.addAttribute("error", "Donor not found");
	            return "error"; // Render an error view
	        }
	        BlooDetails donordeets = (donorDetailsOpt.get()).getBloodetails();
	        model.addAttribute("recDeets", donordeets); 
	        String to = donordeets.getEmail();
	        System.out.println("email: "+ donordeets.getEmail());
	        String subject = "A person is about to die because of you.";
	        String text = "Hello "+donordeets.getName()+",\n"+ "A person needs your help, without which he will most probably die. And only YOU can save him. Could you please bleed for him.\n Please email us back as soon as possible. Thank you.";
	        
	        try {
	            emailService.sendEmail(to, subject, text);
	            
	        } catch (Exception e) {
	        	System.out.println("Couldnt send email bro");
	        	
	        }
	        
	        return "recsuc"; 
	    }
	    /*
	    @Autowired
	    private EmailService emailService;

	    @PostMapping("/send-email")
	    public String sendEmail(
	            @RequestParam String to,
	            @RequestParam String subject,
	            @RequestParam String text) {
	        emailService.sendEmail(to, subject, text);
	        return "Email sent successfully!";
	    }
	    */
	    
	    /* Email: 
	      @GetMapping("/send-email")
    public String sendEmail() {
        try {
            emailService.sendHtmlEmail("recipient@example.com", "Test Subject", "Hello, this is a test email with Thymeleaf!");
            return "Email sent successfully!";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Failed to send email.";
        }
    }
	     */
	    /* <form action="/submitMultiplePractices" method="post">
        <label for="name1">Name 1:</label>
        <input type="text" id="name1" name="names" /><br/>
        <label for="name2">Name 2:</label>
        <input type="text" id="name2" name="names" /><br/>
        <label for="name3">Name 3:</label>
        <input type="text" id="name3" name="names" /><br/>
        <button type="submit">Submit</button>
    </form>
	    public String submitMultiplePractices(@RequestParam("names") List<String> names, Model model) {
	        List<Practice> practices = names.stream()
	            .map(name -> {
	                Practice practice = new Practice();
	                practice.setName(name);
	                return practice;
	            })*/
	}

