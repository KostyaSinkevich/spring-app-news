package by.htp.dao.impl;

import by.htp.dao.DAOException;
import by.htp.dao.NewsDAO;
import by.htp.entity.News;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDAOImpl implements NewsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<News> getList() throws DAOException {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            Query<News> query = currentSession.createQuery("FROM News n WHERE n.statusActive=1 ORDER BY n.publicationDate DESC", News.class);
            return query.getResultList();
        } catch (HibernateException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<News> getLatestsList(int count) throws DAOException {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            Query<News> query = currentSession.createQuery("FROM News n WHERE n.statusActive = true ORDER BY n.publicationDate DESC", News.class);
            query.setMaxResults(count);
            return query.getResultList();

        } catch (HibernateException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public News fetchById(int id) throws DAOException {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession.get(News.class, id);
        } catch (HibernateException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void addNews(News news) throws DAOException {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            news.setStatusActive(true);
            currentSession.save(news);
        } catch (HibernateException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void updateNews(News news) throws DAOException {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            news.setStatusActive(true);
            currentSession.update(news);

        } catch (HibernateException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteSelectedNewses(String[] idNewses) throws DAOException {
        Session currentSession = sessionFactory.getCurrentSession();

        for (String idNews : idNewses) {
            try {
                int id = Integer.parseInt(idNews);
                Query query = currentSession.createQuery("UPDATE News n SET n.statusActive=:status WHERE n.idNews=:id");
                query.setParameter("status", false);
                query.setParameter("id", id);
                query.executeUpdate();

            } catch (NumberFormatException e) {
                throw new DAOException(e);
            }

        }
    }
}
