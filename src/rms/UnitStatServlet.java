package rms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Poststat", urlPatterns = { "/Poststat" })
public class UnitStatServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2172669733622865317L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int ap = Integer.parseInt(request.getParameter("ap"));
		int dp = Integer.parseInt(request.getParameter("dp"));
		int hp = Integer.parseInt(request.getParameter("hp"));
		int rp = Integer.parseInt(request.getParameter("rp"));
		int unitId = Integer.parseInt(request.getParameter("unitId"));
		int rarity = Integer.parseInt(request.getParameter("rarity"));
		
		String errorMsg = null;
		
		if(ap < 1){
			errorMsg = "Attack point cannot less than 1";
		}
		if(dp < 1){
			errorMsg = "Defence point cannot less than 1";
		}
		if(hp < 1){
			errorMsg = "Health point cannot less than 1";
		}
		if(rp < 1){
			errorMsg = "Recovery point cannot less than 1";
		}
		if(unitId < 1){
			errorMsg = "Cannot retrieve unit Id";
		}		
		if(rarity < 1){
			errorMsg = "Cannot retrieve unit rarity";
		}		
		
		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>"+errorMsg+"</font>");
			rd.include(request, response);
		}else{
		
		//Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		//PreparedStatement ps = null;
			java.sql.Connection con = null;
			String dbURL = "jdbc:mysql://localhost/sonoo";
	    	String user = "mitrais";
	    	String pwd = "mitrais123";
		PreparedStatement ps;
		try {
	    	Class.forName("com.mysql.jdbc.Driver");
		    con = DriverManager.getConnection(dbURL,user,pwd);
			ps = con.prepareStatement("INSERT INTO Status (healthpoint,attackpoint,defencepoint,recoverypoint,unitid,rarity) VALUES (?,?,?,?,?,?)");
			ps.setInt(1, hp);
			ps.setInt(2, ap);
			ps.setInt(3, dp);
			ps.setInt(4, rp);
			ps.setInt(5, unitId);
			ps.setInt(6, rarity);
			ps.execute();
			
			//logger.info("User registered with email="+email);
			
			//forward to login page to login
			ps.close();
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=green>Registration successful, please login below.</font>");
			rd.include(request, response);
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
}
