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

@WebServlet(name = "Posttype", urlPatterns = { "/Posttype" })
public class UnitTypeServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1123377734627337935L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		int hm = Integer.parseInt(request.getParameter("hm"));
		int am = Integer.parseInt(request.getParameter("am"));
		int dm = Integer.parseInt(request.getParameter("dm"));
		int rm = Integer.parseInt(request.getParameter("rm"));
		
		String errorMsg = null;
		if(name == null || name.equals("")){
			errorMsg = "Email ID can't be null or empty.";
		}
		if(hm < 1){
			errorMsg = "Cannot retrieve unit Id";
		}	
		if(am < 1){
			errorMsg = "Cannot retrieve unit Id";
		}	
		if(dm < 1){
			errorMsg = "Cannot retrieve unit Id";
		}	
		if(rm < 1){
			errorMsg = "Cannot retrieve unit Id";
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
			ps = con.prepareStatement("INSERT INTO Type (name,healthmodifier,attackmodifier,defencemodifier,recoverymodifier) VALUES (?,?,?,?,?)");
			ps.setString(1, name);
			ps.setInt(2, hm);
			ps.setInt(3, am);
			ps.setInt(4, dm);
			ps.setInt(5, rm);
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
