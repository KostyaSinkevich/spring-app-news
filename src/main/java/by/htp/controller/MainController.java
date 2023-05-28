package by.htp.controller;

import by.htp.dao.DAOException;
import by.htp.entity.News;
import by.htp.entity.Role;
import by.htp.entity.User;
import by.htp.service.ServiceException;
import by.htp.service.impl.NewsServiceImpl;
import by.htp.service.impl.UserServiceImpl;
import by.htp.util.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@ControllerAdvice
@RequestMapping("/news")
public class MainController {

    @Autowired
    private NewsServiceImpl newsService;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/goToBasePage")
    public String basePage(Model model, HttpSession session) {
        List<News> latestNews;
        try {
            latestNews = newsService.latestList(5);
            model.addAttribute("news", latestNews);
            if (session.getAttribute("role") == null) {
                session.setAttribute("role", "guest");
            }
            model.addAttribute("presentation", "newsList");
            return "baseLayout";
        } catch (ServiceException e) {
            return "redirect:/news/goToErrorPage";
        }
    }

    @RequestMapping("/goToRegistrationPage")
    public String goToRegistrationPage(Model model, HttpSession session) {
        try {
            model.addAttribute("user", new User());
            model.addAttribute("presentation", "registration");
            return "baseLayout";
        } catch (Exception e) {
            session.setAttribute("role", "guest");
            return "redirect:/news/goToErrorPage";
        }
    }

    @RequestMapping(value = "/doRegistration", method = RequestMethod.POST)
    public String doRegistration(@ModelAttribute("user") User user, HttpSession session) {
        try {
            if (userService.registration(user)) {
                session.setAttribute("role", userService.getRole(user.getLogin()));
                session.setAttribute("info_message", "Registration was successful");
                return "redirect:/news/goToNewsList";
            }
            session.setAttribute("info_message", "Registration failed, try again");
            return "redirect:/news/goToRegistrationPage";
        } catch (ServiceException | DAOException e) {
            session.setAttribute("role", "guest");
            return "redirect:/news/goToNewsList";
        } catch (ValidationException e) {
            String[] errors = e.getMessage().split(";");
            session.setAttribute("validationError", errors);
            session.setAttribute("role", "guest");
            return "redirect:/news/goToErrorPage";
        }
    }

    @RequestMapping("/doSignIn")
    public String doSignIn(@RequestParam("login") String login, @RequestParam("password") String password, HttpSession session) {
        try {
            String role = userService.signIn(login, password);
            if (!role.equals("guest")) {
                session.setAttribute("usersId", userService.getUsersId(login, password));
                session.setAttribute("role", role);
            } else {
                session.setAttribute("role", "guest");
                session.setAttribute("authenticationError", "wrong login or password");
            }
            return "redirect:/news/goToNewsList";
        } catch (ServiceException e) {
            session.setAttribute("role", "guest");
            return "redirect:/news/goToErrorPage";
        }
    }

    @RequestMapping("/doSignOut")
    public String doSignOut(HttpSession session) {
        session.setAttribute("role", "guest");
        return "redirect:/news/goToBasePage";
    }

    @RequestMapping("/goToAddNews")
    public String goToAddNews(Model model) {
        model.addAttribute("news", new News());
        model.addAttribute("presentation", "addNews");
        return "baseLayout";
    }

    @RequestMapping(value = "/addNews", method = RequestMethod.POST)
    public String addNews(@ModelAttribute("news") News news, HttpSession session) {
        try {
            Integer usersId = (Integer) session.getAttribute("usersId");
            news.setUser(userService.getUserById(usersId));
            newsService.addNews(news);
            return "redirect:/news/goToNewsList";
        } catch (ServiceException | ValidationException e) {
            String[] errors = e.getMessage().split(";");
            session.setAttribute("validationError", errors);
            return "redirect:/news/goToErrorPage";
        }
    }

    @RequestMapping("/goToEditNews")
    public String goToEditNews(@RequestParam("id") Integer id, Model model, HttpSession session) {
        News news;
        try {
            news = newsService.findById(id);
            model.addAttribute("news", news);
            session.setAttribute("newsId", news.getIdNews());
            model.addAttribute("presentation", "editNews");
            return "baseLayout";
        } catch (ServiceException e) {
            return "redirect:/news/goToErrorPage";
        }
    }

    @RequestMapping(value = "/editNews", method = RequestMethod.POST)
    public String doEditNews(@ModelAttribute("news") News news, HttpSession session) {
        try {
            News updateNews = newsService.findById((Integer) session.getAttribute("newsId"));
            updateNews.setTitle(news.getTitle());
            updateNews.setPublicationDate(news.getPublicationDate());
            updateNews.setBrief(news.getBrief());
            updateNews.setContent(news.getContent());
            newsService.update(updateNews);
            return "redirect:/news/goToNewsList";
        } catch (ServiceException | ValidationException e) {
            return "redirect:/news/goToErrorPage";
        }
    }

    @RequestMapping("/goToNewsList")
    public String goToNewsList(Model model) {
        List<News> newsList;
        try {
            newsList = newsService.list();
            model.addAttribute("news", newsList);
            model.addAttribute("presentation", "newsList");

            return "baseLayout";
        } catch (ServiceException e) {
            return "redirect:/news/goToErrorPage";
        }
    }

    @RequestMapping("/goToViewNews")
    public String goToViewNews(@RequestParam("id") Integer id, Model model) {
        News news;
        try {
            news = newsService.findById(id);
            model.addAttribute("news", news);
            model.addAttribute("presentation", "viewNews");

            return "baseLayout";
        } catch (ServiceException e) {
            return "redirect:/news/goToErrorPage";
        }
    }

    @RequestMapping(value = "/deleteNews", method = RequestMethod.POST)
    public String doDeleteNews(HttpServletRequest request) {
        try {
            String[] idNews = request.getParameterValues("idNews");
            if (idNews == null) {
                return "redirect:/news/goToErrorPage";
            }
            newsService.deleteSelectedNews(idNews);
            return "redirect:/news/goToNewsList";
        } catch (ServiceException e) {
            return "redirect:/news/goToErrorPage";
        }
    }

    @RequestMapping("/goToErrorPage")
    private String goToErrorPage(Model model) {
        model.addAttribute("presentation", "error");
        return "baseLayout";
    }
}
