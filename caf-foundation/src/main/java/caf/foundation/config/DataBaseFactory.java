package caf.foundation.config;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataBaseFactory {

	@NotNull
	private String dbName;
	
	@NotNull
	private String host;
	
	@NotNull
	private Integer port;
	

	
	@JsonProperty
	public String getDbName() {
		return dbName;
	}

	@JsonProperty
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	@JsonProperty
	public String getHost() {
		return host;
	}

	@JsonProperty
	public void setHost(String host) {
		this.host = host;
	}

	@JsonProperty
	public Integer getPort() {
		return port;
	}
	
	@JsonProperty
	public void setPort(Integer port) {
		this.port = port;
	}

	
	
}
