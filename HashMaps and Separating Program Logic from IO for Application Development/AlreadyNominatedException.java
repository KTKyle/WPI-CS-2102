package HW6;

public class AlreadyNominatedException extends Exception {

    /**
     * @param name the candidate who caused the exception of already being nominated
     */
    public AlreadyNominatedException (String name){
        super("Candidate: " + name + "has already been nominated");
    }
}
