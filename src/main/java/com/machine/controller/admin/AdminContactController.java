package com.machine.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.machine.model.Contact;
import com.machine.service.ContactService;

@Controller
public class AdminContactController {

	@Autowired
	private ContactService contactService;

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String listContact(final Model modelMap) {
		Contact contact = contactService.getContact();
		String[] images = contact.getIsoimage().split(",");

		modelMap.addAttribute("contact", contact);
		modelMap.addAttribute("images", images);
		return "contactPage";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String redirectToHomePage(@ModelAttribute("contact") Contact contact) {
		try {
			contactService.updateContact(contact);
		} catch (Exception e) {
			System.out.println(e.toString());
			return "redirect:/contact";
		}
		return "redirect:/products";
	}

	@RequestMapping(value = "/admin/uploadFile", method = RequestMethod.POST)
    public @ResponseBody
    String uploadMultipleFileHandler(@RequestParam("name") String[] names,
            @RequestParam("file") MultipartFile[] files)  {

		String message = "";
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String name = names[i];
            try {
                byte[] bytes = file.getBytes();
 
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "resources"  + File.separator + "images");
                if (!dir.exists())
                    dir.mkdirs();
 
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                message = message + "You successfully uploaded file=" + name
                        + "<br />";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        }
        return message;
	}

}
