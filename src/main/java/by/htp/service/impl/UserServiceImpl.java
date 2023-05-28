package by.htp.service.impl;

import by.htp.dao.DAOException;
import by.htp.dao.impl.UserDAOImpl;
import by.htp.entity.User;
import by.htp.service.ServiceException;
import by.htp.service.UserService;
import by.htp.util.validation.ValidationException;
import by.htp.util.validation.impl.UserValidationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAOImpl userDAO;

    @Transactional
    @Override
    public String signIn(String login, String password) throws ServiceException {
        try {
            if (userDAO.logination(login, password)) {
                return userDAO.getRole(login);
            } else {
                return "guest";
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    @Override
    public boolean registration(User user) throws ServiceException, ValidationException {
        UserValidationImpl.UserValidationBuilder builder = new UserValidationImpl.UserValidationBuilder();
        UserValidationImpl userValidation = builder.checkLogin(user.getLogin()).checkPassword(user.getPassword())
                .checkName(user.getUserDetails().getName()).checkSurname(user.getUserDetails().getSurname())
                .checkEmail(user.getUserDetails().getEmail()).validateAll();

        if (!userValidation.getErrors().isEmpty()) {
            throw new ValidationException(userValidation.errorMessage());
        }
        try {
            return userDAO.registration(user.getLogin(), user.getPassword(), user.getUserDetails().getName(), user.getUserDetails().getSurname(),
                    user.getUserDetails().getEmail(), user.getUserDetails().getBirthday());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    @Override
    public String getRole(String login) throws DAOException {
        try {
            return userDAO.getRole(login);
        } catch (DAOException e) {
            throw new DAOException(e);
        }
    }

    @Transactional
    @Override
    public User getUserById(Integer usersId) throws ServiceException {
        try {
            return userDAO.getUserById(usersId);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    @Override
    public Integer getUsersId(String login, String password) throws ServiceException {
        try {
            if (userDAO.logination(login, password)) {
                return userDAO.getUsersId(login);
            }
            return -1;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
