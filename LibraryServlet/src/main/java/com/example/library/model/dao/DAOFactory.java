package com.example.library.model.dao;


public class DAOFactory {

    private DAOFactory() {
    }

    private static AuthorDAO authorDAO = null;

    public static AuthorDAO getAuthor() {
        if (authorDAO == null)
            authorDAO = new AuthorDAO();
        return authorDAO;
    }

    private static BookDAO bookDAO = null;

    public static BookDAO getBook() {
        if (bookDAO == null)
            bookDAO = new BookDAO();
        return bookDAO;
    }

    private static PublicationDAO publicationDAO = null;

    public static PublicationDAO getPublication(){
        if (publicationDAO == null)
            publicationDAO = new PublicationDAO();
        return publicationDAO;
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
