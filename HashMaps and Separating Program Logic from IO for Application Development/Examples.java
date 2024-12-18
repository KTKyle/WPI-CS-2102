package HW6;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class Examples {

    @Test
    public void typicalMostFirstVotes()
            throws AlreadyNominatedException, CandidateNotNominatedException, MoreThanOnceException{
        ElectionData e = new ElectionData(new MostFirstVotesStrategy());
        e.nominateCandidate("Washington");
        e.nominateCandidate("Lincoln");
        e.nominateCandidate("Jefferson");
        e.submitVote("Washington","Lincoln","Jefferson");
        assertEquals(Optional.of("Washington"), e.calculateWinner());
    }

    @Test
    public void typicalMostAgreeable()
    throws AlreadyNominatedException, CandidateNotNominatedException, MoreThanOnceException{
        ElectionData e = new ElectionData(new MostAgreeableStrategy());
        e.nominateCandidate("Washington");
        e.nominateCandidate("Lincoln");
        e.nominateCandidate("Jefferson");
        e.submitVote("Washington","Lincoln","Jefferson");
        e.submitVote("Jefferson","Lincoln","Washington");
        assertEquals(Optional.of("Lincoln"), e.calculateWinner());
    }

    @Test
    public void typicalGetCandidates()
            throws AlreadyNominatedException, CandidateNotNominatedException, MoreThanOnceException{
        ElectionData e = new ElectionData(new MostAgreeableStrategy());
        e.nominateCandidate("Washington");
        e.nominateCandidate("Lincoln");
        e.nominateCandidate("Jefferson");
        HashSet<String> candidates = new HashSet<>();
        candidates.add("Washington");
        candidates.add("Lincoln");
        candidates.add("Jefferson");
        assertEquals(candidates, e.getCandidates());
    }

    @Test
    public void typicalSetStrategy()
            throws AlreadyNominatedException, CandidateNotNominatedException, MoreThanOnceException{
        ElectionData e = new ElectionData(new MostAgreeableStrategy());
        e.setStrategy(new MostFirstVotesStrategy());
        e.nominateCandidate("Washington");
        e.nominateCandidate("Lincoln");
        e.nominateCandidate("Jefferson");
        e.submitVote("Washington","Lincoln","Jefferson");
        assertEquals(Optional.of("Washington"), e.calculateWinner());
    }

}
