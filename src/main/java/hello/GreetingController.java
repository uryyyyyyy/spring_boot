package hello;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GreetingController {

    @RequestMapping(value = "/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    
    @RequestMapping(value = "/greetings")
    public String greetings(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", "hoge");
        return "greeting";
    }
    
    @RequestMapping(value = "/greeting_unsafe")
	public String checkXSS(Model model){
		model.addAttribute("name", "<strong>strongTag</strong>");
		return "unsafeGreeting";
	}
    
    @RequestMapping(value="/download")
	public void download(HttpServletResponse response) throws Exception {
		Resource file = new FileSystemResource("src/main/webapp/tmp/sample.txt");
		System.out.println(file.toString());
		response.setContentType("text/plain");
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println((int)FileUtils.sizeOf(file.getFile()));
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		response.setContentLength((int)FileUtils.sizeOf(file.getFile()));
		response.setHeader("Content-Disposition","attachment; filename=\"" + file.getFile().getName() +"\"");
		FileCopyUtils.copy(file.getInputStream(), response.getOutputStream());
	}
    
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String mokemoke(Model model){
		return "attachment";
	}
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST) 
	public String upload(@RequestParam("myFile") MultipartFile myFile, Model model) throws IOException {
		System.out.println(myFile.toString());
		File saveFile = new File("src/main/webapp/tmp/" + myFile.getOriginalFilename());
		FileUtils.copyInputStreamToFile(myFile.getInputStream(), saveFile);
		model.addAttribute("filepath", "tmp/" + myFile.getOriginalFilename());
		return "attachment";
	} 

}