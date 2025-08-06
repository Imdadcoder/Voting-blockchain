/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author User
 */
public class DBQuery {
    databasecon db=null;
    public DBQuery(){

  db=new databasecon();
}
    
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    
     public String login(String user, String Password) throws SQLException, ClassNotFoundException
     {
          String Str = "";
           String name="";
        
           String qry = "select * from login where ecard = '"+user+"'and password = '"+Password+"'";
           System.out.println(qry);
           Connection con = db.getconnection();
           Statement stmt = con.createStatement(); 
           ResultSet res = stmt.executeQuery(qry);
          
        while(res.next())
        {
            name = res.getString("utype");
        }
          res.close();
          return name;
     }
       public String get_result_status() throws SQLException
{
String flag="";
 con=db.getconnection();
 st=con.createStatement();
String q="select * from result ";
rs=st.executeQuery(q);
while(rs.next())
{
flag=rs.getString("flag");
}
return flag;
}
      public String get_result() throws SQLException
{
String party="",count="";
 con=db.getconnection();
 st=con.createStatement();
String q="select * from e_result ";
rs=st.executeQuery(q);
while(rs.next())
{
party=rs.getString("party");
count=rs.getString("vcount");
}
return party+"-"+count;
}   
    
    public String get_mob(String voterid) throws SQLException
{
String mob="";
 con=db.getconnection();
 st=con.createStatement();
String q="select * from voterregister where ecardnumber='"+voterid+"'";
rs=st.executeQuery(q);
while(rs.next())
{
mob=rs.getString("mobile");
}
return mob;
}
    public int add_otp(String user,String otp) throws SQLException
{
int i=0;

con=db.getconnection();
 st=con.createStatement();
 String q1="delete from otp_details where ecardnumber='"+user+"'";
 st.executeUpdate(q1);
 String q="insert into otp_details values ('"+user+"', '"+otp+"')";
i=st.executeUpdate(q);

return i;
}
    public int loginCheck(String e,String p) throws SQLException{
    int i=0;
    con=db.getconnection();
    String DBEcard="",DBPass="",utype="";
    String q="select * from login where ecard='"+e+"' and password='"+p+"'";
    st=con.createStatement();
    rs=st.executeQuery(q);
    while(rs.next())
    {
    DBEcard=rs.getString("ecard");
    DBPass=rs.getString("password");
    utype=rs.getString("utype");
    
    }
    if(e.equals(DBEcard)&&p.equals(DBPass)){
    
    if(utype.equals("admin")){
    i=1;
    
    }
    else if(utype.equals("user")){
    i=2;
    
    }
    }
    else{
    
    i=0;
    }
    
    
    
    return i;
    }
    
    public int verify_otp(String user,String otp) throws SQLException
{
int i=0;

con=db.getconnection();
 st=con.createStatement();

 String q="select * from otp_details where ecardnumber ='"+user+"' and otp= '"+otp+"'";
rs=st.executeQuery(q);
if(rs.next())
{
i=1;
}
return i;
}
    public int loginCheck1(String e,String ecard) throws SQLException{
    int i=0;
    con=db.getconnection();
    String DBEcard="",DBPass="",utype="";
    String q="select * from voterregister where voterid='"+e+"' and ecardnumber='"+ecard+"'";
    st=con.createStatement();
    rs=st.executeQuery(q);
    while(rs.next())
    {
    DBEcard=rs.getString("voterid");
   // DBPass=rs.getString("password");
    
    }
    if(e.equals(DBEcard)){
    
    i=1;
    
   
    }
     return i;
    }
    public String get_party_list() throws SQLException{
   
      con=db.getconnection();
     st=con.createStatement();
    String query = "select party from partyregister";
                                System.out.println(""+query);
                                String qq="";
                                     rs = st.executeQuery(query);
                                while(rs.next())
                                {
                                String party=rs.getString("party");
                                qq+=party+"-";
                                }
    
    return qq;
    }
    
    public int check_cast_vote(String user) throws SQLException
    {
     int i=0;
     con=db.getconnection();
     st=con.createStatement();
    String q="select status from check_cast_vote where ecard='"+user+"'";
        System.out.println(q);
    rs=st.executeQuery(q);
    if(rs.next())
    {
    i=rs.getInt(1);
    }
     return i;
    }
    
