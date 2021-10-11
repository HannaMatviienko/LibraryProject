package com.example.library.model.dao;


public class DAOFactory {

    private DAOFactory() {
    }

    private static UserDAO userDAO = null;

    public static UserDAO getUser() {
        if (userDAO == null)
            userDAO = new UserDAO();
        return userDAO;
    }

    public static void setUser(UserDAO userDAO) {
        DAOFactory.userDAO = userDAO;
    }

    private static DateDAO dateDAO = null;

    public static DateDAO getDate() {
        if (dateDAO == null)
            dateDAO = new DateDAO();
        return dateDAO;
    }

    public static void setDate(DateDAO dateDAO) {
        DAOFactory.dateDAO = dateDAO;
    }

}
