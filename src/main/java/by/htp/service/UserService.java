package by.htp.service;

import by.htp.dao.DAOException;
import by.htp.entity.User;
import by.htp.util.validation.ValidationException;

public interface UserService {

    String signIn(String login, String password) throws ServiceException;

    boolean registration(User user) throws ServiceException, ValidationException;

    String getRole(String login) throws DAOException;

    User getUserById(Integer usersId) throws ServiceException;

    Integer getUsersId(String login, String password) throws ServiceException;
}
