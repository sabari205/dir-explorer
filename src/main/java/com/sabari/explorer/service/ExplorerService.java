package com.sabari.explorer.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.nio.file.Paths;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import com.sabari.explorer.configuration.PathConfig;
import com.sabari.explorer.dto.FilesDTO;

@Service
public class ExplorerService {
	@Autowired
	private PathConfig pathConfig;

	public List<FilesDTO> getDirListing(String filePath) {
		File path = null;

		if (filePath.equals("/")) {
			path = new File(pathConfig.getHome());
		} else {
			path = new File(pathConfig.getHome() + File.separator + filePath);
		}

		File[] fileListArr = path.listFiles();

		// System.out.println(filePath + Arrays.toString(fileListArr));

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

		// System.out.println(Arrays.toString(fileListArr));

		List<FilesDTO> fileList = new ArrayList<>();

		for (File file: fileListArr) {
			String pwd = Paths.get(
				filePath + File.separator + file.getName()
			).toAbsolutePath().toString();

			fileList.add(new FilesDTO(file.getName(), file.isDirectory(), pwd));
		}

		return fileList;
	}

	public String getFileContents (String filePath) {
		File file = new File(pathConfig.getHome() + File.separator + filePath.substring(filePath.indexOf("/", 1)));

		StringBuilder contents = new StringBuilder();

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				contents.append(line + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return contents.toString();
	}
}