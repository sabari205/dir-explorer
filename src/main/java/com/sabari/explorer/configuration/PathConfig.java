package com.sabari.explorer.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="explorer.path")
@Configuration("pathConfig")
public class PathConfig {
	private String home;

	public PathConfig() {

	}

	public PathConfig(String home) {
		this.home = home;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String toString() {
		return String.format("Home path: %s", this.home);
	}
}