package baicai;

import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import java.sql.*;

//"/login.jsp" is the URL where request form
@WebServlet(name="LoginServlet", urlPatterns={"/login"} )
//LoginServlet's name is "login"
public class LoginServlet extends HttpServlet //At first i forgot to extends HttpServlet
{
	//To response client's request
	public void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, java.io.IOException
	{
		String errMsg = "";
		//since Servlet don't output the response to client,so to transfer it
		RequestDispatcher rd;
		//acquire request's param
		String username = request.getParameter("username");
		String pass = request.getParameter("pass");
		try
		{
			//Use javaBean to deal user's request
			DbDao dd = new DbDao("com.jdbc.mysql.Driver", 
					"jdbc:mysql://localhost:3306/test", "root", "12345");
			ResultSet rs = dd.query("select pass from table1" + 
					"where name = ?", username );
			
			if( rs.next())
			{
				if( rs.getString("pass").equals(pass))
				{
					
					HttpSession session = request.getSession(true);//get session
					session.setAttribute("name", username);//set session's param to trace user's session state
					rd = request.getRequestDispatcher("/welcom.jsp");//get the forward target
					rd.forward(request, response);//retransmit request
				}
				else
				{
					errMsg += "Your username doesn't match! Please input again!";
				}
			}
			else
			{
				errMsg += "Your username doesn't exit! Please register first!";
			}
		}
		catch( Exception e)
		{
			e.printStackTrace();
		}
		
		if( errMsg != null && !errMsg.equals(""))
		{
			rd = request.getRequestDispatcher("/login.jsp");
			request.setAttribute("err", errMsg);
			rd.forward(request, response);
		}
		
		
	}

}
