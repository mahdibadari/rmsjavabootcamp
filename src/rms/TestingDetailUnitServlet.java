package rms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestingDetailUnitServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int unitid = Integer.parseInt(request.getParameter("unitid"));
		
		java.sql.Connection con = null;
		String dbURL = "jdbc:mysql://localhost/sonoo";
	    String user = "mitrais";
	    String pwd = "mitrais123";
	    PreparedStatement stmt = null;
	    
		try {
	    	Class.forName("com.mysql.jdbc.Driver");
		    con = DriverManager.getConnection(dbURL,user,pwd);
		    
		    Unit result = null;
		    
		    stmt = con.prepareStatement("SELECT unitId, name, exp, bblvl, type FROM unit WHERE unitId = ?");
		    stmt.setInt(1, unitid);
		    ResultSet rs = stmt.executeQuery();
		    
		    while(rs.next()){
		         int id  = rs.getInt("unitId");
		         int exp = rs.getInt("exp");
		         String name = rs.getString("name");
		         int bblvl = rs.getInt("bblvl");
		         String type = rs.getString("type");
		         result = new Unit(name, exp, bblvl, type);
		         result.setId(id);	
		      }		    
		      
		      
		      //HttpSession session = request.getSession(true);
		      //session.setAttribute("MySessionVariable", result);
		      //session.setAttribute("MySessionVariable", strResult);
		      
		      rs.close();
		      stmt.close();
		      
		    PreparedStatement ps3 = con.prepareStatement("SELECT healthpoint,attackpoint,defencepoint,recoverypoint,rarity FROM Status Where unitid = ?");
		    ps3.setInt(1, unitid);
				 ResultSet rs3 = ps3.executeQuery();
				 while(rs3.next()) {
				 	 int hp = rs3.getInt("healthpoint");
					 int ap = rs3.getInt("attackpoint");
					 int dp = rs3.getInt("defencepoint");
					 int rp = rs3.getInt("recoverypoint");
					 int rarity = rs3.getInt("rarity");
					 Status stat = new Status(hp, ap, dp, rp, unitid, rarity);
					 result.setStatus(stat);
				 }
				 rs3.close();	
				 ps3.close();
				 

			      PreparedStatement ps2 = con.prepareStatement("SELECT name,healthmodifier,attackmodifier,defencemodifier,recoverymodifier FROM Type WHERE name = ?");
			      ps2.setString(1, result.getTypeName());
				  ResultSet rs2 = ps2.executeQuery();
				  
				  while(rs2.next()) {
					  String name = rs2.getString("name");
					  int hm = rs2.getInt("healthmodifier");
					  int am = rs2.getInt("attackmodifier");
					  int dm = rs2.getInt("defencemodifier");
					  int rm = rs2.getInt("recoverymodifier");
					  Type type = new Type(name, hm, am, dm, rm);
					  result.setType(type);					  
				  }			  
				  rs2.close();
				  ps2.close();
				 
				 
		         con.close();
		         
		         HttpSession session = request.getSession(true);
		         session.setAttribute("unit", result);
		         
		    //request.setAttribute("products", result);
	        //request.getRequestDispatcher("/WEB-INF/products.jsp").forward(request, response);
		    request.setAttribute("unit", result);   
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/detail.jsp");
			PrintWriter out= response.getWriter();
			//out.println("<font color=green>Registration successful, please login below.</font>");
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
