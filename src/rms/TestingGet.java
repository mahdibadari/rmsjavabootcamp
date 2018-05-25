package rms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/products")
public class TestingGet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8369639103197498174L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		java.sql.Connection con = null;
		String dbURL = "jdbc:mysql://localhost/sonoo";
	    String user = "mitrais";
	    String pwd = "mitrais123";
	    Statement stmt = null;
	    
		try {
	    	Class.forName("com.mysql.jdbc.Driver");
		    con = DriverManager.getConnection(dbURL,user,pwd);
		    
		    stmt = con.createStatement();
		    String sql = "SELECT unitId, name, exp, bblvl, type FROM unit" ;
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    List<Unit> result = new ArrayList<Unit>();
		    List<String> strResult = new ArrayList<String>();
		    
		    while(rs.next()){
		         int id  = rs.getInt("unitId");
		         int exp = rs.getInt("exp");
		         String name = rs.getString("name");
		         int bblvl = rs.getInt("bblvl");
		         String type = rs.getString("type");
		         Unit unit = new Unit(name, exp, bblvl, type);
		         unit.setId(id);
		         		 
		         result.add(unit);
		         strResult.add(name);
		      }		    		    
		      
		      //HttpSession session = request.getSession(true);
		      //session.setAttribute("MySessionVariable", result);
		      //session.setAttribute("MySessionVariable", strResult);
		      
		      rs.close();
		      stmt.close();
		         con.close();
		         
		    request.setAttribute("products", result);     
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/products.jsp");
			PrintWriter out= response.getWriter();
			rd.include(request, response);
		} catch(SQLException se) {
	         se.printStackTrace();
	      } catch(Exception e) {
	         e.printStackTrace();
	      } finally {
	    	  try {
	              if(stmt!=null)
	                 stmt.close();
	         } catch(SQLException se2) {
	         } // nothing we can do
	         try {
	            if(con!=null)
	            con.close();
	         } catch(SQLException se) {
	            se.printStackTrace();
	         } //end finally try
	      } //end try
    }
	
}
