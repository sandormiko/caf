package caf.foundation.config;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class ApplicationConfiguration extends Configuration {
    
	@Valid
	@NotNull
	private DataBaseFactory db = new DataBaseFactory();

	@JsonProperty("database")
	public DataBaseFactory getDb() {
		return db;
	}

	@JsonProperty("database")
	public void setDb(DataBaseFactory db) {
		this.db = db;
	}
	
}
