document.addEventListener("DOMContentLoaded", () => {
    /*const custNameElement = document.querySelector("h1.custname");
    if (custNameElement) {
        custNameElement.style.backgroundColor = "rgba(0, 0, 0, 0.5)";
    } */

    const donateBtn = document.getElementById("donateBtn");
    const receiveBtn = document.getElementById("receiveBtn");

    if (donateBtn) {
        donateBtn.addEventListener("click", function() {
            console.log("Donate button clicked");
            window.location.href = "/Donate"; 
        });
    }

    if (receiveBtn) {
        receiveBtn.addEventListener("click", function() {
            console.log("Receive button clicked");
            window.location.href = "/Receive"; 
        });
    }
	let selectedDonorId = null;
	window.toggleDetails = function(id) {
	        const detailsDiv = document.getElementById('details-' + id);
	        if (detailsDiv) {
	            detailsDiv.style.display = (detailsDiv.style.display === "none" || detailsDiv.style.display === "") ? "block" : "none";
				selectedDonorId = id;
			}
	    };

	   /* window.selectPerson = function(name) {
	        alert(name + ' has been selected!');
	    };*/
			
		window.submitSelectedDonor = function() {
		    if (selectedDonorId) {
		        const form = document.createElement('form');
		        form.method = 'post';
		        form.action = '/RecSuc'; // Adjust to your route

		        const input = document.createElement('input');
		        input.type = 'hidden';
		        input.name = 'donorId';
		        input.value = selectedDonorId;

		        form.appendChild(input);
		        document.body.appendChild(form);
		        form.submit(); // Submit the form
		    } else {
		        alert("Please select a donor first.");
		    }
		};
		window.Dondet = function() {
					window.location.href = "/Donate"; 
		}
				
		window.ToFinalDon = function() {
			window.location.href = "/DonCerti"; 
		}

	/*	document.addEventListener('DOMContentLoaded', function() {
		            const button = document.getElementById('downloadButton');
		            if (button) {
		                button.addEventListener('click', function() {
		                    console.log('Button clicked!'); // Check if this logs
		                });
		            } else {
		                console.error('Button not found!');
		            }
		        });
    */
		            document.getElementById('downloadButton').addEventListener('click', function() {
		               html2canvas(document.getElementById('certidown')).then(function(canvas) {
						console.log(typeof html2canvas);
		                    const link = document.createElement('a');
		                    link.href = canvas.toDataURL('image/png');
		                    link.download = 'content.png';
		                    document.body.appendChild(link);
		                    link.click();
		                    document.body.removeChild(link); 
							/* console.log("cow");
							alert("cow"); */
		                })
					}); 
		          
		        
/*	const donateBtn2 = document.getElementById("donateBtn");
	const receiveBtn2 = document.getElementById("receiveBtn");

	if (donateBtn) {
	    donateBtn.addEventListener("click", function() {
	        console.log("Donate button clicked");
	        window.location.href = "/Donate"; 
	    });
	}

	if (receiveBtn) {
	    receiveBtn.addEventListener("click", function() {
	        console.log("Receive button clicked");
	        window.location.href = "/Receive"; 
	    });
	}*/
	
});




