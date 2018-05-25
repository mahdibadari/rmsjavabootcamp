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

@WebServlet(name = "Updatelevel", urlPatterns = { "/Updatelevel" })
public class UpdateLevelServlet extends HttpServlet{
/**
	 * 
	 */
	private static final long serialVersionUID = -8837852802048851288L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int exp = Integer.parseInt(request.getParameter("exp"));
		int bblvl = Integer.parseInt(request.getParameter("bblvl"));
		
		String errorMsg = null;
		if(id < 1){
			errorMsg = "Experience cannot less than 1";
		}
		if(exp < 1){
			errorMsg = "Experience cannot less than 1";
		}
		if(bblvl < 1){
			errorMsg = "Default BraveBurst level cannot be less than 1";
		}
		if(name == null || name.equals("")){
			errorMsg = "Name can't be null or empty.";
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
			
			ps = con.prepareStatement("UPDATE Unit SET name = ?, exp = ?, bblvl = ? WHERE unitId = ?");

			ps.setString(1, name);
			ps.setInt(2, exp);
			ps.setInt(3, bblvl);
			ps.setInt(4, id);
			
			ps.executeUpdate();
			
			//logger.info("User registered with email="+email);
			
			//forward to login page to login
			ps.close();
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/navigation.jsp");
			PrintWriter out= response.getWriter();
			out.println("<font color=green>Update Successful</font><br/>");
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
