package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
@Controller
public class HomeController {
    @Autowired
    NotesRepository NotesRepository;

    @RequestMapping("/")
    public String listNotes(Model model){
        model.addAttribute("notes", NotesRepository.findAll());
        return "list";
    }


    @GetMapping("/add")
    public String notesForm(Model model){
        model.addAttribute("note", new Notes ());
        return "notesform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Notes note,
                              BindingResult result){
        if (result.hasErrors()){
            return "notesform";
        }
        NotesRepository.save(note);
        return "redirect:/ ";

    }
    @RequestMapping ("/detail/{id}")
    public String showNotes (@PathVariable("id") long id, Model model)

    {
        model.addAttribute("note", NotesRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateNotes(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("note", NotesRepository.findById(id).get());
        return "notesform";
    }
    @RequestMapping("/delete/{id}")
    public String delNotes(@PathVariable("id") long id){
        NotesRepository.deleteById(id);

        return "redirect:/";

    }
}
