
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ProccessData extends HttpServlet {
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        String nm=(String)session.getAttribute("name");
        String gender=(String)session.getAttribute("gender");
        String year=(String)session.getAttribute("year");
      /*  out.write("This page will proccess Your life expactancy     "+nm);*/
        int age=2017-Integer.parseInt(year);              //birth year required
       /* out.println("<br/>You age is : "+age);
        out.println("<br/>You are a "+gender);*/
        int life,life_m=0;
       /* int counter=0;   */           //to check filled status of form
        if(gender.equalsIgnoreCase("female"))                           //gender required
        {
            if(age>55&&age<=75)
                life=85;
            if(age>75&&age<=85)
                life=90;
            if(age>90)
                life=age+15;
            else
              life = 81;  
        }
        else
        {
            if(age>55&&age<=75)
                life=83;
            if(age>75&&age<=85)
                life=88;
            if(age>90)
                life=age+15;
            else
                life = 77;
        }
        
//place=urban&polluted=yes&drive=no&sleep=normal&job=mental&jobhrs=on&smoke=no&drink=no&fast=yes&diet=unhealthy&stress=high&diabetes=no&hbp=no&lbp=no&obesity=no
        String place=request.getParameter("place");
        String polluted=request.getParameter("polluted");
        String drive=request.getParameter("drive");
        String sleep=request.getParameter("sleep");
        String job=request.getParameter("job");
        String jobhrs=request.getParameter("jobhrs");
        String smoke=request.getParameter("smoke");
        String drink=request.getParameter("drink");
        String fast=request.getParameter("fast");
        String diet=request.getParameter("diet");
        String stress=request.getParameter("stress");
        String diabetes=request.getParameter("diabetes");
        String hbp=request.getParameter("hbp");
        String lbp=request.getParameter("lbp");
        String obesity=request.getParameter("obesity");

        if(place!=null)
        {
               if (place.equals("urban"))
                   life_m=life_m + 6;
               
        }
        if(polluted!=null)
        {
            if(polluted.equals("yes"))
            {
                life=life - 1;
                life_m=life_m+6;
            }
            
        }
        if(drive!=null)
        {
            if(drive.equals("yes"))
            {
                life=life - 1;
                life_m=life_m + 3;
            }
           
        }
        if((stress!=null)&&(sleep!=null))
        {
            if((sleep.equals("low"))&&(stress.equals("high")))
                life=life - 4;
            if((sleep.equals("low"))&&(stress.equals("moderate")))
                life=life - 2;
            if((sleep.equals("normal"))&&(stress.equals("high")))
                life=life - 2;
            
        }
        if((stress==null)||(sleep==null))
        {
              if((stress!=null)&&(stress.equals("high")))
                  life = life - 2;
              if((sleep!=null)&&(sleep.equals("low")))
                  life = life - 2;
              if((stress==null)&&(sleep==null))
                  life_m = life_m + 8;
        }

        if((job!=null)&&(jobhrs!=null))
        {
             if((job.equalsIgnoreCase("mental"))&&(jobhrs.equalsIgnoreCase("high")))
                life=life-2;
             if((job.equalsIgnoreCase("mental"))&&(jobhrs.equalsIgnoreCase("normal")))
                life_m=life_m + 8;
             if((job.equalsIgnoreCase("mental"))&&(jobhrs.equalsIgnoreCase("null")))
                life_m=life_m + 8;
             if((job.equalsIgnoreCase("physical"))&&(jobhrs.equalsIgnoreCase("high")))
                life=life-2;
        }
        else
        {
            if((job!=null)&&(job.equals("mental")))
                life=life-1;

        }

        if((fast!=null)&&(diet!=null))
        {
            if((fast.equals("yes"))&&(diet.equals("unhealthy")))
            {
                life=life-1;
                life_m=life_m+6;
            }
           
        }
        if((fast==null)||(diet==null))
        {
            if((diet!=null)&&(diet.equalsIgnoreCase("unhealthy")))//include breakfast
                life_m=life_m + 6;

        }

        if(smoke!=null)
        {
            if(smoke.equals("yes"))
                life= life - 3;
            
        }
        if(drink!=null)
        {
            if(drink.equals("yes"))
            {
                if((hbp!=null)||(lbp!=null))
                {
                        life = life - 4;
                }
                else
                life= life - 2;
            }
        }
        if(diabetes!=null)
        {
          if(diabetes.equals("yes"))
                life= life - 3;
            
        }
        if(hbp!=null)
        {
          if(hbp.equals("yes"))
                life= life - 3;
           
        }
        if(lbp!=null)
        {
          if(lbp.equals("yes"))
                life= life - 4;
           
        }

        /*
         Obesity calculated with gender and age get from session
         */
        if(obesity!=null)
        {
            if(obesity.equals("yes"))
            {
            if(age<=40)
            {
                if(gender.equalsIgnoreCase("female"))
                    life=life-2;
                else
                    life=life-3;
            }
            else
                life=life-1;
            }
        }
      
        if(life<=(age+6))
        {
            life=age+7;
        }
        if((life==77)&&gender.equalsIgnoreCase("male"))
        {
            life=75;
        }
         if((life==81)&&gender.equalsIgnoreCase("female"))
        {
            life=80;
        }

        int month=0,q=0,r=0;
        if(life_m!=0)
        {
            q = life_m/12;
            r=life_m%12;
            if(r!=0)
            {
                life=life-q-1;
                month=12-r;
            }
            else
            {
                life=life-q;
            }
        }

        out.println("<html>");
        out.println("<body background='lastbg.jpg'>");
        out.println("<br/><br/><br/><br/><br/><center><font color='white' face='georgia'>");
        out.println("<h2>Hello, "+nm);
        out.println("<br/><br/>Your current age is  "+age+"</h2>");
        out.println("<br/><h1>Your expectancy is calculated as "+life+" years and "+month+" month(s)</h1>");
        out.println("</font></center>");
        out.println("<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>");
        if(age>40)
        {
            out.println("<font size='4' color='yellow'>");
            out.println("   Early detection of diseases can help you control them and will let you live more :)<br/>");
            out.println("   Live Healthy Live Best :) ");
            out.println("</font>");
        }
        out.println("<br/><br/><br/><hr/>");
        out.println("<font size='3' color='yellow'><sup>**</sup>10% positive or negative likely deviation acceptable in calculated value provided that the data is right </font>");
        out.println("</body>");
        out.println("</html>");

     //   out.println("<h1>Your expectancy calculated as : "+life+" years</h1>"+" "+month+" month(s)");




    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
