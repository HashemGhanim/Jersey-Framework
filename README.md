# Jersey-Framework
In ITG - Company I already study the basic way to make backend server based on java (programming language) First I had studied about Servlet and what the difference between Servlet and CGI (Common Gateway Interface) and the next step was Jersey before Spring Core and Spring Boot


# Servlet Interface :
   A technology is used to build a web application (server side and generates a dynamic web pages)

#### Servlet VS CGI (Common Gateway Interface) :
  At The begining time when you need to build a web application you should use CGI to build it, But CGI it is not robust way to build a web application and lowest performance because in CGI when the HttpRequest reach the Server CGI will pass Http Request to external program to process the Request  
  <br/>
  <img width="640" alt="Screenshot 2023-09-15 at 15 42 05" src="https://github.com/HashemGhanim/Jersey-Framework/assets/72875896/1cd8464d-834e-43ea-9f6f-e5135e420e5f">
  <br/>
and this lead for a lot disadvantages like : 
1- Clients increases, it takes more time for sending the response.
2- For each request, it starts a process, and the web server is limited to start processes.

  Servlet came to made a solution for this issue, So when you use Servlet Interfaces the web server will contain an web container that receive an Http Request and make a multi Threads for all requests that already received in web container and this make the performance better, Robust, protability and Secure.
  <br/>
<img width="533" alt="Screenshot 2023-09-15 at 15 50 01" src="https://github.com/HashemGhanim/Jersey-Framework/assets/72875896/ec1260e3-4c97-495e-9263-69287c8463c0">
  <br/>


  
