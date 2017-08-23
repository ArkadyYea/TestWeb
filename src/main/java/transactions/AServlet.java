package transactions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Transaction Status codes http://docs.oracle.com/javaee/6/api/constant-values.html#javax.transaction.Status.STATUS_ACTIVE
@WebServlet("/TransCheckServ")
public class AServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	TransactionBean tb;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter pw = response.getWriter();
		pw.println("<h2>Hello World from Servlet!</h2>");
		
		pw.println("<br/>Transaction required: "+TransactionBean.getTransactionStatus(tb.req()));
		pw.println("<br/>Transaction never: "+TransactionBean.getTransactionStatus(tb.never()));
		pw.println("<br/>Transaction no Annotation: "+TransactionBean.getTransactionStatus(tb.noAnnotation()));
		pw.println("<br/>Transaction rolled back: "+TransactionBean.getTransactionStatus(tb.rolledBack()));
		
		pw.close();
		
	}

}
