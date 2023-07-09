package com.sabari.explorer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping("/{pathToFile}")
	public String getIndexPage(@PathVariable String pathToFile) {
		System.out.println(pathToFile);
		return "sample";
	}
}