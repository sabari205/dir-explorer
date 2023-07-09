package com.sabari.explorer.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.sabari.explorer.configuration.PathConfig;
import com.sabari.explorer.dto.FilesDTO;

@Service
public class ExplorerService {
	@Autowired
	private PathConfig pathConfig;

	public List<FilesDTO> getDirListing(String filePath) {
		File path = null;

		if (filePath != null) {
			path = new File(pathConfig.getHome() + File.separator + filePath);

			System.out.println(pathConfig.getHome() + File.separator + filePath);
		} else {
			path = new File(pathConfig.getHome());
		}

		File[] fileListArr = path.listFiles();

		Arrays.toString(fileListArr);

		Arrays.sort(fileListArr, (a,b) -> {
			if (a.isDirectory() && b.isDirectory()) {
				return a.getName().compareTo(b.getName());
			} else if (a.isDirectory()) {
				return -1;
			} else if (b.isDirectory()){
				return 1;
			} else {
				return a.getName().compareTo(b.getName());
			}
		});

		List<FilesDTO> fileList = new ArrayList<>();

		for (File file: fileListArr) {
			fileList.add(new FilesDTO(file.getName(), file.isDirectory()));
		}

		return fileList;
	}
}