package app.user;

public class Income {
    double merchRevenue,songRevenue,ranking;
    String mostProfitableSong;

    public Income(double merchRevenue, double songRevenue, double ranking, String mostProfitableSong) {
        this.merchRevenue = merchRevenue;
        this.songRevenue = songRevenue;
        this.ranking = ranking;
        this.mostProfitableSong = mostProfitableSong;
    }
}
