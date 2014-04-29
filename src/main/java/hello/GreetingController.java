package hello;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
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
    
    @RequestMapping(value = "/greeting_unsafe")
	public String checkXSS(Model model, HttpServletRequest request){
		model.addAttribute("name", "<strong>"+ request.getRemoteUser() +"</strong>");
		return "unsafeGreeting";
	}
    
    @RequestMapping(value="/download")
	public void download(HttpServletResponse response) throws Exception {
		Resource file = new FileSystemResource("src/main/webapp/tmp/sample.txt");
		System.out.println(file.toString());
		response.setContentType("text/plain");
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
    
    
    @Autowired
	private DataSource dataSource;
	
	@Autowired
	private PlatformTransactionManager platformTransactionManager;
	
	@Transactional
	@RequestMapping("/moke")
	public String moke4ing(Model model) throws ClassNotFoundException, SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//データの登録
		jdbcTemplate.execute("drop table customers if exists");
		jdbcTemplate.execute("create table customers(" +
				"id serial, first_name varchar(255), last_name varchar(255))");

		String[] names = "John Woo;Jeff Dean;Josh Bloch;Josh Long".split(";");
		for (String fullname : names) {
			String[] name = fullname.split(" ");
			//System.out.printf("Inserting customer record for %s %s\n", name[0], name[1]);
			jdbcTemplate.execute(
					"INSERT INTO customers(first_name,last_name) values('" + name[0] + "','" + name[1] + "')");
		}

		//登録したデータの検索
		List<Customer> list = jdbcTemplate.query("select * from customers", 
				new RowMapper<Customer>() {
			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Customer(rs.getLong("id"), rs.getString("first_name"),
						rs.getString("last_name"));
			}
		});

		model.addAttribute("customers", list);
		model.addAttribute("name", list.get(0).toString());

		return "thymeleaf";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}

}