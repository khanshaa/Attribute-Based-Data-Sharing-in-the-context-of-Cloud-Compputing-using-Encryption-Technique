package pack1;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.sql.*;

import dbconnection.DbConnection;

/**
 * Servlet implementation class MessageAction
 */
@WebServlet("/MessageAction")
//@WebServlet("/uploadfile")
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFile() {
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
		Connection cn=DbConnection.getConnection();
		HttpSession session=request.getSession();
		int userid=(Integer)session.getAttribute("userid");
		//---------------------------------------------------------------------
		if(ServletFileUpload.isMultipartContent(request)){
	           try {
	               List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	               String FileName = "";
	               String FileExtention = "";
	               long FileSize = 0;
	               
	              
	                  
	               
	            	   String UPLOAD_DIRECTORY1 ="D:\\Attribute Based\\workspace\\AttributeBased\\WebContent\\tempfile\\";
	            	   
	            	   String rp=null;
	            	   for(FileItem item : multiparts)
	            	   {
		            	   
		                   if(!item.isFormField())
		                   {
		                	   String name = new File(item.getName()).getName();
		                	   
		                       item.write( new File(UPLOAD_DIRECTORY1 + File.separator + name));
		                       
		                       FileName = item.getName();
		                       FileExtention = item.getContentType();
		                       FileName = item.getName();
		                       FileSize = item.getSize();
		                       
		                       
		                    }
		                              
		                   
		                }
	            	   String first="no";
	            	   String second="no";
	            	   String third="no";
	            	   String fourth="no";
	            	   String fifth="no";
	            	   String sixth="no";
	            	   for(FileItem item : multiparts){
		            	   if (item.getFieldName().equals("first")) {
		                   	    first  = item.getString();
		                   	    if(first.equalsIgnoreCase("null"))
		                   	    {
		                   	    	first="no";
		                   	    }
		                   	 System.out.println(first);
		                   	         }
		                   if (item.getFieldName().equals("second")) {
		                	   second  = item.getString();
		                	   if(second.equalsIgnoreCase("null"))
		                   	    {
		                		   second="no";
		                   	    }
		                   	 System.out.println(second);
		                   	         }
		                   if (item.getFieldName().equals("third")) {
		                	   third  = item.getString();
		                	   if(third.equalsIgnoreCase("null"))
		                   	    {
		                		   third="no";
		                   	    }
		                   	 System.out.println(third);
		                   	         }
		                   if (item.getFieldName().equals("fourth")) {
		                	   fourth  = item.getString();
		                	   if(fourth.equalsIgnoreCase("null"))
		                   	    {
		                		   fourth="no";
		                   	    }
		                   	 //System.out.println(firstl);
		                   	         }
		                   if (item.getFieldName().equals("fifth")) {
		                	   fifth  = item.getString();
		                	   if(fifth.equalsIgnoreCase("null"))
		                   	    {
		                		   fifth="no";
		                   	    }
		                   	 //System.out.println(secondl);
		                   	         }
		                   if (item.getFieldName().equals("sixth")) {
		                	   sixth  = item.getString();
		                	   if(sixth.equalsIgnoreCase("null"))
		                   	    {
		                		   sixth="no";
		                   	    }
		                   	 //System.out.println(secondl);
		                   	         }
		        
		                       }
	            	   
	            	   //------------------------upload encrypted file---------------------------------------------------------------
	            	   String UPLOAD_DIRECTORY_MAIN ="D:\\Attribute Based\\workspace\\AttributeBased\\WebContent\\uploadFile\\";
	            	   String data=null;
	                   String line;
	                   BufferedReader br11 = new BufferedReader(new FileReader(UPLOAD_DIRECTORY1+FileName));
	                   while ((line = br11.readLine()) != null) {
	                       data=data+line;
	                   }
	          			String enc;
	          			String digest1=null;
	        			
	        				enc = AESAlgorithm.encrypt(data,"XMzDdG4D03CKm2IxIWQw7g==");
	        				FileWriter fw1=new FileWriter(UPLOAD_DIRECTORY_MAIN+FileName);  
	        				fw1.write(enc);  
	        				fw1.close();
	            	   //---------------------------------get key of file-------------------------------------------------
	        				File fileForKeyGeneration = new File(UPLOAD_DIRECTORY_MAIN+FileName);
	        				String generated_key=SHA_512.getSH1(fileForKeyGeneration);
	        				System.out.println("imp=============="+generated_key);
	        				int length=generated_key.length();
	        				int break_key_index=length/2;
	        				String adminkey=generated_key.substring(0,break_key_index);
	        				String tpakey=generated_key.substring(break_key_index,length);
	        				
	        				//-----------------------------------------------------------------
	        				PreparedStatement stmt=cn.prepareStatement("INSERT INTO `tpa`.`userfile` (`fileid`, `userid`, `ufile`, `adminkey`,`tpakey`, `Manager`, `System Administrator`, `Office Assistant`, `Account Department`, `Marketing Department`, `Team Leader`) "
	        						+ "VALUES (NULL, '"+userid+"', '"+FileName+"', '"+adminkey+"', '"+tpakey+"', '"+first+"', '"+second+"', '"+third+"' , '"+fourth+"' , '"+fifth+"' , '"+sixth+"')");
	        				stmt.executeUpdate();
	        				//------------------------------------------------------------------

	            	   RequestDispatcher rd=request.getRequestDispatcher("userhome.jsp?upload=yes");
	            	   rd.forward(request,response);
	            	   
	            	   
	            	   
	            	   	               } catch (Exception ex) {
	            	   	            	   System.out.println(ex);
		              request.setAttribute("message", "File Upload Failed due to " + ex);
		           }  
	           		
	            	   
	            	   	 

	}


}
}