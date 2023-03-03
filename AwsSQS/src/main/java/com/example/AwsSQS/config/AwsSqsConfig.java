package com.example.AwsSQS.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

@Configuration
@ComponentScan(excludeFilters  = {@ComponentScan.Filter(
              type = FilterType.ASSIGNABLE_TYPE, classes = {ContextStackAutoConfiguration.class})})
public class AwsSqsConfig {
	
	@Value("${cloud.aws.region.static}")
	private String reagion;
	
	@Value("${cloud.aws.credentials.access-key}")
	private String accesskey;
	
	@Value("${cloud.aws.credentials.secret-key}")
	private String secretkey;
	
	@Bean
	public QueueMessagingTemplate  queueMessagingTemplate() {
		return new QueueMessagingTemplate(amazonSqsAsync());
	}

	@Bean
	@Primary
	public AmazonSQSAsync amazonSqsAsync() {
		return AmazonSQSAsyncClientBuilder.standard().withRegion(Regions.US_WEST_2)
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accesskey, secretkey)))
				.build();
	}
}
