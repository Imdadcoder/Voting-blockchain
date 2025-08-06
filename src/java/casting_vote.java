/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sumit
 */
public class casting_vote extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String ecardnumber=request.getParameter("ecardnumber");
        System.out.println("..................."+ecardnumber);
                
        String cdate = (new java.util.Date()).toString();
          try {
                                    Class.forName("com.mysql.jdbc.Driver");
                                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/election", "root", "root");
                                    Statement statement = con.createStatement();
                                    String q = "select * from voterregister where ecardnumber='" + ecardnumber + "'";
                                    ResultSet resultSet = statement.executeQuery(q);
                                    String stdate = "", stTime = "", endTime = "", age = "", address = "", address1 = "", city = "", dist = "", state = "", pin = "", mobile = "";
                                    while (resultSet.next()) {


                                        dist = resultSet.getString("district");
                                        state = resultSet.getString("state");


                                    }

                                    String q1 = "select * from ecschd where consti='" + dist + "'";
                                    ResultSet resultSet1 = statement.executeQuery(q1);
                                    while (resultSet1.next()) {


                                        stdate = resultSet1.getString("stdate");
                                        stTime = resultSet1.getString("stTime");
                                        endTime = resultSet1.getString("endTime");


                                    }
                                    // String startTime=stTime.substring(0,2);
                                    // String enTime=endTime.substring(0,2);
                                    String day = cdate.substring(8, 10);
                                    String month = cdate.substring(4, 7);
                                    String year = cdate.substring(24, 28);
                                    String hour = cdate.substring(11, 13);
                                    String min = cdate.substring(14, 16);
                                    String sec = cdate.substring(17, 19);
                                    String curDate = day + "-" + month + "-" + year;
                                    String time = hour + "-" + min + "-" + sec;
              System.out.println("day-"+day);
              System.out.println("month-"+month);
              System.out.println("year-"+year);
              System.out.println("hour-"+hour);
              System.out.println("min-"+min);
              System.out.println("sec-"+sec);
                                    System.out.println(dist + "........" + ecardnumber + ">>>>>>>>>>>>>>>>>>>>>>>>" + stTime + ">>>>>>>>>>>>>>>>>>" + endTime);
                            System.out.println("curDate="+curDate);
                            System.out.println("stdate="+stdate);
                            System.out.println("time="+time);
                            System.out.println("stTime="+stTime);
                            int t_day=Integer.parseInt(day);
                          //  int t_month=Integer.parseInt(month);
                            int t_year=Integer.parseInt(year);
                            int t_hour=Integer.parseInt(hour);
                            int t_min=Integer.parseInt(min);
                            
                            
                            String st_time_details[]=stTime.split("-");
                            int v_st_hour=Integer.parseInt(st_time_details[0]);
                            int v_st_min=Integer.parseInt(st_time_details[1]);
                                    
                            
                                
                            String end_time_details[]=endTime.split("-");
                            int v_end_hour=Integer.parseInt(end_time_details[0]);
                            int v_end_min=Integer.parseInt(end_time_details[1]);
                                    
                            System.out.println(stdate+"===="+curDate);
                            System.out.println(t_hour+"===="+v_st_hour);
                            System.out.println(t_hour+"===="+v_end_hour);
                            if(stdate.equals(curDate)  && (t_hour>=v_st_hour&&t_hour<=v_end_hour))
                            {
                                System.out.println("during");
                                
                                String query = "select party from partyregister";
                                System.out.println(""+query);
                                String qq="";
                                     resultSet = statement.executeQuery(query);
                                while(resultSet.next())
                                {
                                String party=resultSet.getString("party");
                                qq+=party+"-";
                                }
                                System.out.println("during##"+qq);
                                out.print("during##"+qq);
                            }
                            
                            else{
                            out.print("no_election");
                            }
                            
          }catch(Exception e)
          {
          e.printStackTrace();
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
        processRequest(request, response);
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
        processRequest(request, response);
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
