package caf.foundation.mongo;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.MongoClient;

import caf.foundation.config.ApplicationConfiguration;

public class MongoModule extends AbstractModule {

	@Provides
	public Datastore provideDataStore(MongoClient client, 
			Morphia morphia, ApplicationConfiguration config) {
		return morphia.createDatastore(client, config.getDb().getDbName());

	}

	@Override
	protected void configure() {
		bind(MongoClient.class).toProvider(MongoClientProvider.class).in(Singleton.class);
		bind(Morphia.class).toProvider(MorphiaProvider.class).in(Singleton.class);

	}
}
