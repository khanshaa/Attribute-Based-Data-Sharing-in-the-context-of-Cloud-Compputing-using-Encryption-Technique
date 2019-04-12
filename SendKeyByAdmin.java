package pack1;

import java.io.IOException;
import java.sql.*;

import dbconnection.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendKeyByAdmin
 */
@WebServlet("/SendKeyByAdmin")
public class SendKeyByAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendKeyByAdmin() {
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
		String reqid=request.getParameter("reqid");
		String fileid=request.getParameter("fileid");
		String useremail=request.getParameter("useremail");
		//-----------------------------------------------------
		Connection cn=DbConnection.getConnection();
		try{
		Statement st=cn.createStatement();
		Statement stu=cn.createStatement();
		ResultSet rsu=stu.executeQuery("select * from userfile where fileid="+fileid);
		//ResultSet rs=st.executeQuery("select * from userfile where uid="+id);
		String adminkey="";
		if(rsu.next())
		{
			adminkey=rsu.getString("adminkey");
			
		}
		
		String msg="Your Half Key From Admin : "+adminkey;
		Mailer.EmailSending(useremail,"Admin Key Response", msg);
		//------------------------------------------------------
		Statement stupdate=cn.createStatement();
		stupdate.executeUpdate("UPDATE `adminkeyreq` SET `flagadmin`='1' WHERE `reqid`='"+reqid+"'");
		/*RequestDispatcher rd=request.getRequestDispatcher("adminkeyrequest.jsp?send=yes");
		   rd.forward(request,response);*/
		   
		   response.sendRedirect("adminkeyrequest.jsp?send=yes");
		
		
		
		
		}catch(Exception e)
		{
			System.out.println("in exception");
		}
		
	}

}
