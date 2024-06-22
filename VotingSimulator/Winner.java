class Winner extends Candidate{
  private double voteNum;

  public Winner(){
    super("N/A", "N/A", "N/A");
    voteNum = 0.0;
  }

  public Winner(String n, String n2, String p, double v){
    super(n, n2, p);
    voteNum = v;
  }

  public double getVoteNum(){
    return voteNum;
  }

  public void setVoteNum(double v){
    voteNum = v;
  }

  public Winner findWinner(Winner w, String f, String l, String p, double v){
    w.setCandFirstName(f);
    w.setCandLastName(l);
    w.setCandParty(p);
    w.setVoteNum(v);
    return w;
  }

  public Winner Tie(Winner w, String f1, String f2, String l1, String l2, String p1, String p2, double v){
    w.setCandFirstName(f1 + " " + l1 + " and ");
    w.setCandLastName(f2 + " " + f2 + " (Tie)");
    w.setCandParty(p1 + " and " + p2);
    w.setVoteNum(v);
    return w;
  }

  public double winPercentage(int totalV){
    return (((double) voteNum / totalV) * 100);
  }

  public String toString(){
    return "Winner Name: " + getCandFirstName() + " " + getCandLastName() + "\nWinner Party: " + getCandParty() + " \nWinner Votes: " + voteNum;
  }
}