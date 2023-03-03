-:Simple Queue Service (distributed P2P messaging system on AWS):-
----------------------------
Create SQS Queue in AWS
----------------------------
Steps:
1. Search Simple Queue Services on AWS
2. Click on create queue.
3. Use Standard Queue (Our application is using standard Queue)
4. give queue name
5. Configure below properties according to requirement. 
  Visibility timeout-Visibility timeout sets the length of time that a message received from a queue (by one consumer)
                      will not be visible to the other message consumers.
   Message retention period-The message retention period is the amount of time that Amazon SQS retains a message that 
                      does not get deleted. Amazon SQS automatically deletes messages that have been in a queue for more 
                      than the maximum message retention period. The default retention period is 4 days. The retention
                      period has a range of 60 seconds to 1,209,600 seconds (14 days).
   Delivery delay- If your consumers need additional time to process messages, you can delay each new message coming to the queue. 
                      The delivery delay is the amount of time to delay the first delivery of each message added to the queue. 
                      Any messages that you send to the queue remain invisible to consumers for the duration of the delay period. 
                      The default (minimum) delay for a queue is 0 seconds. The maximum is 15 minutes.
   Maximum message size- You can set the maximum message size for your queue. The smallest supported message size is 1 byte (1 character).
                      The largest size is 262,144 bytes (256 KB).
   Receive message wait time- The receive message wait time is the maximum amount of time that polling will wait for messages to become available to receive. 
                      The minimum value is zero seconds and the maximum value is 20 seconds.
  
6. (This step is optional)Have a dead letter queue to keep track of non consumed messages in queue.
   Dead-letter queues- If a message can't be consumed successfully, you can send it to a dead-letter queue (DLQ). Dead-letter queues let you isolate problematic messages to determine why they are failing.   
7. Click on create queue.


------------------------------
Confugure spring boot app:

8. change configuration according to your queue in application.yml
9. Run Spring boot app
10.send request
11.Observe the console that consumer will pull message from sqs queue and print it.
