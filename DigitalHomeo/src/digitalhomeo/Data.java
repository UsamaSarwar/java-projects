package digitalhomeo;

public class Data {
    
    String Id=null;
    String Des=null;
    String Med=null;
    String Pot=null;
    
    public Data() {
    }
    
    public Data(String Id, String Des, String Med, String Pot) {
        this.Id=Id;
        this.Des=Des;
        this.Med=Med;
        this.Pot=Pot;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setDes(String Des) {
        this.Des = Des;
    }

    public void setMed(String Med) {
        this.Med = Med;
    }

    public void setPot(String Pot) {
        this.Pot = Pot;
    }

    public String getId() {
        return Id;
    }

    public String getDes() {
        return Des;
    }

    public String getMed() {
        return Med;
    }

    public String getPot() {
        return Pot;
    }
}