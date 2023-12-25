import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

public class Cletka {
    private Integer type;
    //0-пустые, 39 штук
    //1 срелки 4 наискась, 3штуки
    //2 срелки во все стороны по прмой 3 шт
    //3 стрелки в верх вправо, наискась вниз, влево 3 штуки
    //4 стрелки в сторону прямую, 3 шт
    //5 стрелка наискась, 3 шт
    //6 стрелка по прямой в 1 стороны, 3 шт.
    //7 стрелки по прямой в углы, 3шт.
    //8 конь, 2 штки
    // 9 веертушка 2, 5 шт
    // 10 верртушка 3, 4 шт
    //11 вертушкаа 4, 2 штуки
    //12 вертушка 5, 1 штука
    //13 лёд 6 шт
    //14 яма 3 шт
    //15 крокодил 4 шт
    //16 людоед 1 шт
    //17 крепость 2 шт
    //18 воскрес 1 шт
    //19 вертик 2 шт
    //20 шар 2 штука
    //21 пушка 2 шт
    //22 ром 4 шт
    //23 золото 1, 5 шт
    //24 золото 2, 5 шт
    //25 золото 3, 3 шт
    //26 золото 4, 2 шт
    //27, золото 5, 1 шт
    //всего 37 монет значит нам надо 19 монет, забрать и я выйграю. всего сундуков 26 штук, на 117 клеток, шанс того что выпадет в кажд
    public static List<Cletka> getCartochkiIndf(){
        List<Cletka> rez = new ArrayList<>();
        Integer a = 100;
        for (int i = 0; i < 39; i++) {
            rez.add(new Cletka(0, a, null));
            a++;
        }

        for (int i = 0; i < 3; i++) {
            rez.add(new Cletka(1, a, new Integer[]{2,4,6,8}));
            a++;
        }
        for (int i = 0; i < 3; i++) {
            rez.add(new Cletka(2, a, new Integer[]{1,3,5,7}));
            a++;
        }
        for (int i = 0; i < 3; i++) {
            rez.add(new Cletka(3, a, zapolnenie3()));
            a++;
        }
        for (int i = 0; i < 3; i++) {
            rez.add(new Cletka(4, a, zapolnenie4()));
            a++;
        }
        for (int i = 0; i < 3; i++) {
            rez.add(new Cletka(5, a, zapolnenie5()));
            a++;
        }
        for (int i = 0; i < 3; i++) {
            rez.add(new Cletka(6, a, zapolnenie6()));
            a++;
        }
        for (int i = 0; i < 3; i++) {
            rez.add(new Cletka(7, a, zapolnenie7()));
            a++;
        }

        for (int i = 0; i < 2; i++) {
            rez.add(new Cletka(8, a, null));
            a++;
        }
        for (int i = 0; i < 5; i++) {
            rez.add(new Cletka(9, a, null));
            a++;
        }
        for (int i = 0; i < 4; i++) {
            rez.add(new Cletka(10, a, null));
            a++;
        }
        for (int i = 0; i < 2; i++) {
            rez.add(new Cletka(11, a, null));
            a++;
        }
        for (int i = 0; i < 1; i++) {
            rez.add(new Cletka(12, a, null));
            a++;
        }
        for (int i = 0; i < 6; i++) {
            rez.add(new Cletka(13, a, null));
            a++;
        }
        for (int i = 0; i < 3; i++) {
            rez.add(new Cletka(14, a, null));
            a++;
        }
        for (int i = 0; i < 4; i++) {
            rez.add(new Cletka(15, a, null));
            a++;
        }
        for (int i = 0; i < 1; i++) {
            rez.add(new Cletka(16, a, null));
            a++;
        }
        for (int i = 0; i < 2; i++) {
            rez.add(new Cletka(17, a, null));
            a++;
        }
        for (int i = 0; i < 1; i++) {
            rez.add(new Cletka(18, a, null));
            a++;
        }
        for (int i = 0; i < 2; i++) {
            rez.add(new Cletka(19, a, null));
            a++;
        }
        for (int i = 0; i < 2; i++) {
            rez.add(new Cletka(20, a, null));
            a++;
        }

        for (int i = 0; i < 2; i++) {
            rez.add(new Cletka(21, a, zapolnenie6()));
            a++;
        }
        for (int i = 0; i < 4; i++) {
            rez.add(new Cletka(22, a, null));
            a++;
        }

        for (int i = 0; i < 5; i++) {
            rez.add(new Cletka(23, a, null, 1));
            a++;
        }
        for (int i = 0; i < 5; i++) {
            rez.add(new Cletka(24, a, null,2));
            a++;
        }
        for (int i = 0; i < 3; i++) {
            rez.add(new Cletka(25, a, null,3));
            a++;
        }
        for (int i = 0; i < 2; i++) {
            rez.add(new Cletka(26, a, null,4));
            a++;
        }for (int i = 0; i < 1; i++) {
            rez.add(new Cletka(27, a, null,5));
            a++;
        }

        return rez;
    }

