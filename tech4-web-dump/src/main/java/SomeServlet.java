import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SomeServlet extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
      
    // Use "request" to read incoming HTTP headers (e.g. cookies)
    // and HTML form data (e.g. data the user entered and submitted)
    
    // Use "response" to specify the HTTP response line and headers
    // (e.g. specifying the content type, setting cookies).
    
    PrintWriter out = response.getWriter();
    out.append("<font size=\"77\">tech4 demo version 0.0</font>");
      out.flush();
  }
}