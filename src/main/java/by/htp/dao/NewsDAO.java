package by.htp.dao;

import by.htp.entity.News;

import java.util.List;

public interface NewsDAO {

    List<News> getList() throws DAOException;

    List<News> getLatestsList(int count) throws DAOException;

    News fetchById(int id) throws DAOException;

    void addNews(News news) throws DAOException;

    void updateNews(News news) throws DAOException;

    void deleteSelectedNewses(String[] idNewses) throws DAOException;

}
