package com.sabari.explorer.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.sabari.explorer.configuration.PathConfig;

@Service
public class ExplorerService {
	@Autowired
	private PathConfig pathConfig;

	public void getDirListing() {
		System.out.println(pathConfig.toString());
	}
}