package caf.foundation.mongo;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.mongodb.MongoClient;

import io.dropwizard.lifecycle.Managed;

@Singleton
public class ManagedMongoClient implements Managed {

	private final MongoClient mClient;

	@Inject
	public ManagedMongoClient(MongoClient mClient) {
		this.mClient = mClient;
	}

	@Override
	public void start() throws Exception {

	}

	@Override
	public void stop() throws Exception {
		mClient.close();

	}

}