    public Cletka() {
        money=0;
    }

    private Integer money;

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    private static Integer[] zapolnenie3(){
        Integer m=1;
        double a= Math.random();
        if (a <= 0.25) {
            m=1;
        }else if(a>=0.25 && a<=0.5){
            m=3;
        }else if(a>=0.5 && a<=0.75){
            m=5;
        }else {
            m=7;
        }
        return new Integer[]{m,(m+2)%8,(m+5)%8};
    }
    private static Integer[] zapolnenie4(){
        Double a = Math.random();
        if(a>=0.5){
            return new Integer[]{1,5};
        }
        return new Integer[]{3,7};
    }
    private static Integer[] zapolnenie5(){
        Double a = Math.random();
        if(a>=0.5){
            return new Integer[]{2,6};
        }
        return new Integer[]{4,8};
    }
    private static Integer[] zapolnenie6(){
        Integer m=1;
        double a= Math.random();
        if (a <= 0.25) {
            m=1;
        }else if(a>=0.25 && a<=0.5){
            m=3;
        }else if(a>=0.5 && a<=0.75){
            m=5;
        }else {
            m=7;
        }
        return new Integer[]{m};
    }
    private static Integer[] zapolnenie7(){
        Integer m=1;
        double a= Math.random();
        if (a <= 0.25) {
            m=1;
        }else if(a>=0.25 && a<=0.5){
            m=3;
        }else if(a>=0.5 && a<=0.75){
            m=5;
        }else {
            m=7;
        }
        return new Integer[]{m+1};
    }


    public Cletka(Integer type, Integer indf, Integer[] naprav) {
        this.type = type;
        Indf = indf;
        this.naprav = naprav;
        money=0;
    }
    public Cletka(Integer type, Integer indf, Integer[] naprav, Integer money) {
        this.type = type;
        Indf = indf;
        this.naprav = naprav;
        this.money=money;
    }


    private Integer Indf;


    public void setType(Integer type) {
        this.type = type;
    }

    public void setIndf(Integer indf) {
        Indf = indf;
    }

    public Integer getIndf() {
        return Indf;
    }

    private Integer[] naprav;//1 верх, 2 право верх и т.д
    private boolean worc=true;

    private boolean otcr=true;

    public Cletka(Integer type, Integer[] naprav) {
        this.type = type;
        this.naprav = naprav;
    }

    public boolean isOtcr() {
        return otcr;
    }

    public void setOtcr(boolean otcr) {
        this.otcr = otcr;
    }

    public Integer getType() {
        return type;
    }

    public Integer[] getNaprav() {
        return naprav;
    }

    public void setNaprav(Integer[] naprav) {
        this.naprav = naprav;
    }

    public boolean isWorc() {
        return worc;
    }

    public void setWorc(boolean worc) {
        this.worc = worc;
    }
}
