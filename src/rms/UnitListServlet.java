package rms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Getlevel", urlPatterns = { "/Getlevel" })
public class UnitListServlet extends HttpServlet{
/**
	 * 
	 */
	private static final long serialVersionUID = -4914685338821087744L;
	
	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		java.sql.Connection con = null;
		String dbURL = "jdbc:mysql://localhost/sonoo";
	    String user = "mitrais";
	    String pwd = "mitrais123";
	    	
		PreparedStatement ps = null;
		try {
	    	Class.forName("com.mysql.jdbc.Driver");
		    con = DriverManager.getConnection(dbURL,user,pwd);
		    
		    ps = con.prepareStatement("SELECT id, name, exp, bblvl, type FROM Unit");
		    ResultSet rs = ps.executeQuery();
		    
		    List<Unit> result = new ArrayList<Unit>();
		    
		    while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("id");
		         int exp = rs.getInt("exp");
		         String name = rs.getString("name");
		         int bblvl = rs.getInt("bblvl");
		         String type = rs.getString("type");
		         Unit unit = new Unit(name, exp, bblvl, type);
		         unit.setId(id);
		         
		         PreparedStatement ps3 = con.prepareStatement("SELECT healthpoint,attackpoint,defencepoint,recoverypoint,rarity FROM Status Where unitid = ?");
				 ResultSet rs3 = ps3.executeQuery();
				 while(rs3.next()) {
					 int hp = rs3.getInt("healthpoint");
					 int ap = rs3.getInt("attackpoint");
					 int dp = rs3.getInt("defencepoint");
					 int rp = rs3.getInt("recoverymodifier");
					 int rarity = rs3.getInt("rarity");
					 Status stat = new Status(hp, ap, dp, rp, id, rarity);
					 unit.setStatus(stat);
				 }
				 rs3.close();
				 
		         result.add(unit);
		      }
		    
		      rs.close();
		      
		      PreparedStatement ps2 = con.prepareStatement("SELECT name,healthmodifier,attackmodifier,defencemodifier,recoverymodifier FROM Type");
			  ResultSet rs2 = ps2.executeQuery();
			  
			  while(rs2.next()) {
				  String name = rs2.getString("name");
				  int hm = rs2.getInt("healthmodifier");
				  int am = rs2.getInt("attackmodifier");
				  int dm = rs2.getInt("defencemodifier");
				  int rm = rs2.getInt("recoverymodifier");
				  Type type = new Type(name, hm, am, dm, rm);
				  for(Unit unit:result){
					  if (unit.getTypeName() == name) {
						  unit.setType(type);
					  }
					  continue;
				  }
			  }			  
			  rs2.close();
		      
		      HttpSession session = request.getSession(true);
		      session.setAttribute("MySessionVariable", result);
		      
			ps.close();
			//RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			//PrintWriter out= response.getWriter();
			//out.println("<font color=green>Registration successful, please login below.</font>");
			//rd.include(request, response);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			//logger.error("Database connection problem");
			throw new ServletException("DB Connection problem.");
		}finally{
			//try {
				//ps.close();
			//} catch (SQLException e) {
				//logger.error("SQLException in closing PreparedStatement");
			//}
		}
		
		
	}
}
