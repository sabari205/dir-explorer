package com.sabari.explorer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.nio.file.Paths;

import com.sabari.explorer.service.ExplorerService;
import com.sabari.explorer.dto.FilesDTO;
import com.sabari.explorer.dto.FileContentDTO;

@Controller
public class ExplorerController {

	@Autowired
	private ExplorerService explorerService;

	@GetMapping("/**")
	public String getIndexPage(HttpServletRequest request, Model model) {
		String pathToFile = request.getRequestURI();

		List<FilesDTO> fileList = explorerService.getDirListing(pathToFile);

		model.addAttribute("fileList", fileList);

		model.addAttribute("pwd", pathToFile);
		if (!pathToFile.equals("/")) {
			model.addAttribute("prevDir", Paths.get(pathToFile).getParent());
		}
		
		return "index";
	}

	@GetMapping("/getFileContents/**")
	public String getFileContents(HttpServletRequest request, Model model) {
		String pathToFile = request.getRequestURI();
		FileContentDTO fileContents = explorerService.getFileContents(pathToFile);

		model.addAttribute("fileContents", fileContents);
		model.addAttribute("prevDir", Paths.get(pathToFile.substring(pathToFile.indexOf("/", 1))).getParent());
		
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

	@GetMapping("/favicon.ico")
	@ResponseBody
	public void noFavIcon() {

	}
}