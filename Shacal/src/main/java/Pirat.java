public class Pirat {
    private Integer[] cord;
    private boolean money=false;
    private boolean dvig=true;


    public Pirat(Integer[] cord) {
        this.cord = cord;
    }

    public Integer[] getCord() {
        return cord;
    }

    public void setCord(Integer[] cord) {
        this.cord = cord;
    }

    public boolean isMoney() {
        return money;
    }

    public void setMoney(boolean money) {
        this.money = money;
    }

    public boolean isDvig() {
        return dvig;
    }

    public void setDvig(boolean dvig) {
        this.dvig = dvig;
    }
}
