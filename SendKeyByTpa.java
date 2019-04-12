package pack1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbconnection.DbConnection;

/**
 * Servlet implementation class SendKeyByTpa
 */
@WebServlet("/SendKeyByTpa")
public class SendKeyByTpa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendKeyByTpa() {
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
		String tpakey="";
		if(rsu.next())
		{
			tpakey=rsu.getString("tpakey");
			
		}
		
		String msg="Your Half Key From TPA : "+tpakey;
		Mailer.EmailSending(useremail,"TPA Key Response", msg);
		//------------------------------------------------------
		Statement stupdate=cn.createStatement();
		stupdate.executeUpdate("UPDATE `adminkeyreq` SET `flagtpa`='1' WHERE `reqid`='"+reqid+"'");
		/*RequestDispatcher rd=request.getRequestDispatcher("tpakeyrequest.jsp?send=yes");
		rd.forward(request,response);*/
		
		response.sendRedirect("tpakeyrequest.jsp?send=yes");
		
		
		
		}catch(Exception e)
		{
			System.out.println("in exception");
		}
		
	}

}
