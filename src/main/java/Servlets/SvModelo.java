/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Logica.Modelo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 *
 * @author juand
 */
@WebServlet(name = "SvModelo", urlPatterns = {"/SvModelo"})
@MultipartConfig
public class SvModelo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        private ArrayList<Modelo> modelos = new ArrayList<>(); // Declare ArrayList as a member variable
        
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvModelo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvModelo at " + request.getContextPath() + "</h1>");
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
        
            String tipoZapato = request.getParameter("tipoZapato");
            String nombreForm = request.getParameter("nombre");
            Part imagenParte = request.getPart("imagen");
            InputStream contenidoImagen = imagenParte.getInputStream();
            String nombreImagen = imagenParte.getSubmittedFileName();

            Modelo modelo = new Modelo(nombreForm, tipoZapato, nombreImagen);

            HttpSession session = request.getSession();

            // Obtener la lista de modelos de la sesión
            modelos =  (ArrayList<Modelo>) session.getAttribute("modelos");

            // Si la lista de modelos no existe en la sesión, crear una nueva
            if (modelos == null) {
                modelos = new ArrayList<>();
            }

            modelos.add(modelo);
            
              // Imprimimos los datos en la consola para comprobar que si estan llegando

                System.out.println("Nombre Formulario: " + nombreForm);
                System.out.println("Tipo de Zapato: " + tipoZapato);
                System.out.println("Nombre de Imagen: " + nombreImagen);

                // Imprimir datos del ArrayList (opcional)
                System.out.println("Datos del ArrayList: ");
                for (Modelo m : modelos) {
                    System.out.println("Nombre: " + m.darNombreModelo());
                    System.out.println("Tipo Zapato: " + m.darTipoZapato());
                    System.out.println("Imagen: " + m.darImagen());
                }

                 session.setAttribute("modelos", modelos);

                 response.sendRedirect("index.jsp");
       
              


                // Envía una respuesta al cliente
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("<head><title>Respuesta del Servidor</title></head>");
                out.println("<body>");
                out.println("<h1>Modelo Agregado Exitosamente</h1>");
                out.println("<p>Nombre: " + nombreForm + "</p>");
                out.println("<p>Tipo de Zapato: " + tipoZapato + "</p>");
                out.println("<p>Nombre Imagen: " + nombreImagen + "</p>");
                out.println("<a href='index.jsp'>Regresar</a>");
                out.println("</body></html>");
        

     

        


     
        
        
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
