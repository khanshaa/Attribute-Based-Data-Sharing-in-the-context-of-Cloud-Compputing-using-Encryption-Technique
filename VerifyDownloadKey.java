package pack1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
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
 * Servlet implementation class VerifyDownloadKey
 */
@WebServlet("/VerifyDownloadKey")
public class VerifyDownloadKey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyDownloadKey() {
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
		String adminkey=request.getParameter("adminkey");
		String tpakey=request.getParameter("tpakey");
		HttpSession session=request.getSession();
		String fileid=(String)session.getAttribute("downloadfileid");
		System.out.println(adminkey+" "+" "+tpakey+" "+fileid);
		//-----------------------------------------------------
		Connection cn=DbConnection.getConnection();
		try{
		
		Statement stu=cn.createStatement();
		ResultSet rsu=stu.executeQuery("select * from userfile where fileid='"+fileid+"' and adminkey='"+adminkey+"' and tpakey='"+tpakey+"'");
		//ResultSet rs=st.executeQuery("select * from userfile where uid="+id);
		String filename="";
		
		if(rsu.next())
		{
			//---------------------------------------------
			filename=rsu.getString("ufile");
			String data=new String();
		    String line;
		    String decrypt_path = "D:\\Attribute Based\\workspace\\AttributeBased\\WebContent\\decrypt\\";
		    String filepath1 = "D:\\Attribute Based\\workspace\\AttributeBased\\WebContent\\uploadFile\\";
		    BufferedReader br11 = new BufferedReader(new FileReader(filepath1+filename));
		    while ((line = br11.readLine()) != null) {
		        data=data+line;
		    }
		    System.out.println(data);
		    String decrypt=null;
			
				System.out.println("in decrypt try");
				decrypt = AESAlgorithm.decrypt(data,"XMzDdG4D03CKm2IxIWQw7g==");
				decrypt=decrypt.replaceFirst("null","");
				FileWriter fw1=new FileWriter(decrypt_path+filename);  
				fw1.write(decrypt);  
				fw1.close();
				
			System.out.println(decrypt);
			//---------------------------------------------
			
			response.setContentType("text/html");  
			PrintWriter out = response.getWriter();  
			
			//String filepath = "/opt/tomcat/webapps/ROOT/tempfile/"; 
			String filepath=decrypt_path;
			response.setContentType("APPLICATION/OCTET-STREAM");   
			response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
			  
			FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
			            
			int i;   
			while ((i=fileInputStream.read()) != -1) {  
			out.write(i);   
			}   
			fileInputStream.close();   
			out.close();  
			response.sendRedirect("showfile.jsp");
			return;
			
		}
		else
		{
			response.sendRedirect("showfile.jsp?wrongkey=yes");
			return;
		}
		
		
		
		
		
		
		}catch(Exception e)
		{
			System.out.println("in exception"+e);
		}
	}

}
