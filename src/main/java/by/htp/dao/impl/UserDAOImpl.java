package by.htp.dao.impl;

import by.htp.dao.DAOException;
import by.htp.dao.UserDAO;
import by.htp.entity.Role;
import by.htp.entity.User;
import by.htp.entity.UserDetails;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean logination(String login, String password) throws DAOException {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            Query<User> query = currentSession.createQuery("FROM User u WHERE u.login = :login", User.class);
            query.setParameter("login", login);
            User user = query.uniqueResult();
            if (user != null) {
                return BCrypt.checkpw(password, user.getPassword());
            }
            return false;
        } catch (HibernateException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean registration(String login, String password, String name, String surname, String email, Date birthday) throws DAOException {
        try {
            Session currentSession = sessionFactory.getCurrentSession();

            if (checkLogin(login)) {
                return false;
            }

            UserDetails newUserDetails = insertNewUserDetails(name, surname, email, birthday, currentSession);
            insertNewUser(login, password, currentSession, newUserDetails);
            return true;
        } catch (HibernateException e) {
            throw new DAOException(e);
        }

    }

    @Override
    public String getRole(String login) throws DAOException {
        String defaultRole = "guest";

        try {
            Session currentSession = sessionFactory.getCurrentSession();
            Query<User> query = currentSession.createQuery("FROM User u WHERE u.login = :login", User.class);
            query.setParameter("login", login);
            User user = query.uniqueResult();
            if (user == null) {
                return defaultRole;
            }
            return getRoleById(user.getRole()).getRoleName();
        } catch (HibernateException e) {
            throw new DAOException(e);
        }
    }

    private Role getRoleById(int roleId) throws DAOException {
        int defaultRole = 4;

        try {
            Session currentSession = sessionFactory.getCurrentSession();
            Query<Role> query = currentSession.createQuery("FROM Role r WHERE r.id = :roleId", Role.class);
            query.setParameter("roleId", roleId);
            Role role = query.uniqueResult();
            if (role == null) {
                return getRoleById(defaultRole);
            }
            return role;
        } catch (HibernateException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public User getUserById(Integer usersId) throws DAOException {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            Query<User> query = currentSession.createQuery("FROM User u WHERE u.id = :id", User.class);
            query.setParameter("id", usersId);
            return query.uniqueResult();
        } catch (HibernateException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Integer getUsersId(String login) throws DAOException {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            Query<User> query = currentSession.createQuery("FROM User u WHERE u.login = :login", User.class);
            query.setParameter("login", login);
            User user = query.uniqueResult();
            return user.getId();
        } catch (HibernateException e) {
            throw new DAOException(e);
        }
    }

    private void insertNewUser(String login, String password, Session currentSession, UserDetails newUserDetails) {
        User user = new User();

        user.setLogin(login);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        user.setRole(3);
        user.setUserDetails(newUserDetails);
        currentSession.save(user);
    }

    private UserDetails insertNewUserDetails(String name, String surname, String email, Date birthday, Session currentSession) {
        UserDetails newUserDetails = new UserDetails();

        newUserDetails.setName(name);
        newUserDetails.setSurname(surname);
        newUserDetails.setEmail(email);
        newUserDetails.setBirthday(birthday);
        currentSession.save(newUserDetails);
        return newUserDetails;
    }

    private boolean checkLogin(String login) throws DAOException {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            Query<User> query = currentSession.createQuery("FROM User u WHERE u.login = :login", User.class);
            query.setParameter("login", login);
            User user = query.uniqueResult();

            return user != null;
        } catch (HibernateException e) {
            throw new DAOException(e);
        }
    }
}
