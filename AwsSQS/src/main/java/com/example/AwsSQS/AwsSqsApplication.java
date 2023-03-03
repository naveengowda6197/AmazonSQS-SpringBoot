package com.example.AwsSQS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.s3.internal.eventstreaming.Message;
import com.example.AwsSQS.Model.SurveyLaunch;
//import com.example.Model.SurveyLaunch;
//import com.example.amazonsqs.Model.SurveyLaunch;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@SpringBootApplication(exclude = ContextStackAutoConfiguration.class)

@RestController
public class AwsSqsApplication {
		@Autowired
		private QueueMessagingTemplate queueMessagingTemplate;
	
		private String endpoint="awsendpoint";
		
		@GetMapping("/home")
		public String getname() {
			return "success";
		}
		@RequestMapping("/aws")
		public String sendmessage(@RequestBody SurveyLaunch name) {
			String obj=new Gson().toJson(name);
			queueMessagingTemplate.send(endpoint,MessageBuilder.withPayload(obj).build());
			return "Message sent to SQS Queue";
		}
		
		@SqsListener("test-queue")
		public void recieve(String name) throws JsonMappingException, JsonProcessingException {
			SurveyLaunch surveyLaunch=new Gson().fromJson(name, SurveyLaunch.class);
			System.out.println(surveyLaunch.getData());
			System.out.println(name);
		}
	
		public static void main(String[] args) {
			SpringApplication.run(AwsSqsApplication.class, args);
		}
}
