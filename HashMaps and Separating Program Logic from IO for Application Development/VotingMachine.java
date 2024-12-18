package HW6;

import java.util.Optional;
import java.util.Scanner;

public class VotingMachine {

    private final ElectionData eData;
    private final Scanner scanner;

    /**
     * Creates an instance of a voting machine
     */
    public VotingMachine() {
        this.eData = new ElectionData(new MostFirstVotesStrategy());
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        VotingMachine machine = new VotingMachine();
        machine.run();
    }

    /**
     * Starts the voting process taking in user data and responding to their answers respectively
     */
    public void run(){
        while (true) {
            System.out.println("Candidates: " + String.join(", ", eData.getCandidates()));
            System.out.println("Do you want to [n]ominate someone, [v]ote for someone, change winner [s]trategy, see who [w]on, or [q]uit?");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.startsWith("q")) {
                System.out.println("Closing voting machine. Have a nice day.");
                break;
            }
            else if (choice.startsWith("n")) {
                Nominate();
            }
            else if (choice.startsWith("v")) {
                Vote();
            }
            else if (choice.startsWith("s")) {
                changeStrategy();
            }
            else if (choice.startsWith("w")) {
                displayWinner();
            }
            else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Nominates a candidate
     */
    private void Nominate() {
        System.out.println("Enter the name of the candidate to nominate: ");
        String name = scanner.nextLine().trim();
        try {
            eData.nominateCandidate(name);
            System.out.println("Candidate " + name + " has been successfully nominated.");
        }
        catch (AlreadyNominatedException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Puts the users votes in for their candidates
     */
    private void Vote() {
        try {
            System.out.println("Enter your first choice: ");
            String first = scanner.nextLine().trim();

            System.out.println("Enter your second choice: ");
            String second = scanner.nextLine().trim();

            System.out.println("Enter your third choice: ");
            String third = scanner.nextLine().trim();

            eData.submitVote(first, second, third);
            System.out.println("Your vote has been successfully recorded.");
        }

        catch (CandidateNotNominatedException e) {
            System.out.println(e.getMessage());
            System.out.println("Would you like to nominate the candidate [y]es/[n]o?");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.startsWith("y")) {
                nominateMissingCandidate(e.getCandidate());
            }

        }
        catch (MoreThanOnceException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Nominates a candidate IF it hasn't already been nominated alredy
     * @param candidate the name of the candidate
     */
    private void nominateMissingCandidate(String candidate) {
        try {
            eData.nominateCandidate(candidate);
            System.out.println("Candidate " + candidate + " has been successfully nominated.");
        }
        catch (AlreadyNominatedException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Changes the users intended voting strategy
     */
    private void changeStrategy() {
        System.out.println("Which strategy would you like to use? most [f]irst votes or most [a]greeable?");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.startsWith("f")) {
            eData.setStrategy(new MostFirstVotesStrategy());
            System.out.println("Strategy set: Most First Votes");
        }
        else if (choice.startsWith("a")) {
            eData.setStrategy(new MostAgreeableStrategy());
            System.out.println("Strategy set: Most Agreeable");
        }
        else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    /**
     * Displays winner of the vote with respect to their voting strategy
     */
    private void displayWinner() {
        Optional<String> winner = eData.calculateWinner();

        if (winner.isPresent()) {
            System.out.println("The winner is: " + winner.get());
        }
        else {
            System.out.println("No clear winner under the current strategy.");
        }
    }
}