package caf.foundation.config;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProducerFactory {

	@NotNull
	private String enableAutoCommit;
	
	@NotNull
	private String bootStrapServers;
	
	@NotNull
	private String autoCommitInterval;

	@JsonProperty("enable.auto.commit")
	public String getEnableAutoCommit() {
		return enableAutoCommit;
	}

	@JsonProperty("enable.auto.commit")
	public void setEnableAutoCommit(String enableAutoCommit) {
		this.enableAutoCommit = enableAutoCommit;
	}

	@JsonProperty("bootstrap.servers")
	public String getBootStrapServers() {
		return bootStrapServers;
	}

	@JsonProperty("bootstrap.servers")
	public void setBootStrapServers(String bootStrapServers) {
		this.bootStrapServers = bootStrapServers;
	}

	@JsonProperty("auto.commit.interval.ms")
	public String getAutoCommitInterval() {
		return autoCommitInterval;
	}

	@JsonProperty("auto.commit.interval.ms")
	public void setAutoCommitInterval(String autoCommitInterval) {
		this.autoCommitInterval = autoCommitInterval;
	}
	
}
