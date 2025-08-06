import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;
public class EC extends HttpServlet
{

	public void init(ServletConfig config)
	{
		
	}//init
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw=null;
		try
		{
			RequestDispatcher view=null;
			res.setContentType("text/html");
			pw=res.getWriter();
			String txtusr="",txtpwd="";
			txtusr=req.getParameter("txtusr");
			txtpwd=req.getParameter("txtpwd");
			if(txtusr.equals("EC") && txtpwd.equals("EC"))
			view=req.getRequestDispatcher("ECHome.jsp"); //electionschedule.jsp");
			else
			view=req.getRequestDispatcher("es.html");
			view.forward(req,res);
		}
		catch(Exception e)
		{pw.println(e);}
	}
}