package caf.foundation.mongo;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.mongodb.MongoClient;

public class MongoModule extends AbstractModule {

	private final String dbName;

	public MongoModule(String dbName) {
		this.dbName = dbName;
	}

	@Provides
	public Datastore provideDataStore() {
		Morphia morphia = new Morphia();
		return morphia.createDatastore(new MongoClient(), dbName);

	}

	@Override
	protected void configure() {
		// TODO Auto-generated method stub

	}
}
