package com.example.auth.web;

import com.example.auth.web.model.Calculator;
import com.example.auth.web.model.CalculatorResult;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.auth.web.model.User;
import com.example.auth.web.repository.CalculatorRepository;
import com.example.auth.web.repository.UserRepository;
import com.example.auth.web.service.SecurityService;
import com.example.auth.web.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @Autowired
    UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CalculatorRepository calculatorRepository;

    @Autowired
    private UserRepository userRepository;
    
    @RequestMapping(value = "/main")
    public String main(Authentication authentication) {
        //System.out.println(userRepository.findAll());
        return "main";
    }

    @RequestMapping("/login")
    public String login(Model model, String error, String logout, HttpServletRequest request) {
        if (logout != null) {
            model.addAttribute("logout", "You have been logged out successfully.");
        }
        
        return "login";
    }

    @RequestMapping(value = "/loginError")
    public String loginError(Model model, String username) {
        model.addAttribute("error", "Your username and password is invalid.");
        model.addAttribute("username", username);
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,
            Model model, String[] roles){
        
             //Authentication authentication) {
        /*String password = userForm.getPassword();
        String[] roles = new String[1];
        roles[0] = "USER";
        userService.saveUser(userForm, roles);
        securityService.autologin(userForm.getUsername(), password);*/
        String password = userForm.getPassword();
        System.out.println(roles);        
        userService.saveUser(userForm,roles);
        securityService.autologin(userForm.getUsername(),password);
        return "redirect:/main";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "/admin/admin";
    }

    @RequestMapping("/user")
    public String user(Model model,Calculator calculator) {
        model.addAttribute(calculator);
        return "/user/user";
    }
    
    @RequestMapping("/403")
    public String access() {
        return "/access";
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String validateForm(
            @Valid Calculator calculator,
            BindingResult bindingResult,
            Model model,
            Authentication authentication,
            User user) {
        if (bindingResult.hasErrors()) {
            return "form";
        } else {
            CalculatorResult calculatorResult = new CalculatorResult(calculator.getDataWyjazdu(), calculator.getGodzinaWyjazdu(), 
                    calculator.getDataPrzyjazdu(), calculator.getGodzinaPrzyjazdu(), calculator.getDietaPodrozy(),
                    calculator.getIloscSniadan(), calculator.getIloscObiadow(), calculator.getIloscKolacji(), 
                    calculator.getSrodekTransportu(), calculator.getCenaSrodekTransportu(), calculator.getPojazd(), 
                    calculator.getIloscKilometrow(), calculator.getIloscDobDojazd(), calculator.getCenaBiletu(),
                    calculator.getLiczbaNoclegow(), calculator.getCenaNoclegu(),
                    calculator.czasPodrozyToString(calculator.getDataWyjazdu(), calculator.getDataPrzyjazdu(),calculator.getGodzinaWyjazdu(), calculator.getGodzinaPrzyjazdu()),
                    calculator.obliczKosztPodrozyPojazdem(calculator.getIloscKilometrow(), calculator.getPojazd()),
                    calculator.obliczRyczaltZaDojazdy(calculator.getIloscDobDojazd()),
                    calculator.obliczRyczaltNoclegu(calculator.getLiczbaNoclegow()),
                    calculator.obliczRoznicaDiet(calculator.obliczWysokoscDiety(calculator.getDietaPodrozy(), calculator.getDataWyjazdu(), calculator.getDataPrzyjazdu(),calculator.getGodzinaWyjazdu(), calculator.getGodzinaPrzyjazdu()), calculator.getKwotaZmniejszajacaDiety()),
                    calculator.getDietaPodrozySum(), calculator.getSumaKosztow(), userRepository.findByUsername(authentication.getName()));
            
                    
                    
                    
            calculatorRepository.save(calculatorResult);
            //System.out.println(calculatorRepository.findAll());
            //System.out.println(userRepository.findAll());
            model.addAttribute("calcResults", calculatorRepository.findOne(calculatorResult.getId()));

            return "user/sucess";
        }
    }
        @GetMapping("/form")  
        public String elo(Model model, Calculator calculator){  
            model.addAttribute("calcResults", calculatorRepository.findAll());
           // model.addAttribute("sorted", calculatorRepository.findAll())
            return "user/form";  
        }
        
       @RequestMapping("/orderByCompany")  
        public String orderByName(Model model, Calculator calculator, Authentication authentication, CalculatorResult calc){  
            model.addAttribute("calcResults", calculatorRepository.findAll(new Sort(Sort.Direction.ASC, "userId.nazwaFirmy")));
            return "user/form";  
        }
        
        @RequestMapping("/orderByDateW")  
        public String orderByDate(Model model, Calculator calculator, Authentication authentication, CalculatorResult calc){  
            model.addAttribute("calcResults", calculatorRepository.findAll(new Sort(Sort.Direction.ASC, "dataWyjazdu")));
            return "user/form";  
        }
        
        @RequestMapping("/orderByDateP")  
        public String orderByDat(Model model, Calculator calculator, Authentication authentication, CalculatorResult calc){  
            model.addAttribute("calcResults", calculatorRepository.findAll(new Sort(Sort.Direction.ASC, "dataPrzyjazdu")));
            return "user/form";  
        }
        
    @RequestMapping(value = "/elo", method = RequestMethod.POST)
    public String calcer(CalculatorResult calcResults,Model model, Calculator calculator){
        model.addAttribute("calcResults", calculatorRepository.findOne(calculator.getId()));
        return "user/elo";
    }
    
    @RequestMapping(value = "/users")
    public String showUsers(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }
    
    @GetMapping(value = "/addUser")
    public String addUser(Model model){
    	model.addAttribute("userForm", new User());
        return "admin/addUser";
    }	
    
        @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String registrationUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,
            Model model, String[] roles){
         
        userService.saveUser(userForm,roles);
        return "redirect:/users";
    }
    
    
}
