package com.sabari.explorer.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.ext.task.list.items.TaskListItemsExtension;
import org.commonmark.Extension;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.nio.file.Paths;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sabari.explorer.configuration.PathConfig;
import com.sabari.explorer.dto.FilesDTO;
import com.sabari.explorer.dto.FileContentDTO;

@Service
public class ExplorerService {
	@Autowired
	private PathConfig pathConfig;

	public static final String LAST_MODIFIED_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.S";

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

			fileList.add(new FilesDTO(file.getName(), getFormattedTime(file.lastModified()), file.isDirectory(), pwd));
		}

		return fileList;
	}

	public FileContentDTO getFileContents (String filePath) {
		File file = new File(pathConfig.getHome() + File.separator + filePath);
		FileContentDTO fileContent = new FileContentDTO();

		// Check if file exist else throw error
		// method to check whether the file is binary encoded or an ASCII file

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

		fileContent.setFileName(file.getName());
		fileContent.setContent(contents.toString());
		fileContent.setContentHTML(getHTMLContent(contents.toString()));
		fileContent.setIsAsciiFile(true);

		return fileContent;
	}

	public String getHTMLContent (String fileContent) {

		List<Extension> extensions = Arrays.asList(TaskListItemsExtension.create());

		Parser parser = Parser.builder().extensions(extensions).build();
		Node document = parser.parse(fileContent);
		HtmlRenderer renderer = HtmlRenderer.builder().extensions(extensions).build();

		return renderer.render(document);

	}

	public String getFormattedTime(long timestamp) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(LAST_MODIFIED_DATE_FORMAT);
		return dateFormat.format(new Date(timestamp));
	}
}
