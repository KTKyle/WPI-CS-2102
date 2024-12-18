package HW6;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MostFirstVotesStrategy implements I3VoteStrategy{

    /**
     * @param votes HashMap of candidates and their respective votes
     * @return the winner based on who has the majority of votes
     */
    @Override
    public Optional<String> calculateWinner(HashMap<String, Votes> votes) {
        int totalFirstVotes = votes.values().stream().mapToInt(Votes::getFirstVotes).sum();
        String winner = null;
        int maxFirstVotes = 0;

        for (Map.Entry<String, Votes> v : votes.entrySet()) {
            int firstVotes = v.getValue().getFirstVotes();
            if (firstVotes > totalFirstVotes / 2 && firstVotes > maxFirstVotes) {
                winner = v.getKey();
                maxFirstVotes = firstVotes;
            }
        }

        return Optional.ofNullable(winner);
    }
}
