package pack1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconnection.DbConnection;

/**
 * Servlet implementation class TPALoginAction
 */
@WebServlet("/TPALoginAction")
public class TPALoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TPALoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("uname");
		String pass=request.getParameter("pass");
		Connection cn=DbConnection.getConnection();
		HttpSession session=request.getSession();
		Statement st = null;
		ResultSet rs=null;
		PrintWriter out=response.getWriter();
		int f=0;
		try {
			st = cn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs=st.executeQuery("select * from tpa");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next())
			{
				if(name.equalsIgnoreCase(rs.getString(2)) && pass.equalsIgnoreCase(rs.getString(3)))
				{
				
					f=1;
					break;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		
		
		
		}
		if(f==0)
		{
			RequestDispatcher rd=request.getRequestDispatcher("tpalogin.jsp?n=N");
	    	rd.include(request, response);
	    	
		}
		
		if(f==1)
		{
			/*RequestDispatcher rd=request.getRequestDispatcher("tpakeyrequest.jsp");
	    	rd.include(request, response);	*/	
	    	
	    	response.sendRedirect("tpakeyrequest.jsp");
				
			
		}

		
	}

}
