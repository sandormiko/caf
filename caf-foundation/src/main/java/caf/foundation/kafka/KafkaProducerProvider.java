package caf.foundation.kafka;

import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import com.google.inject.Provider;

import caf.foundation.config.ApplicationConfiguration;
import caf.foundation.config.ProducerFactory;


@Singleton
public class KafkaProducerProvider implements Provider<Producer<String,byte[]>>{

	private final ProducerFactory producerConfig;
	
	@Inject
	public KafkaProducerProvider(ApplicationConfiguration appConfig){
		this.producerConfig = appConfig.getProducer();
	}
	
	@Override
	public Producer<String, byte[]> get() {
		Properties props = new Properties();
        props.put("bootstrap.servers", producerConfig.getBootStrapServers());
       
        props.put("enable.auto.commit", producerConfig.getEnableAutoCommit().toString());
        props.put("auto.commit.interval.ms", producerConfig.getAutoCommitInterval());
        
        props.put("key.serializer", org.apache.kafka.common.serialization.StringSerializer.class.getName());
        props.put("value.serializer", org.apache.kafka.common.serialization.ByteArraySerializer.class.getName());
        

        Producer<String,byte[]> producer = new KafkaProducer<String,byte[]>(props);
        
		return producer;
	}

}
