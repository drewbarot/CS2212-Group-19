package orchestration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;
import events.EventType;
import pubSubServer.*;
import publishers.AbstractPublisher;
import publishers.PublisherType;
import states.subscriber.StateName;
import strategies.publisher.StrategyName;
import subscribers.AbstractSubscriber;
import subscribers.SubscriberType;

public class Orchestration {

    private static AdministrationServer server = AdministrationServer.getInstance();

    public static void main(String args[]) {

        Map<Integer, AbstractPublisher> listOfPublishers;
        Map<Integer, AbstractSubscriber> listOfSubscribers;
        Orchestration driver = new Orchestration();
        BufferedReader br;
        int errors = 0;

        try {

            listOfPublishers = driver.initializePublishers(); // initialize the publisher list
            listOfSubscribers = driver.initializeSubscribers(); //initialize the subscriber list

            System.out.println("Program Successfully Initialized!\n\n");

           br = new BufferedReader(new FileReader(new File("src/driverFile.txt")));
           String input, command;
           StringTokenizer st;

           while (br.ready()) {
               input = br.readLine().trim();
               if (input.isEmpty()) continue;
               st = new StringTokenizer(input);
               command = st.nextToken();
               System.out.println();

               switch (command) {
                   case "PUB": {
                       if (st.countTokens() == 1) {
                           String publisherID = st.nextToken();
                           AbstractPublisher publisher = listOfPublishers.get(Integer.parseInt(publisherID));
                           if (publisher != null) {
                               server.publish(publisher);
                           }
                           else {
                               System.out.println("Publisher ID=" + publisherID + " doesn't exist");
                           }
                       }
                       else if (st.countTokens() == 4) {
                           String publisherID, eventType, eventMessageHeader, eventMessageBody;
                           publisherID = st.nextToken();
                           eventType = st.nextToken();
                           eventMessageHeader = st.nextToken();
                           eventMessageBody = st.nextToken();
                           AbstractPublisher publisher = listOfPublishers.get(Integer.parseInt(publisherID));
                           if (publisher != null) {
                               server.publish(publisher, server.newEvent(EventType.values()[Integer.parseInt(eventType)],
                                       publisher.getPublisherID(), server.newEventMessage(eventMessageHeader, eventMessageBody)));
                           }
                           else {
                               System.out.println("Publisher ID=" + publisherID + " doesn't exist");
                           }
                       }
                       else {
                           errors++; //syntax was wrong so keep track of errors
                       }
                       break;
                   }
                   case "SUB": {
                       if (st.countTokens() == 2) {
                           String subscriberID, channelName;
                           subscriberID = st.nextToken();
                           channelName = st.nextToken();
                           AbstractSubscriber subscriber = listOfSubscribers.get(Integer.parseInt(subscriberID));
                           if (subscriber != null) {
                               server.subscribe(subscriber, channelName);
                           }
                           else {
                               System.out.println("Subscriber ID=" + subscriberID + " doesn't exist");
                           }
                       }
                       else {
                           errors++; //track the syntax error in the file
                       }
                       break;
                   }
                   case "BLOCK": { //the precondition is that the channel already exists
                       if (st.countTokens() == 2) {
                           String subscriberID, channelName;
                           subscriberID = st.nextToken();
                           channelName = st.nextToken();
                           AbstractSubscriber subscriber = listOfSubscribers.get(Integer.parseInt(subscriberID));
                           if (subscriber != null) {
                               server.blockSubscriber(subscriber, channelName);
                           }
                           else {
                               System.out.println("Subscriber ID=" + subscriberID + " doesn't exist");
                           }
                       }
                       else {
                           errors++; //track syntax error
                       }
                       break;
                   }
                   case "UNBLOCK": { //the precondition is that the channel already exists
                       if (st.countTokens() == 2) {
                           String subscriberID, channelName;
                           subscriberID = st.nextToken();
                           channelName = st.nextToken();
                           AbstractSubscriber subscriber = listOfSubscribers.get(Integer.parseInt(subscriberID));
                           if (subscriberID != null) {
                               server.unblockSubscriber(subscriber, channelName);
                           }
                           else {
                               System.out.println("Subscriber ID=" + subscriberID + " doesn't exist");
                           }
                       }
                       else {
                           errors++; //track syntax error
                       }
                       break;
                   }
                   default:
                       errors++; //no valid operation was requested so report error and loop again
               }

           }

            br.close();

        }
        catch (IOException e) {
            System.out.println("Error Reading File");
        }

        System.out.println("\n" + errors + " errors in driverFile.txt\n");

    }


    /**
     * read the file Strategies.str to create a list of publishers with their strategies
     * @return   the Map to the main function
     */
    private Map<Integer, AbstractPublisher> initializePublishers() {

        System.out.println("Initializing Publishers:");

        Map<Integer, AbstractPublisher> listOfPublishers = new HashMap<>();
        int fileFormattingErrors = 0;

        try {

            BufferedReader br = new BufferedReader(new FileReader(new File("src/Strategies.str")));
            String input, publisherType, strategyType;
            StringTokenizer st;

            while(br.ready()) {
                input = br.readLine();
                st = new StringTokenizer(input);
                if (st.countTokens() == 2) { //make sure the file is formatted correctly
                    publisherType = st.nextToken();
                    strategyType = st.nextToken();
                    AbstractPublisher publisher = server.createPublisher(PublisherType.values()[Integer.parseInt(publisherType)],
                            StrategyName.values()[Integer.parseInt(strategyType)]);
                    listOfPublishers.put(publisher.getPublisherID(), publisher);
                }
                else {
                    fileFormattingErrors++; //track number of lines skipped over
                }
            }

            br.close();

        } catch(IOException ioe) {
            System.out.println("Error Initializing Publishers\n\n");
        }

        System.out.println("\n" + fileFormattingErrors + " errors in Strategies.str\n");

        return listOfPublishers;
    }


    /**
     * read the file States.sts to create a list of subscribers with their current states
     * @return    the Map to the main function
     */
    private Map<Integer, AbstractSubscriber> initializeSubscribers() {

        System.out.println("Initializing Subscribers:");

        Map<Integer, AbstractSubscriber> listOfSubscribers = new HashMap<>();
        int fileFormattingErrors = 0;

        try {

            BufferedReader br = new BufferedReader(new FileReader(new File("src/States.sts")));
            String input, subscriberType, stateName;
            StringTokenizer st;

            while (br.ready()) {
                input = br.readLine();
                st = new StringTokenizer(input);
                if (st.countTokens() == 2) {
                    subscriberType = st.nextToken();
                    stateName = st.nextToken();
                    AbstractSubscriber subscriber = server.createSubscriber(SubscriberType.values()[Integer.parseInt(subscriberType)],
                            StateName.values()[Integer.parseInt(stateName)]);
                    listOfSubscribers.put(subscriber.getSubscriberID(), subscriber);
                    server.subscribe(subscriber, "Main"); //everyone subscribes to the main channel automatically
                }
                else {
                    fileFormattingErrors++; //track number of invalid input lines in the files
                }
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error Initializing Subscribers\n\n");
        }

        System.out.println("\n" + fileFormattingErrors + " errors in States.sts\n");

        return listOfSubscribers;
    }

}
