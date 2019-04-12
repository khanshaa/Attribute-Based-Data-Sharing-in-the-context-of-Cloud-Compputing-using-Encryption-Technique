package pack1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconnection.DbConnection;

/**
 * Servlet implementation class SendKeyRequest
 */
@WebServlet("/SendKeyRequest")
public class SendKeyRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendKeyRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		int uid=(Integer)session.getAttribute("userid");
		String useremail=(String)session.getAttribute("uemail");
		int fileid=Integer.parseInt(request.getParameter("fileid"));
		try{
		DateFormat format1=new SimpleDateFormat("dd-MM-yyyy");
		Calendar now=Calendar.getInstance();
		String dateTime=format1.format(now.getTime());
		Connection cn=DbConnection.getConnection();
		PreparedStatement stmt=cn.prepareStatement("INSERT INTO `tpa`.`adminkeyreq` (`reqid`, `userid`,`useremail` ,`fileid`, `date`,`flagadmin`, `flagtpa`) "
				+ "VALUES (NULL, '"+uid+"','"+useremail+"', '"+fileid+"', '"+dateTime+"', '"+0+"', '"+0+"')");
		stmt.executeUpdate();
		}catch(Exception e)
		{
			
		}
		//------------------------------------------------------------------

   /*RequestDispatcher rd=request.getRequestDispatcher("showfile.jsp?sendrequest=yes");
   rd.forward(request,response);*/
   response.sendRedirect("showfile.jsp?sendrequest=yes");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
