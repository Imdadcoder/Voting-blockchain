/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Logic.ip_details;
import Logic.path_info;
import databaseconnection.DBQuery;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fgfdg
 */
public class get_result extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
        try {
 
                 
                 
                 
             
              File file1 = new File(path_info.pypath+"task.txt"); 
                    FileOutputStream fout1=new FileOutputStream(file1);
                    fout1.write("download".getBytes());
                    fout1.close();
             
             try {
                     Thread.sleep(5000);
                 } catch (InterruptedException ex) {
                     Logger.getLogger(get_result.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 
                 String st="",data=""; 
            File file = new File(path_info.pypath+"results.txt"); 
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            
            
                   
                   while ((st = br.readLine()) != null) 
                   {
                   System.out.println(st);
                   data+=st+"\n";
                  
                   }
                   if (data.startsWith("%%@@")) {
                       data=data.substring(4,data.length());
                
                    }
                   String arr[]=data.split("%%@@");
                   DBQuery db=new DBQuery();
                   int pi[]=null;
                   String p[]=null;
                    try {
                        String det=db.get_party_list();
                        p=det.split("-");
                         pi=new int[p.length];
                        for(int i=0;i<pi.length;i++)
                        {
                        
                        pi[i]=0;
                            System.out.println(p[i]+"===="+pi[i]);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(get_result.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for(int i=0;i<arr.length;i++)
                    {
                        int index=-1;
                    String a[]=arr[i].split("@");
                        System.out.println("blockchain party "+a[1]);
                    for(int k=0;k<p.length;k++)
                    {
                        
                        System.out.println(k+"    checking "+p[k]+" with "+a[1].trim());
                        if (p[k].equals(a[1].trim())) {
                            System.out.println("matching "+k+"    "+p[k]);
                            index=k;
                            break;
                        }
                    }
                    int temp=pi[index];
                    temp++;
                     pi[index]=temp;   
                        System.out.println("in p at "+index+" value "+pi[index] );
                    }
                    String det="";
                    for(int k=0;k<p.length;k++)
                    {
                        det+=p[k]+"\t"+pi[k]+"%%@@";
                    }
                   System.out.println("sending res "+det);
                  out.print(det);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
