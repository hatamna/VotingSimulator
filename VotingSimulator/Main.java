import java.util.*;

public class Main {
  static int voteChoice;
  static boolean voteEnded = false;
  static boolean gameRunning = true;
  static int numCandidates = 0;
  static boolean initialCandidatesDone = false;
  static String voterName;

  static Scanner input = new Scanner(System.in);

  static ArrayList<Candidate> candidates = new ArrayList<Candidate>();
  static LinkedList<Vote> votes = new LinkedList<Vote>();
  static Winner winner = new Winner();

  static String RESET = "\u001B[0m";
  static String RED = "\u001B[31m";
  static String GREEN = "\u001B[32m";
  static String YELLOW = "\u001B[33m";

  public static int checkInt() {
    int num = 0;
    outer: while (gameRunning) {
      try {
        num = input.nextInt();
        input.nextLine();
        break outer;
      } catch (InputMismatchException e) {
        System.out.println(RED + "Please enter a valid integer" + RESET);
        input.nextLine();
      }
    }
    return num;
  }

  public static void voteRecord() {
    if (voteEnded == false) {
      System.out.println(RED + "\nVote Recond Only Available after voting. " + RESET);
    } else {
      for (int i = 0; i < votes.size(); i++) {
        System.out.println(RED + "Vote #" + (i + 1) + ": " + RESET + votes.get(i));
      }
    }
  }

  public static void stats() {
    if (voteEnded) {
      System.out.println("\nWinner: " + winner.getCandFirstName() + " " + winner.getCandLastName() + "\nParty: "
          + winner.getCandParty() + "\nVotes: " + winner.getVoteNum());
      System.out.println("\nTotal Number of Votes: " + votes.size());
      for (int i = 0; i < candidates.size(); i++) {
        candidates.get(i).getNumOfVotes();
        System.out.printf(
            "Votes for Candidate #" + (i + 1) + ": " + candidates.get(i).getNumOfVotes()
                + "\nVote Percentage of Candidate #" + (i + 1) + ":  %.2f Percent\n",
            winner.winPercentage(votes.size()));
      }
    } else {
      System.out.println(RED + "\nVote not yet concluded. Finish voting to see staticstics" + RESET);
    }
  }

  public static void displayMenu() {
    System.out.print(
        "\nSelect one of the following using the associated number (0 to quit): \n\n1. View Candidates\n2. Add Candidate\n3. Remove Candidate\n4. Edit Candidate\n5. Start Voting\n6. View Vote Record\n7. View Additional Stats\n\n-> ");
  }

  public static void newCandidate() {
    do {
      System.out.print("\nHow many candidates do you want to add?: ");
      numCandidates = checkInt();
      if (numCandidates < 2 && !initialCandidatesDone) {
        System.out.println("You need atleast 2 candidates. ");
      }
    } while (numCandidates < 2 && !initialCandidatesDone);
    for (int i = 0; i < numCandidates; i++) {
      System.out.print("\nEnter the first name of candidate " + (i + 1) + ": ");
      String firstName = input.next();
      System.out.print("Enter the last name of candidate " + (i + 1) + ": ");
      String lastName = input.next();
      System.out.print("Enter the party of candidate " + (i + 1) + ": ");
      String party = input.next();
      candidates.add(new Candidate(firstName, lastName, party));
    }

    System.out.println(GREEN + "\nCandidate Added Successfully!" + RESET);
    initialCandidatesDone = true;
  }

  public static void DisplayCandidates() {
    if (candidates.size() == 0) {
      System.out
          .println(RED + "\nThere are currently no candidates to display. Please add candidates first.\n" + RESET);
    } else if (candidates.size() > 0) {
      for (int i = 0; i < candidates.size(); i++) {
        System.out.println("\n" + (i + 1) + ". " + candidates.get(i).getCandFirstName() + " "
            + candidates.get(i).getCandLastName() + " (" + candidates.get(i).getCandParty() + ")");
      }
    }
  }

  public static void removeCandidate() {
    boolean notFound = false;
    if (candidates.size() < 3) {
      System.out.println(
          RED + "\nThere are currently not enough  candidates to remove. Please add candidates first.\n" + RESET);
    } else if (candidates.size() > 2) {
      System.out.print("\nEnter the first name of the candidate you want to remove: ");
      String firstName = input.next();
      System.out.print("Enter the last name of the candidate you want to remove: ");
      String lastName = input.next();
      for (int i = 0; i < candidates.size(); i++) {
        if (candidates.get(i).getCandFirstName().equals(firstName)
            && candidates.get(i).getCandLastName().equals(lastName)) {
          candidates.remove(i);
          System.out.println(GREEN + "\nCandidate Removed Successfully!" + RESET);
        } else {
          notFound = true;
        }
      }
    }
    if (notFound) {
      System.out.println(RED + "\nCandidate not found. Please try again.\n" + RESET);
    }
  }

