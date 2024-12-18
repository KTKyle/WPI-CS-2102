package HW6;

public class CandidateNotNominatedException extends Exception {

    private final String correctCandidate;


    /**
     * Constructs a new CandidateNotNominatedException with the specified candidate name
     * @param candidate the name of the candidate that is not nominated
     */
    public CandidateNotNominatedException(String candidate) {
        super("Candidate: " + candidate + "has not been nominated.");
        this.correctCandidate = candidate;
    }

    /**
     * Retrieves the name of the candidate that caused this exception
     * @return the candidate's name
     */
    public String getCandidate(){
        return correctCandidate;
    }
}
