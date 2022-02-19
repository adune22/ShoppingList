package ca.sait.lab5b.servlets;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        if (name == null) {

            getServletContext().getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("WEB-INF/shoppingList.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String action = request.getParameter("action");

        if (action != null && action.equals("add")) {
            String item = request.getParameter("item");
            ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");

            items.add(item);
            session.setAttribute("items", items);

        } else if (action != null && action.equals("delete")) {
            String item = request.getParameter("item");
            ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");

            items.remove(item);
            session.setAttribute("items", items);

        } else {

            String name = request.getParameter("name");

            ArrayList<String> items = new ArrayList<>();

            session.setAttribute("name", name);
            session.setAttribute("items", items);
        }

        getServletContext().getRequestDispatcher("WEB-INF/shoppingList.jsp").forward(request, response);

    }
}