  public static void editCandidate() {
    outer: while (gameRunning) {
      if (candidates.size() > 0) {
        System.out.println("Which Candidate's profile do yo want to edit? (Enter 0 to cancel and return to Main): \n");
        DisplayCandidates();
        System.out.println("\n\nOld First Name: ");
        String oldFirstName = input.next();
        if (!oldFirstName.equals("0")) {
          System.out.print("New First Name: ");
          String newFirstName = input.next();
          System.out.println("Old Last Name: ");
          String oldLastName = input.next();
          System.out.print("New Last Name: ");
          String newLastName = input.next();
          System.out.print("Old Party: ");
          String oldParty = input.next();
          System.out.print("New Party: ");
          String newParty = input.next();
          for (int i = 0; i < candidates.size(); i++) {
            if (candidates.get(i).getCandFirstName().equals(oldFirstName)
                && candidates.get(i).getCandLastName().equals(oldLastName)
                && candidates.get(i).getCandParty().equals(oldParty)) {
              candidates.get(i).setCandFirstName(newFirstName);
              candidates.get(i).setCandLastName(newLastName);
              candidates.get(i).setCandParty(newParty);
            } else {
              System.out.println("");
            }
            break outer;
          }
        } else {

        }
      } else {
        System.out.println(RED + "There Are current no candidates to edit. Please add candidates first." + RESET);
        break;
      }
    }
  }

  public static void voteSequence() {
    String f1 = null;
    String f2 = null;
    String l1 = null;
    String l2 = null;
    String p1 = null;
    String p2 = null;
    // Checks for the number of candidates
    if (candidates.size() == 0) {
      System.out.println(RED + "There are no candidates to vote for. Please add candidates first." + RESET);
    } else {
      System.out.println(GREEN + "\n\nVoting Started!\n\n" + RESET);
      // Actual voting sequence
      do {
        System.out.print("\nWhats is the voter's last name?: ");
        voterName = input.next();
        DisplayCandidates();
        System.out.print(
            "\nWhat is the number of the candidate you want to vote for? (Enter 0 to cancel and return to Main): ");
        voteChoice = checkInt();
        if ((voteChoice - 1) > candidates.size()) {
          System.out.println(RED + "\nInvalid Choice. Please try again.\n" + RESET);
        } else if ((voteChoice - 1) > -1 && voteChoice <= candidates.size()) {
          votes.add(new Vote(candidates.get(voteChoice - 1).getCandFirstName(),
              candidates.get(voteChoice - 1).getCandLastName(), candidates.get(voteChoice - 1).getCandParty(),
              voterName));
          candidates.get(voteChoice - 1).addVote();
        }
      } while (voteChoice != 0 && !voterName.equals("0"));
      // Post voting-sequence: Start of vote-checking
      for (int i = 0; i < candidates.size(); i++) {
        if (candidates.get(i).getNumOfVotes() > winner.getVoteNum()) {
          winner = winner.findWinner(winner, candidates.get(i).getCandFirstName(), candidates.get(i).getCandLastName(),
              candidates.get(i).getCandParty(), candidates.get(i).getNumOfVotes());
          f1 = candidates.get(i).getCandFirstName();
          l1 = candidates.get(i).getCandLastName();
          p1 = candidates.get(i).getCandLastName();
        } else if (candidates.get(i).getNumOfVotes() == winner.getVoteNum())
          f2 = candidates.get(i).getCandFirstName();
        l2 = candidates.get(i).getCandLastName();
        p2 = candidates.get(i).getCandLastName();
        winner = winner.Tie(winner, f1, f2, l1, l2, p1, p2, candidates.get(i).getNumOfVotes());
      }
      System.out
          .println(YELLOW + "\n\nWinner: " + winner.getCandFirstName() + " " + winner.getCandLastName() + RESET);
      System.out.println(GREEN + "\nVoting Concluded!" + RESET);
      voteEnded = true;
    }
  }

  public static void main(String[] args) {
    System.out.print("Welcome to the " + RED + "LDHSS Student President 2024-2025 Elections!\n" + RESET);
    while (gameRunning) {
      displayMenu();
      int option = checkInt();
      switch (option) {
        case 0:
          gameRunning = false;
          System.out.println(RED + "\nThank you for using the LDHSS 2024-2025 Elections App!");
          System.exit(0);
          break;
        case 1:
          DisplayCandidates();
          break;
        case 2:
          newCandidate();
          break;
        case 3:
          removeCandidate();
          break;
        case 4:
          editCandidate();
          break;
        case 5:
          voteSequence();
          break;
        case 6:
          voteRecord();
          break;
        case 7:
          stats();
          break;
        default:
          System.out.println(RED + "\nInvalid input. Please try again." + RESET);
      }
    }
  }
}