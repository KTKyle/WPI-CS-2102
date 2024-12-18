package HW6;

import java.util.*;

public class ElectionData {

    private final HashMap<String, Votes> votes;
    private I3VoteStrategy strategy;

    /**
     * Constructs an instance of ElectionData with a given strategy
     * @param strategy the given strategy
     */
    public ElectionData(I3VoteStrategy strategy){
        this.votes = new HashMap<>();
        this.strategy = strategy;
    }

    /**
     * A getter for names of the candidates running with the exception case of no candidates
     * @return the names of the candidates running
     */
    public Set<String> getCandidates(){
        if(votes.isEmpty()){
            throw new RuntimeException("No candidates are running");
        }
        return new HashSet<>(votes.keySet());
    }

    /**
     * Nominates new candidate
     * @param candidate the candidate's name
     * @throws AlreadyNominatedException if a given candidate was already nominated previously
     */
    public void nominateCandidate(String candidate) throws AlreadyNominatedException{
        if(votes.containsKey(candidate)){
            throw new AlreadyNominatedException(candidate);
        }
        votes.put(candidate, new Votes(0,0,0));
    }

    /**
     * Submits one instance of a vote
     * @param first name of the first ballot candidate
     * @param second name of the second ballot candidate
     * @param third name of the third ballot candidate
     * @throws CandidateNotNominatedException if a given candidate hasn't been nominated, can't submit a vote
     * @throws MoreThanOnceException if a given candidate has already been nominated, can't submit another vote
     */
    public void submitVote(String first, String second, String third) throws
            CandidateNotNominatedException, MoreThanOnceException {
        if (first.equals(second) || first.equals(third) || second.equals(third)) {
            throw new MoreThanOnceException(first.equals(second) ? first : (first.equals(third) ? first : second));
        }
        if(!votes.containsKey(first) || !votes.containsKey(second) || !votes.containsKey(third)){
            throw new CandidateNotNominatedException(first);
        }
        votes.get(first).voteFirst();
        votes.get(second).voteSecond();
        votes.get(third).voteThird();
    }

    /**
     * @return the name of the winning candidate
     */
    public Optional<String> calculateWinner(){
        if(votes.isEmpty()){
            throw new RuntimeException("Candidate didn't win");
        }
        HashMap<String, Votes> votesCopy = new HashMap<>();
        for (Map.Entry<String, Votes> v : votes.entrySet()) {
            votesCopy.put(v.getKey(), new Votes(v.getValue()));
        }
        return strategy.calculateWinner(votesCopy);
    }

    /**
     * @param strategy sets the new voting strategy
     */
    public void setStrategy(I3VoteStrategy strategy){
        if(strategy == null){
            throw new RuntimeException("No voting strategy present");
        }
        this.strategy = strategy;
    }
}

