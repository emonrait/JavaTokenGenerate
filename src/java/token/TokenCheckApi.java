/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package token;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author user
 */
@WebServlet(name = "TokenCheckApi", urlPatterns = {"/TokenCheckApi"})
public class TokenCheckApi extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TokenCheckApi</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TokenCheckApi at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        String requestCode = request.getParameter("requestCode");
        System.out.println("requestCode = " + requestCode);
        if ("1".equals(requestCode)) {

            String userName = request.getParameter("userName");
            System.out.println("userName = " + userName);

            String password = request.getParameter("password");
            System.out.println("password = " + password);

            try {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                TokenCheckDao dao = new TokenCheckDao();
                TokenModel reqModel = new TokenModel();
                JSONArray ja = new JSONArray();

                TokenModel cardModel = dao.getToken(reqModel);

                JSONObject jSONObject = new JSONObject();
                jSONObject.put("tockenNo", cardModel.getTockenNo());
                jSONObject.put("outCode", cardModel.getOutCode());
                jSONObject.put("outMessage", cardModel.getOutMessage());
                jSONObject.put("userName", cardModel.getUserName());
                jSONObject.put("password", cardModel.getPassword());
                jSONObject.put("sessionTime", cardModel.getSessionTime());

                ja.add(jSONObject);

                System.out.println("Token list Information = " + ja);
                response.addHeader("Access-Control-Allow-Origin", "*");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(ja.toString());
                response.getWriter().flush();
            } catch (Exception ex) {
                Logger.getLogger(TokenCheckApi.class.getName()).log(Level.SEVERE, null, ex);
            }
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
