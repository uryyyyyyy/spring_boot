package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    
    @RequestMapping("/greetings")
    public String greetings(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", "hoge");
        return "greeting";
    }
    
    @RequestMapping("/greeting_unsafe")
	public String checkXSS(Model model){
		model.addAttribute("name", "<strong>strongTag</strong>");
		return "unsafeGreeting";
	}

}