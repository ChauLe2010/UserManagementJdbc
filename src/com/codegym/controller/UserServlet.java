package com.codegym.controller;

import com.codegym.dao.UserDAO;
import com.codegym.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserServlet",urlPatterns = "/users")
public class UserServlet extends HttpServlet {

    private UserDAO userDAO=new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if(action==null){
            action="";
        }
        switch (action){
            case "create":
                createUser(request,response);
                break;
            case "edit":
                updateUser(request,response);
                break;

            default:
                break;

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if(action==null){
            action="";
        }
        switch (action){
            case "create":
                showCreateForm(request,response);
                break;
            case "edit":
                showEditForm(request,response);
                break;
            case "delete":
                deleteUser(request,response);
                break;
            default:
                listUsers(request,response);
                break;
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        boolean success=this.userDAO.deleteUser(id);
        RequestDispatcher requestDispatcher=null;
        if(success){
            requestDispatcher=request.getRequestDispatcher("user/list.jsp");
            request.setAttribute("successMessage","Deleted");
            List<User> listUsers=this.userDAO.selectAllUsers();
            request.setAttribute("listUsers",listUsers);
        }else{
            requestDispatcher=request.getRequestDispatcher("error.jsp");
        }
        requestDispatcher.forward(request,response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String country=request.getParameter("country");
        boolean success=this.userDAO.updateUser(new User(id,name,email,country));
        RequestDispatcher requestDispatcher=null;
        if(success){
            requestDispatcher=request.getRequestDispatcher("user/edit.jsp");
            request.setAttribute("successMessage","Updated!");
        }else{
            requestDispatcher=request.getRequestDispatcher("error.jsp");
        }
        requestDispatcher.forward(request,response);
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String country=request.getParameter("country");
        User user=new User(name,email,country);
        this.userDAO.insertUser(user);
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("user/create.jsp");
        request.setAttribute("successMessage","Created!");
        requestDispatcher.forward(request,response);
    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        User user = this.userDAO.selectUser(id);
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("user/edit.jsp");
        request.setAttribute("user",user);
        requestDispatcher.forward(request,response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("user/create.jsp");
        requestDispatcher.forward(request,response);
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> listUsers=this.userDAO.selectAllUsers();
        request.setAttribute("listUsers",listUsers);
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("user/list.jsp");
        requestDispatcher.forward(request,response);
    }
}
