import java.util.*;


//Passenger class
class Passenger {
    private String name;
    private int passengerNumber;
    private PassengerType type;
    private double balance;

    
    public Passenger(String name, int passengerNumber, PassengerType type, double balance) {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.type = type;
        this.balance = balance;
    }

    
    public String getName() {
        return name;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public PassengerType getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    // Function to sign up as well as checks the type of passenger
    public boolean signUpForActivity(Activity activity) {
        if (type == PassengerType.STANDARD) {
            if (balance >= activity.getCost()) {
                balance -= activity.getCost();
                return true;// For standrad
            } else {
                return false;
            }
        } else if (type == PassengerType.GOLD) { // checks for gold
            double discountedCost = activity.getCost() * 0.9; // 10% discount
            if (balance >= discountedCost) {
                balance -= discountedCost;
                return true;
            } else {
                return false;
            }
        } else { // If it reach to here then the passenger is Premium holder
            return true; // The Premium passenger can sign up for activities for free 
        }
    }
}

//Activity class
class Activity {
    private String name;
    private String description;
    private double cost;
    private int capacity;

    //constructor
    public Activity(String name, String description, double cost, int capacity) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
    }

    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public int getCapacity() {
        return capacity;
    }
}

//Destination class
class Destination {
    private String name;
    private List<Activity> activities;

    // constructor
    public Destination(String name) {
        this.name = name;
        activities = new ArrayList<>();
    }

    // getter
    public String getName() {
        return name;
    }

    // method to add activity
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    // getter for activities
    public List<Activity> getActivities() {
        return activities;
    }
}

//TravelPackage class
class TravelPackage {
    private String name;
    private int passengerCapacity;
    private List<Destination> itinerary;
    private List<Passenger> passengers;

    // constructor
    public TravelPackage(String name, int passengerCapacity) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        itinerary = new ArrayList<>();
        passengers = new ArrayList<>();
    }

    // getter
    public String getName() {
        return name;
    }

    // method to add destination
    public void addDestination(Destination destination) {
        itinerary.add(destination);
    }

    // getter for itinerary
    public List<Destination> getItinerary() {
        return itinerary;
    }

    // method to add passenger if there is a vacancy in the package
    public boolean addPassenger(Passenger passenger) {
        if (passengers.size() < passengerCapacity) {
            passengers.add(passenger);
            return true;
        }
        return false;
    }

    // Getter for passengers
    public List<Passenger> getPassengers() {
        return passengers;
    }
}

//PassengerType --> Standard or Gold or Premium
enum PassengerType {
    STANDARD, GOLD, PREMIUM
}

//Main class
public class Nymble {
    public static void main(String[] args) {
        //activities
        Activity activity1 = new Activity("Hiking", "Enjoy hiking in the mountains", 50.0, 10);
        Activity activity2 = new Activity("Scuba", "Explore the underwater world", 100.0, 8);
       Activity activity3 = new Activity("Rafting", "Explore the tides of beach", 200.0, 8);

        
        //destinations
        Destination destination1 = new Destination("Mountain Resort");
        destination1.addActivity(activity1);
        Destination destination2 = new Destination("Beach Resort");
        destination2.addActivity(activity2);
        Destination destination3 = new Destination("Gokarna Beach");
        destination3.addActivity(activity3);
        
        //travel package
        TravelPackage travelPackage = new TravelPackage("Adventure Package", 20);
        travelPackage.addDestination(destination1);
        travelPackage.addDestination(destination2);
        travelPackage.addDestination(destination3);

        //passengers
        Passenger passenger1 = new Passenger("Vishnu", 1, PassengerType.STANDARD, 200.0);
        Passenger passenger2 = new Passenger("Vardhan", 2, PassengerType.GOLD, 300.0);
        Passenger passenger3 = new Passenger("Reddy", 3, PassengerType.PREMIUM, 0.0);
        Passenger passenger4 = new Passenger("Nymble", 4, PassengerType.PREMIUM, 0.0);
    
        // Adding passengers to the travel package
        travelPackage.addPassenger(passenger1);
        travelPackage.addPassenger(passenger2);
        travelPackage.addPassenger(passenger3);
        travelPackage.addPassenger(passenger4);

        //Checks and prints the destination, activity and the name of the passenger 
        for (Destination destination : travelPackage.getItinerary()) {
            System.out.println("Destination: " + destination.getName());
            for (Activity activity : destination.getActivities()) {
                System.out.println("Activity: " + activity.getName());
                for (Passenger passenger : travelPackage.getPassengers()) {
                    boolean signedUp = passenger.signUpForActivity(activity);
                    if (signedUp) {
                        System.out.println(passenger.getName() + " signed up for " + activity.getName());
                    } else {
                        System.out.println(passenger.getName() + " could not sign up for " + activity.getName() + " due to insufficient balance");
                    }
                }
            }
            System.out.println();
        }
    }
}
