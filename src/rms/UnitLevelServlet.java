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
import javax.swing.JOptionPane;

@WebServlet(name = "Postlevel", urlPatterns = { "/Postlevel" })
public class UnitLevelServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8933244214503619685L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errorMsg = null;
		
		String name = request.getParameter("name");
		String tipe = request.getParameter("tipe");
		int exp = Integer.parseInt(request.getParameter("exp"));
		int bblvl = Integer.parseInt(request.getParameter("bblvl"));
		
		//String errorMsg = null;
		if(exp < 1){
			errorMsg = "Experience cannot less than 1";
		}
		if(bblvl < 1){
			errorMsg = "Default BraveBurst level cannot be less than 1";
		}
		if(name == null || name.equals("")){
			errorMsg = "Name can't be null or empty.";
		}
		if(tipe == null || tipe.equals("")){
			errorMsg = "Type can't be null or empty.";
		}
		
		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>"+errorMsg+tipe+"</font>");
			rd.include(request, response);
		}else{		
			
			java.sql.Connection con = null;
			String dbURL = "jdbc:mysql://localhost/sonoo";
	    	String user = "mitrais";
	    	String pwd = "mitrais123";
		PreparedStatement ps;
		try {
	    	Class.forName("com.mysql.jdbc.Driver");
		    con = DriverManager.getConnection(dbURL,user,pwd);
			ps = con.prepareStatement("INSERT INTO Unit(name,exp,bblvl,type) VALUES (?,?,?,?)");
			ps.setString(1, name);
			ps.setInt(2, exp);
			ps.setInt(3, bblvl);
			ps.setString(4, tipe);
			ps.execute();
			
			//logger.info("User registered with email="+email);
			
			//forward to login page to login
			ps.close();
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/welcome.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=green>New unit successfully inputted</font><br/>");
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
