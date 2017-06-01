package caf.admin;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

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
