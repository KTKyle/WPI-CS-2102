package HW6;

public class MoreThanOnceException extends Exception {

    /**
     * @param candidate name of candidate who caused the exception, voted more than once
     */
    public MoreThanOnceException(String candidate) {
        super("Candidate: " + candidate + "has already been nominated more than once in a single voting sequence");
    }
}
