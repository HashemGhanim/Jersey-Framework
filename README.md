# Jersey-Framework
In ITG - Company I already study the basic way to make backend server based on java (programming language) First I had studied about Servlet and what the difference between Servlet and CGI (Common Gateway Interface) and the next step was Jersey before Spring Core and Spring Boot


# Servlet Interface :
   A technology is used to build a web application (server side and generates a dynamic web pages)

#### Servlet VS CGI (Common Gateway Interface) :
  At The begining time when you need to build a web application you should use CGI to build it, But CGI it is not robust way to build a web application and lowest performance because in CGI when the HttpRequest reach the Server CGI will pass Http Request to external program to process the Request  
  <br/>
  <img width="640" alt="Screenshot 2023-09-15 at 15 42 05" src="https://github.com/HashemGhanim/Jersey-Framework/assets/72875896/1cd8464d-834e-43ea-9f6f-e5135e420e5f">
  <br/>
and this lead for a lot disadvantages like : <br/>
1- Clients increases, it takes more time for sending the response.<br/>
2- For each request, it starts a process, and the web server is limited to start processes.<br/>

  Servlet came to made a solution for this issue, So when you use Servlet Interfaces the web server will contain an web container that receive an Http Request and make a multi Threads for all requests that already received in web container and this make the performance better, Robust, protability and Secure.
  <br/>
<img width="533" alt="Screenshot 2023-09-15 at 15 50 01" src="https://github.com/HashemGhanim/Jersey-Framework/assets/72875896/ec1260e3-4c97-495e-9263-69287c8463c0">
  <br/>

Ok let us take a simple example using servlet, I'm the clinet who is need to open a page and it's path ```http://localhost:8080/info``` so if the request is about a static page not dynamic the server will return the contant of this page immediately, But otherwise if the request about a dynamic (server-side generated page) page the server will pass the request to web container (Tomcate server) and the Tomcat server will use <strong>Parser</strong> to read (Deployment Descriptor xml file) <strong>web.xml</strong> like this :

```
<servlet>
   <servlet-name>page</sarvlet-name>
   <sarvlet-class>Page</sarvlet-class>
</servlet>

<servlet-mapping>
   <servlet-name>page</sarvlet-name>
   <url-pattern>/page</url-pattern>
</servlet-mapping>

```
<br/>

so Tomcate will read the servlet name and define the servlet class using Parser depending on url-pattern, so the servlet class will be :

```
import java.io.*;  
import javax.servlet.*;  
  
public class Page extends GenericServlet{  
public void service(ServletRequest req,ServletResponse res)  
throws IOException,ServletException{  

// the page type is HTML
res.setContentType("text/html");  

// to write in this page we will use PrintWriter

PrintWriter out=res.getWriter();  
out.print("<html><body>");  
out.print("<b>This Page is Dynamic page</b>");  
out.print("</body></html>");  
  
}  
}  
```

<br/>

web container use ClassLoader to load servlet class and begin life-cycle :
<br/>
<img width="575" alt="Screenshot 2023-09-15 at 16 49 32" src="https://github.com/HashemGhanim/Jersey-Framework/assets/72875896/6a283d5e-d6a6-4491-8831-81c5df610ddf">
<br/>

and so on.....

## What is Jersey and what it is important ?

If you notice building a web applicaion using Servlet API will take a lot of time because for each url-pattern will make configuration on <b>web.xml</b> and if you need to build a Restful-API application it is difficult, so Jersey came on top of Servlet and make the configuration based on <b>Annotations</b> this make the build of application more easy, readable and make a development more fast.

So if we need build the previous application that already mention it, That will be so easy :<br/>
in <b>web.xml</b> there is one configuration for all class on the backage <br/>

```
<servlet>
   <servlet-name>Jersey Web Application</servlet-name>
   <servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
   <init-param>
         <param-name>jersey.config.server.provider.packages</param-name>
         <param-value>com.dome.restdome</param-value>
   </init-param>
</servlet>
<servlet-mapping>
   <servlet-name>Jersey Web Application</servlet-name>
   <url-pattern>/*</url-pattern>
</servlet-mapping>

```

using this configuration allow us to use annotaions rather than xml configuration so <b>ServletContainer</b> will read the whole annotaions in <b>com.dome.restdome</b> backage and load the servlet classes when the client request any url pattern using wild-card <b>/*</b> .... <br/>


```
@Path("page")
public class Page {

   @GET
   public String getPage(){
         return "<b>This Page is Dynamic page</b>";
   }
}

```

so that's it ... ! <br/>

Jersey is very beautiful framework make the development so easy .. <br/>

I hope you get the general idea. <br/>

<b>By : Hashem Ghanim</b>
