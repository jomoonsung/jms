package com.jms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/point/")
public class PointController {
	@GetMapping("/pointView")
	public void pointView() {

	}

	@GetMapping("/pointList")
	public void pointList() {
				
	}
	@GetMapping("/paymentList")
	public void paymentList() {
		
	}
}
