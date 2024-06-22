class Vote extends Candidate {
  private String voterName;

  public Vote() {
    super("N/A", "N/A", "N/A");
    voterName = "N/A";
  }

  public Vote(String n1, String n2, String p, String n) {
    super(n1, n2, p);
    voterName = n;
  }

  public String getVoterName() {
    return voterName;
  }

  public void setVoterName(String n) {
    voterName = n;
  }

  public String toString() {
    return "Voter Name: " + voterName + "\nCandidate First Name: " + getCandFirstName() + "\nCandidate Last Name: "
        + getCandLastName() + "\nCandidate Party: " + getCandParty();
  }
}