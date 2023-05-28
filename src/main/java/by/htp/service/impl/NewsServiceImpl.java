package by.htp.service.impl;

import by.htp.dao.DAOException;
import by.htp.dao.impl.NewsDAOImpl;
import by.htp.entity.News;
import by.htp.service.NewsService;
import by.htp.service.ServiceException;
import by.htp.util.validation.ValidationException;
import by.htp.util.validation.impl.NewsValidationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDAOImpl newsDAO;

    @Transactional
    @Override
    public List<News> latestList(int count) throws ServiceException {
        try {
            return newsDAO.getLatestsList(5);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    @Override
    public List<News> list() throws ServiceException {
        try {
            return newsDAO.getList();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    @Override
    public News findById(int id) throws ServiceException {
        try {
            return newsDAO.fetchById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    @Override
    public void deleteSelectedNews(String[] idNewses) throws ServiceException {
        try {
            newsDAO.deleteSelectedNewses(idNewses);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    @Override
    public void update(News news) throws ServiceException, ValidationException {
        NewsValidationImpl.NewsValidationBuilder builder = new NewsValidationImpl.NewsValidationBuilder();
        NewsValidationImpl newsValidation = builder.checkTitle(news.getTitle()).checkBrief(news.getBrief()).checkContent(news.getContent()).validateAll();

        if (!newsValidation.getErrors().isEmpty()) {
            throw new ValidationException(newsValidation.errorMessage());
        }

        try {
            newsDAO.updateNews(news);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    @Override
    public void addNews(News news) throws ServiceException, ValidationException {
        NewsValidationImpl.NewsValidationBuilder builder = new NewsValidationImpl.NewsValidationBuilder();
        NewsValidationImpl newsValidation = builder.checkTitle(news.getTitle()).checkBrief(news.getBrief()).checkContent(news.getContent()).validateAll();

        if (!newsValidation.getErrors().isEmpty()) {
            throw new ValidationException(newsValidation.errorMessage());
        }

        try {
            newsDAO.addNews(news);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
