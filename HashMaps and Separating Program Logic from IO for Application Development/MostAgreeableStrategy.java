package HW6;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MostAgreeableStrategy implements I3VoteStrategy{

    /**
     * @param votes HashMap of candidates and their respective votes
     * @return the winner based on who has the most votes in a given category
     */
    @Override
    public Optional<String> calculateWinner(HashMap<String, Votes> votes) {
        String winner = null;
        int maxVotes = 0;

        for (Map.Entry<String, Votes> v : votes.entrySet()) {
            Votes candidateVotes = v.getValue();
            int highestCategoryVotes = Math.max(
                    candidateVotes.getFirstVotes(),
                    Math.max(candidateVotes.getSecondVotes(), candidateVotes.getThirdVotes()));
            if (highestCategoryVotes > maxVotes) {
                winner = v.getKey();
                maxVotes = highestCategoryVotes;
            }
        }

        return Optional.ofNullable(winner);
    }
}
