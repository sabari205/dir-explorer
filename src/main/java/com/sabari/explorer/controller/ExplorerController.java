package com.sabari.explorer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.nio.file.Paths;

import com.sabari.explorer.service.ExplorerService;
import com.sabari.explorer.dto.FilesDTO;

@Controller
public class ExplorerController {

	@Autowired
	private ExplorerService explorerService;

	@GetMapping("/")
	public String getIndexPage(@RequestParam(required=false) String pathToFile, Model model) {
		List<FilesDTO> fileList = explorerService.getDirListing(pathToFile);

		model.addAttribute("fileList", fileList);

		if (pathToFile == null) {
			model.addAttribute("pwd", "/");
		} else {
			model.addAttribute("pwd", pathToFile);
			model.addAttribute("prevDir", Paths.get(pathToFile).getParent());
		}
		
		return "index";
	}

	@GetMapping("/getFileContents")
	public String getFileContents(@RequestParam String pathToFile, Model model) {
		String fileContents = explorerService.getFileContents(pathToFile);

		model.addAttribute("fileContents", fileContents);
		model.addAttribute("prevDir", Paths.get(pathToFile).getParent());
		
		return "fileViewer";
	}

	@GetMapping("/dynamicUrlTest/**")
	public String getIndexPage(HttpServletRequest request) {
		System.out.println(request.getRequestURI());
		System.out.println(request.getPathInfo());
		System.out.println(request.getRequestURL());
		System.out.println(request.getRequestURL());
		return "sample";
	}
}