class Candidate{
  private String candFirstName;
  private String candLastName;
  private String candParty;
  private int numOfVotes = 0;

  public Candidate(){
    candFirstName = "N/A";
    candLastName = "N/A";
    candParty = "N/A";
  }

  public Candidate(String n1, String n2, String p){
    candFirstName = n1;
    candLastName = n2;
    candParty = p;
  }

  public String getCandFirstName(){
    return candFirstName;
  }

  public String getCandLastName(){
    return candLastName;
  }

  public String getCandParty(){
    return candParty;
  }

  public int getNumOfVotes() {
    return numOfVotes;
  }

  public void setCandFirstName(String n){
    candFirstName = n;
  }

  public void setCandLastName(String n){
    candLastName = n;
  }

  public void setCandParty(String p){
    candParty = p;
  }

  public void setNumOfVotes(int v) {
    numOfVotes = v;
  }
  
  public void addVote(){
    setNumOfVotes(getNumOfVotes() + 1);
  }


  public String toString(){
    return "Candidate First Name: " + candFirstName + "\nCandidate Last Name: " + candLastName + "\nCandidate Party: " + candParty + "\nNumber of Votes: " + numOfVotes;
  }
}