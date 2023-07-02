package com.sabari.explorer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.sabari.explorer.service.ExplorerService;

@Controller
public class ExplorerController {

	@Autowired
	private ExplorerService explorerService;

	@GetMapping("/")
	public String getIndexPage() {
		explorerService.getDirListing();
		
		return "index";
	}
}