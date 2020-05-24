package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;


/**
 * tomcat配置
 * @author: dgm 
 * @desc:
 * @date: 
 * 
 */
@Component
@ConfigurationProperties(prefix="server.tomcat")
public class TomcatConfig {

	private static final Logger logger = LoggerFactory.getLogger(TomcatConfig.class);  

    private String port;
    private String acceptorThreadCount;
    private String minSpareThreads;
    private String maxSpareThreads;
    private String maxThreads;
    private String maxConnections;
    private String protocol;
    private String redirectPort;
    private String compression;
    private String connectionTimeout;

    private String location;
    private DataSize MaxFileSize;
    private DataSize MaxRequestSize;
    private DataSize fileSizeThreshold;
    
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getAcceptorThreadCount() {
		return acceptorThreadCount;
	}
	public void setAcceptorThreadCount(String acceptorThreadCount) {
		this.acceptorThreadCount = acceptorThreadCount;
	}
	public String getMinSpareThreads() {
		return minSpareThreads;
	}
	public void setMinSpareThreads(String minSpareThreads) {
		this.minSpareThreads = minSpareThreads;
	}
	public String getMaxSpareThreads() {
		return maxSpareThreads;
	}
	public void setMaxSpareThreads(String maxSpareThreads) {
		this.maxSpareThreads = maxSpareThreads;
	}
	public String getMaxThreads() {
		return maxThreads;
	}
	public void setMaxThreads(String maxThreads) {
		this.maxThreads = maxThreads;
	}
	public String getMaxConnections() {
		return maxConnections;
	}
	public void setMaxConnections(String maxConnections) {
		this.maxConnections = maxConnections;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getRedirectPort() {
		return redirectPort;
	}
	public void setRedirectPort(String redirectPort) {
		this.redirectPort = redirectPort;
	}
	public String getCompression() {
		return compression;
	}
	public void setCompression(String compression) {
		this.compression = compression;
	}
	public String getConnectionTimeout() {
		return connectionTimeout;
	}
	public void setConnectionTimeout(String connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public DataSize getMaxFileSize() {
		return MaxFileSize;
	}
	public void setMaxFileSize(DataSize maxFileSize) {
		MaxFileSize = maxFileSize;
	}
	public DataSize getMaxRequestSize() {
		return MaxRequestSize;
	}
	public void setMaxRequestSize(DataSize maxRequestSize) {
		MaxRequestSize = maxRequestSize;
	}
	public DataSize getFileSizeThreshold() {
		return fileSizeThreshold;
	}
	public void setFileSizeThreshold(DataSize fileSizeThreshold) {
		this.fileSizeThreshold = fileSizeThreshold;
	}
}
