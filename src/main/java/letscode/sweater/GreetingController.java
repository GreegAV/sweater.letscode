package letscode.sweater;

import letscode.sweater.domain.Message;
import letscode.sweater.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(
//            @RequestParam(name = "name", required = false) String name,
            Model model) {
//        if (name == null) name = "World!";
//        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/main")
    public String messages(Model model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag,
                      Model model) {
        System.out.println(text);
        System.out.println(tag);

        Message message = new Message(text, tag);
        messageRepo.save(message);
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("message", new Message());
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }
        model.put("messages", messages);
        return "main";
    }
    ////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/2")
    public String greeting2(
//            @RequestParam(name = "name", required = false, defaultValue = "Mustache!") String name,
                            Map<String, Object> model) {
//        model.put("name", name);
        return "greeting2";
    }

    @GetMapping("/main2")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main2";
    }

    @PostMapping("/main2")
    public String add2(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);
        messageRepo.save(message);
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main2";
    }

    @PostMapping("filter2")
    public String filter2(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }
        model.put("messages", messages);
        return "main2";
    }

}
