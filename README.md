# BloodBonding
**Simple Project for Online Blood Donation and Blood Reception** <br>
Hello, I am Joe and this is my fourth project, which is a simple Online BloodBank website where you can donate or receive blood. <br>

**What it does** 
1) First of all, it asks for login, if new, then register. Then you can choose to either donate or receive. <br>
2) If you choose to receive, you can choose your preferred donor, and an email will be sent to them, regarding your request. <br>
3) If you choose to donate, you will get an email of thanks, a certificate of felicitation, and a free frooti. :) <br>
4) The Customer details are all stored in the MySQL Database. <br>
<br>

**Background story of how this project was made and tools used** <br>
I made this project for my college mini project. I did this in my second year.<br>
It took a lot of time and researching to make.<br>
I have used Java, SpringBoot Framework, Thymeleaf, MySQL and used Eclipse IDE. <br>
I have also used HTML templates, CSS for styling and JavaScript, for actions after button clicking. JavaScript is not used much, but to just experiment with what it does. <br>
I have used a Javascript HTML Renderer called html2canvas to take screenshots of the certificate displayed to make the certificate available for download to donors who donated. <br>
I have also used Maven for being able to add dependencies and automating the build process. <br>
I have used ThymeLeaf for Dynamic Html Pages.<br>
Many Images were also used, to enhance the quality of use, which I have included in the Project. <br>
Model View Controller technique is used, involving Entity Class, Service Class, and Controller Class.
<br>
# Framework used: SpringBoot <br>
Command:
> Open a new Spring Project
<br>

# Code to include Ionicons in html. <br>
```html
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
```
<br>
# For being able to send mails, Include this dependency for spring-boot-starter-mail in your pom.xml.
Command:
> <dependency>
  	<groupId>org.springframework.boot</groupId>
  	<artifactId>spring-boot-starter-mail</artifactId>
  </dependency>
<br>
# For being able to add dependencies and automating the build process, Include this dependency for maven in your pom.xml.
Command:
> <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
  </plugin>
<br>
# For being able to make the user download their donation certificate, include this line as CDN for html2canvas into your html file.
Command:
> script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>
