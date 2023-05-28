package by.htp.service;

import by.htp.entity.News;
import by.htp.util.validation.ValidationException;

import java.util.List;

public interface NewsService {

    List<News> latestList(int count) throws ServiceException;

    List<News> list() throws ServiceException;

    News findById(int id) throws ServiceException;

    void deleteSelectedNews(String[] idNewses) throws ServiceException;

    void update(News news) throws ServiceException, ValidationException;

    void addNews(News news) throws ServiceException, ValidationException;

}
