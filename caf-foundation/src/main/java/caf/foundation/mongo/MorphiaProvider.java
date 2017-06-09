package caf.foundation.mongo;

import org.mongodb.morphia.Morphia;

import com.google.inject.Provider;

public class MorphiaProvider implements Provider<Morphia>{

	@Override
	public Morphia get() {
		return new Morphia();
	}

}