    public int get_voting_count(String party,String user) throws SQLException{
    int i=0;
     con=db.getconnection();
     st=con.createStatement();
     int j=check_cast_vote(user);
     if(j==0)
     {
     String q="select count1 from votingcounter where party='"+party+"'";
        System.out.println(""+q);
    rs=st.executeQuery(q);
    if(rs.next())
    {
    
    i=rs.getInt(1);
    }
     if(i==0)
    {
    String q2="insert into votingcounter values('"+party+"','"+i+"')";
    st.executeUpdate(q2);
    }
    i++;
   
    String q1="update votingcounter set count1='"+i+"' where party='"+party+"'";
        System.out.println(""+q1);
    st.executeUpdate(q1);
    int k=1;
      String q3="insert into check_cast_vote values('"+user+"','"+k+"')";
      st.executeUpdate(q3);
     }
     else{
     i=5;
     }
     
    
    return i;
    }
     public int get_voting_res(String party) throws SQLException{
    int i=0;
     con=db.getconnection();
     st=con.createStatement();
     String q="select count1 from votingcounter where party='"+party+"'";
        System.out.println(""+q);
    rs=st.executeQuery(q);
    if(rs.next())
    {
    
    i=rs.getInt(1);
    }
    return i;
     }
    public int update_voting_count(String party) throws SQLException{
    int i=0;
     con=db.getconnection();
     st=con.createStatement();
    
    i=update_voting_count(party);
    i++;
    String q="update votingcount set count1='"+i+"' where party='"+party+"'";
    st.executeUpdate(q);
        return i;
    
    
    
    
    }
    
public int loginCheck2(String e,String p) throws SQLException{
    int i=0;
    con=db.getconnection();
    String DBEcard="",DBPass="",utype="";
    String q="select * from voterregister where voterid='"+e+"' and password='"+p+"'";
    st=con.createStatement();
    rs=st.executeQuery(q);
    while(rs.next())
    {
    DBEcard=rs.getString("voterid");
    DBPass=rs.getString("password");
    
    
    }
    if(e.equals(DBEcard)&&p.equals(DBPass)){
    i=1;
    }
    else{
    
    i=0;
    }
    
    return i;
    }

public int insertState(String state,String city) throws SQLException{
int i=0;
 String q=" insert into location (state,district) values('"+state+"','"+city+"')";
 con=db.getconnection();
 st=con.createStatement();
   i=st.executeUpdate(q);
return i;
}



public String  validate_cast_time(String ecard) throws SQLException{

String stdate = "", stTime = "", endTime = "", age = "", res = "", address1 = "", city = "", dist = "", state = "", pin = "", mobile = "";
                                                   
 con=db.getconnection();
 st=con.createStatement();
  String q = "select * from voterregister where ecardnumber='" + ecard + "'";
    System.out.println(q);
 ResultSet resultSet = st.executeQuery(q);
 while (resultSet.next()) {
dist = resultSet.getString("district");
state = resultSet.getString("state");
}
 
 String q1 = "select * from ecschd where consti='" + dist + "'";
    System.out.println(q1);
 ResultSet resultSet1 = st.executeQuery(q1);
while (resultSet1.next()) {
 stdate = resultSet1.getString("stdate");
  stTime = resultSet1.getString("stTime");
 endTime = resultSet1.getString("endTime");
 }
 
 String cdate = (new java.util.Date()).toString();
  String day = cdate.substring(8, 10);
 String month = cdate.substring(4, 7);
  String year = cdate.substring(24, 28);
 String hour = cdate.substring(11, 13);
  String min = cdate.substring(14, 16);
  String sec = cdate.substring(17, 19);
  String curDate = day + "-" + month + "-" + year;
    String time = hour + "-" + min + "-" + sec;

   System.out.println(dist + "........" + ecard + ">>>>>>>>>>>>>>>>>>>>>>>>" + stTime + ">>>>>>>>>>>>>>>>>>" + endTime);
   String st[]=stTime.split("-");
   String end[]=endTime.split("-");
 if(curDate.equalsIgnoreCase(stdate))
 {
 if(Integer.parseInt(hour)>=Integer.parseInt(st[0])&&Integer.parseInt(hour)<=Integer.parseInt(end[0])){
  res="during";
 }
   else{
    res="not started";
              
   }
 
 }
 else{
  res="no";
 }
return res;
}



}
