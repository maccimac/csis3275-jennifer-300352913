package com.example.jennifermargaret300352913.web;

import com.example.jennifermargaret300352913.entities.Customer;
import com.example.jennifermargaret300352913.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping(path = "/index")
    public String customers(Model model) {
        List<Customer> customers = customerRepository.findAll();
        String[] seatChar =  {"A","B","C","D","E"};
        model.addAttribute("listCustomers", customers);
        model.addAttribute("seatChars", seatChar);
        model.addAttribute("seatsRemain", 20-customers.size());
        model.addAttribute("today", new Date());

        return "customers";
    }

    @GetMapping("/delete")
    public String delete(Long id) {
        customerRepository.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping("/editCustomer")
    public String editCustomer(Model model, Long id, HttpSession session){
        session.setAttribute("info", 0);
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer==null) throw new RuntimeException("Patient does not exist");
        model.addAttribute("customer", customer);
        return "editCustomer";
    }

    @PostMapping(path = "/save")
    public String save(Model model, Customer customer, BindingResult
            bindingResult, ModelMap mm, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "formCustomer";
        } else {
            customerRepository.save(customer);
//            if (num == 2) {
//                mm.put("e", 2);
//                mm.put("a", 0);
//            } else {
//                mm.put("a", 1);
//                mm.put("e", 0);
//            }
            return "redirect:index";
        }
    }
}