/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import databaseconnection.DBQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(name = "loginCheck", urlPatterns = {"/loginCheck"})
public class loginCheck extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        RequestDispatcher view=null;
        String txtusr="",txtpwd="";
	txtusr=request.getParameter("txtusr");
	txtpwd=request.getParameter("txtpwd");
        DBQuery db=new DBQuery();
        int i=db.loginCheck(txtusr, txtpwd);
        if(i==0){
      session.setAttribute("msg", "User ID and Password are not matching");
        view=request.getRequestDispatcher("login.jsp");
        view.forward(request,response);
        }
        else if(i==1){
        view=request.getRequestDispatcher("ECHome.jsp");
        view.forward(request,response);
      //  MainForm mf=new MainForm();
       // EnrollmentForm form = new EnrollmentForm(mf);
		//form.setVisible(true);
        }
        else if(i==2){
      session.setAttribute("msg", "Please Login from Mobile app");
        view=request.getRequestDispatcher("login.jsp");
        view.forward(request,response);
//       session.setAttribute("ecard", txtusr);
//        view=request.getRequestDispatcher("userHome.jsp");
//        view.forward(request,response);
        }
        
        
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(loginCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(loginCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
