package caf.foundation.kafka;

import org.apache.kafka.clients.producer.Producer;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class KafkaModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Producer.class).toProvider(KafkaProducerProvider.class).in(Singleton.class);
	}

}
