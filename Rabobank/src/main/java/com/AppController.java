package com;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.AppService;
import com.Record;

@Controller
public class AppController {

	Record rec = new Record();

	AppService service = new AppService();

	@RequestMapping("/")
	public String viewHome(Model mm) {
		return "welcome";
	}

	@RequestMapping("/result")
	public String upload(@RequestParam("file") MultipartFile file, Model mm) {

			String name=file.getOriginalFilename();
			
			if (!file.isEmpty()) {
			    try {
			    	
			    	//saving the file to server
			        byte[] bytes = file.getBytes();
			        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
			        stream.write(bytes);
			        stream.close();
			        
					//converting to java objects
					List<Record> listOfRecords = service.toJavaObject(new File(name));

//					// printing arraylist of all input records
//					for (Record r : listOfRecords) {
//						System.out.println(r.toString());
//					}
					
					
					//validate and retrieve failed records
					List<Record> print=service.validate(listOfRecords);
//					System.out.println("Failed records: ");
//					for(Record e:print){
//						System.out.println(e.toString());
//					}

					//sending the failedrecords which doesnt satisfy the given condition to jsp page in a variable "failedrecords" 
					mm.addAttribute("failedrecords",print);
					
			    } catch (Exception e) {
			        
			    	mm.addAttribute("msg",   "You failed to upload " + name + " => " + e.getMessage());
			    }
			} else {
				mm.addAttribute("msg",    "You failed to upload " + name + " because the file was empty.");
			 }

		return "result";
	}

}
