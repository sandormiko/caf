package caf.admin;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class CafAdminConfiguration extends Configuration {
    
	@NotNull
	private String dbName;

	@JsonProperty
	public String getDbName() {
		return dbName;
	}

	@JsonProperty
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	
}
