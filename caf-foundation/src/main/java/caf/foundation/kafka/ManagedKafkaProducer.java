package caf.foundation.kafka;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.kafka.clients.producer.Producer;

import io.dropwizard.lifecycle.Managed;

@Singleton
public class ManagedKafkaProducer implements Managed {

	private Producer producer;

	@Inject
	public ManagedKafkaProducer(Producer producer) {
		this.producer = producer;
	}

	@Override
	public void start() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() throws Exception {
		producer.close();

	}

}
