package letscode.sweater;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false) String name,
                           Model model) {
        if (name == null) name = "World!";
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/greeting2")
    public String greeting2(@RequestParam(name = "name", required = false) String name,
                            Map<String, Object> model) {
        if (name == null) name = "Mustache!";
        model.put("name", name);
        return "greeting";
    }
}
