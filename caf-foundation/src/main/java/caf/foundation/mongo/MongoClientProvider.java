package caf.foundation.mongo;

import javax.inject.Inject;

import com.google.inject.Provider;
import com.mongodb.MongoClient;

import caf.foundation.config.ApplicationConfiguration;
import caf.foundation.config.DataBaseFactory;

public class MongoClientProvider implements Provider<MongoClient>{

	private final ApplicationConfiguration config;
	
	@Inject
	public MongoClientProvider(ApplicationConfiguration config){
		this.config = config;
	}
	@Override
	public MongoClient get() {
		DataBaseFactory db = config.getDb();
		return new MongoClient(db.getHost(),db.getPort());
	}

}
