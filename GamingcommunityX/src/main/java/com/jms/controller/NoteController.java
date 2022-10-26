package com.jms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/note/")
public class NoteController {

	@GetMapping("/noteList")
	public void noteView() {
		
	}
	@GetMapping("/sentnote")
	public void sentnoteView() {
		
	}

	@GetMapping("/savenote")
	public void savenoteView() {
		
	}


}
