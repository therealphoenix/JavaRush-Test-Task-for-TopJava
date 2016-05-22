package com.klindziuk.test.controller;

 import java.text.DateFormat;
 import java.util.Date;
 import java.util.List;
 import java.util.Locale;
 import com.klindziuk.test.model.User;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.support.PagedListHolder;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.ModelAttribute;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestMethod;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.servlet.ModelAndView;
 import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 import com.klindziuk.test.service.UserService;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate );

        return "home";
    }

    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ModelAndView userListPage(@RequestParam(required = false) Integer page) {
        ModelAndView mav = new ModelAndView("list");

        List<User> userList = userService.findAll();

        PagedListHolder<User> pagedListHolder = new PagedListHolder<>(userList);
        pagedListHolder.setPageSize(10);
        int numberOfPages = pagedListHolder.getPageCount();
        mav.addObject("maxPages",numberOfPages );

        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;

        mav.addObject("page", page);
        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
             userList = pagedListHolder.getPageList();
            mav.addObject("userList", userList);
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            userList = pagedListHolder.getPageList();
            mav.addObject("userList", userList);

        }


        return mav;
    }



    @RequestMapping(value="/search", method=RequestMethod.GET)
    public ModelAndView userSearchPage(@RequestParam(value = "searchstring", required = false) String s) {
        ModelAndView mav = new ModelAndView("list");

        List<User> userList = userService.search(s);

        mav.addObject("userList", userList);

        return mav;
    }

    @RequestMapping(value="/create", method=RequestMethod.GET)
    public ModelAndView newUserPage() {
        ModelAndView mav = new ModelAndView("form", "user", new User());

        return mav;
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute User user, final RedirectAttributes redirectAttributes){

        ModelAndView mav = new ModelAndView();
        String message = "New user " + user.getFirstName() + " " + user.getLastName() + " was successfully created.";

        userService.create(user);
        mav.setViewName("redirect:/list");

        redirectAttributes.addFlashAttribute("message", message);

        return mav;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editUserPage(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("edit");

        User user = userService.findById(id);

        mav.addObject("user", user);

        return mav;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute User user, @PathVariable Integer id, final RedirectAttributes redirectAttributes){
        ModelAndView mav = new ModelAndView("redirect:/list");

        userService.update(user);
        String message = "User " + user.getFirstName() + " " + user.getLastName() + " was successfully updated.";

        redirectAttributes.addFlashAttribute("message", message);

        return mav;
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteShop(@PathVariable Integer id, final RedirectAttributes redirectAttributes){
        ModelAndView mav = new ModelAndView("redirect:/list");

        User user = userService.delete(id);
        String message = "User " + user.getFirstName() + " " + user.getLastName() + " was successfully deleted.";

        redirectAttributes.addFlashAttribute("message", message);

        return mav;
    }



}
