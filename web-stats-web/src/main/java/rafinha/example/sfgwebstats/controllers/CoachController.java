package rafinha.example.sfgwebstats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/coaches")
@Controller
public class CoachController {

    @GetMapping({"", "/", "/index", "/index.html"})
    public String getCoaches(){
        return "coaches/index";
    }
}
