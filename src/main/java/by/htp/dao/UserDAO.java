package by.htp.dao;

import by.htp.entity.User;

import java.util.Date;

public interface UserDAO {

    boolean logination(String login, String password) throws DAOException;

    boolean registration(String login, String password, String name, String surname, String email, Date birthday) throws DAOException;

    String getRole(String login) throws DAOException;

    User getUserById(Integer usersId) throws DAOException;

    Integer getUsersId(String login) throws DAOException;
}
