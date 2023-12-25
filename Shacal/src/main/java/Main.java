import java.util.ArrayList;
import java.util.List;

public class Main {
    static final List<Cletka> cletki = Cletka.getCartochkiIndf();
    static List<List<Integer>> pole = new ArrayList<>();
    static List<List<Integer>> test = new ArrayList<>();
    static List<Pirat> pirats = new ArrayList<>();

    public static void main(String[] args) {
        test3();
    }

    public static void test2() {//мотрим оптимальные клетки
        Integer m = 0;
        for (int i = 0; i < 10000; i++) {
            generatorPolu();
            m += poiskOPt();
        }
        System.out.println(m / 10000f);
    }


    public static Integer poiskOPt() {
        Integer rez = 0;
        for (int i = 2; i < 11; i++) {
            Cletka cletka = poiskPoIndf(pole.get(1).get(i));
            if (cletka.getType() == 1 || cletka.getType() == 2 || cletka.getType() == 3 || cletka.getType() == 4 ||
                    cletka.getType() == 5 || cletka.getType() == 6 || cletka.getType() == 7 || cletka.getType() == 8 ||
                    cletka.getType() == 13) {
                rez++;
            }
        }

        return rez;
    }

    // КАЖДОЙ СТРОКЕ 3 = МоНЕТ В СРЕДНЕМ ЗНАСИТ НАМ НУЖНА СТОРОК
    public static void test1() {
        Integer[] m = new Integer[11];
        for (int i = 0; i < 11; i++) {
            m[i] = 0;
        }

        for (int i = 0; i < 1000000; i++) {
            generatorPolu();
            for (int k = 1; k < 12; k++) {
                m[k - 1] += sim(k);
            }
        }
        for (int i = 0; i < 11; i++) {
            System.out.println(i + 1 + ": " + m[i]);
        }
    }

    public static Integer sim(Integer gr1) {
        Integer rez = 0;
        for (int j = 0; j < 13; j++) {
            Cletka cletka = poiskPoIndf(pole.get(gr1).get(j));
            rez += cletka.getMoney();
        }

        return rez;
    }


    public static void isledovately(Integer[] inpcv) {//lev u, pr u, heit, weith
        Integer[] pirat = pirats.get(0).getCord();
        List<Integer[]> cordCletok = new ArrayList<>();
        for (int i = 0; i < inpcv[2]; i++) {
            for (int j = 0; j < inpcv[3]; j++) {
                cordCletok.add(new Integer[]{inpcv[0] + i, inpcv[1] + j});
            }
        }

        while (cordCletok.size() > 0) {
            for (int i = 0; i < cordCletok.size(); i++) {
                Integer a = vozmothenShjd(pirat, cordCletok.get(i));
                if (a != 0) {
                    shod(cordCletok.get(i));
                    cordCletok.remove(i);
                    break;
                }
            }
        }
    }

    public static Integer vozmothenShjd(Integer[] cord1, Integer[] cord2) {
        if (cord1[0] == 0 || cord1[0] == 12 || cord1[1] == 0 || cord1[1] == 12) {
            if (cord1[0] == 0 && cord1[1] == cord2[1] && cord2[0] == 1) {
                return 5;
            } else if (cord1[0] == 12 && cord1[1] == cord2[1] && cord2[0] == 11) {
                return 1;
            } else if (cord1[1] == 0 && cord1[0] == cord2[0] && cord2[1] == 1) {
                return 3;
            } else if (cord1[1] == 12 && cord1[0] == cord2[0] && cord2[1] == 11) {
                return 7;
            } else {
                return 0;
            }
        } else {
            if (cord1[0] - 1 == cord2[0] && cord1[1] == cord2[1]) {
                return 1;
            } else if (cord1[0] - 1 == cord2[0] && cord1[1] + 1 == cord2[1]) {
                return 2;
            } else if (cord1[0] == cord2[0] && cord1[1] + 1 == cord2[1]) {
                return 3;
            } else if (cord1[0] + 1 == cord2[0] && cord1[1] + 1 == cord2[1]) {
                return 4;
            } else if (cord1[0] + 1 == cord2[0] && cord1[1] == cord2[1]) {
                return 5;
            } else if (cord1[0] + 1 == cord2[0] && cord1[1] - 1 == cord2[1]) {
                return 6;
            } else if (cord1[0] == cord2[0] && cord1[1] - 1 == cord2[1]) {
                return 7;
            } else if (cord1[0] - 1 == cord2[0] && cord1[1] - 1 == cord2[1]) {
                return 8;
            } else {
                return 0;
            }
        }
    }

    public static Cletka shod(Integer[] cord) {
        Cletka cletka = poiskPoIndf(pole.get(cord[0]).get(cord[1]));
        if (!cletka.isOtcr()) {
            return cletka;
        }
        return null;
    }


    public static Cletka poiskPoIndf(Integer indf) {
        for (int i = 0; i < cletki.size(); i++) {
            if (cletki.get(i).getIndf() == indf) {
                return cletki.get(i);
            }
        }
        return new Cletka();
    }

    public static void generatorPolu() {

        List<Cletka> a = new ArrayList<>(cletki);
        pole.clear();

        for (int i = 0; i < 13; i++) {
            List<Integer> s = new ArrayList<>();
            for (int j = 0; j < 13; j++) {
                if (j == 0 || i == 0 || j == 12 || i == 12) {
                    if (i == 6 || j == 6) {
                        s.add(-33);
                    } else {
                        s.add(-10);
                    }
                } else if ((i == 1 || i == 11) && (j == 1 || j == 11)) {
                    s.add(-10);
                } else {
                    Integer w = (int) (Math.random() * 100);
                    w = w % a.size();
                    s.add(a.get(w).getIndf());
                    a.remove(0 + w);
                }
            }
            pole.add(s);
        }

    }

    public static void smPole() {
        for (int i = 0; i < pole.size(); i++) {
            String m = "";
            for (int j = 0; j < pole.size(); j++) {
                m += pole.get(i).get(j) + " ";
            }
            System.out.println(m);
        }
    }

    public static List<Integer> putidoCorably(Integer start) {

        return null;
    }

    public static List<Integer> putydoCletki(Integer[] start, Integer[] finish) {
        return null;
    }

    public static void test3() {
        //мне нужна тест матрица
        //сам алгоритм tipo +

        Integer q=0;
        for (int i = 0; i < 6; i++) {
            List<Integer> a = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                if(j==1){
                    a.add((cletki.get(75+1).getIndf()));
                }else {
                    a.add(cletki.get(q).getIndf());
                }
                q++;
            }
            pole.add(a);
        }
        smPole();
        List<Integer> qwe= new ArrayList<>();
        qwe.add(0);
        qwe.add(0);
        finish=new Integer[]{2,2};
        List<Integer> a=recurs(new Integer[]{0,0}, qwe);
        System.out.println(a.size()/2);
        for (int i = 0; i < a.size(); i+=2) {
            System.out.println(a.get(i)+" "+a.get(i+1));
        }
        System.out.println(finish[0]+" "+finish[1]);
    }
    public static void dvigeniy(Integer[] start, Integer[] f){
        //finish=f;
        puty=0;
    }
    private static Integer[] finish;
    private static Integer puty;
    public static List<Integer> recurs (Integer[] cord, List<Integer> shod) {
        List<Integer> rez = new ArrayList<>();
        if (cord[0] == finish[0] && cord[1] == finish[1]) {
            return shod;
        }
        Cletka c = poiskPoIndf(pole.get(cord[0]).get(cord[1]));
        if (c.getType() == 1 && c.getType() == 2 ){

                for (int i = 0; i < 4; i++) {
                    Integer a1 = 0, a2 = 0;
                    if (c.getType() == 1) {
                        if (i == 0) {
                            a1 = -1;
                            a2 = -1;
                        } else if (i == 1) {
                            a1 = -1;
                            a2 = 1;
                        } else if (i == 2) {
                            a1 = 1;
                            a2 = 1;
                        } else {
                            a1 = 1;
                            a2 = -1;
                        }
                    }
                    else{
                        if (i == 0) {
                            a1 = 0;
                            a2 = -1;
                        } else if (i == 1) {
                            a1 = 0;
                            a2 = 1;
                        } else if (i == 2) {
                            a1 = 1;
                            a2 = 0;
                        } else {
                            a1 = -1;
                            a2 = 0;
                        }

                    }

                    if (cord[0] + a1 >= 0 && cord[0] + a1 < pole.size() && cord[1] + a2 >= 0 && cord[1] + a2 < pole.size() && (Math.abs(finish[0] - cord[0]) + Math.abs(finish[1] - cord[1]) >= Math.abs(finish[0] - cord[0] - a1) + Math.abs(finish[1] - cord[1] - a2))) {
                        Cletka cletka = poiskPoIndf(pole.get(cord[0] + a1).get(cord[1] + a2));
                        if (cletka.getType()==13){
                            a1*=2;
                            a2*=2;
                        }
                        if (cletka.isOtcr() && ((cletka.getType() != 14 && cletka.getType() != 15 && cletka.getType() != 16 && cletka.getType() != 17 &&
                                cletka.getType() != 20))) {
                            Boolean z = true;
                            for (int k = 0; k < shod.size(); k += 2) {
                                if (shod.get(k) == cord[0] + a1 && shod.get(k + 1) == cord[1] + a2) {
                                    z = false;
                                    break;
                                }
                            }
                            if (a1 == 0 && a2 == 0) {
                                z = false;
                            }
                            if (z) {
                                Integer zx = 1;
                                if (cletka.getType() == 9 || cletka.getType() == 22) {
                                    zx = 2;
                                } else if (cletka.getType() == 10) {
                                    zx = 3;
                                } else if (cletka.getType() == 11) {
                                    zx = 4;
                                } else if (cletka.getType() == 12) {
                                    zx = 5;
                                }
                                for (int k = 0; k < zx; k++) {
                                    shod.add(cord[0] + a1);
                                    shod.add(cord[1] + a2);
                                }
                                List<Integer> m = recurs(new Integer[]{cord[0] + a1, cord[1] + a2}, shod);
                                for (int k = 0; k < zx; k++) {
                                    shod.remove(shod.size() - 1);
                                    shod.remove(shod.size() - 1);
                                }
                                if (m.size() > 0 && (rez.size() == 0 || rez.size() > m.size())) {
                                    rez.clear();
                                    rez.addAll(m);
                                }
                            }
                        }
                }
            }
        }
        else if(c.getType()==3){
            Integer[] q = c.getNaprav();
            for (int i = 0; i < 3; i++) {
                Integer a=q[i],a1=0,a2=0;
                switch (a){
                    case 1:
                        a1=1;
                        a2=0;
                    case 2:
                        a1=1;
                        a2=1;
                    case 3:
                        a1=0;
                        a2=1;
                    case 4:
                        a1=-1;
                        a2=1;
                    case 5:
                        a1=-1;
                        a2=0;
                    case 6:
                        a1=-1;
                        a2=-1;
                    case 7:
                        a1=0;
                        a2=-1;
                    case 18:
                        a1=1;
                        a2=-1;
                }

                if (cord[0] + a1 >= 0 && cord[0] + a1 < pole.size() && cord[1] + a2 >= 0 && cord[1] + a2 < pole.size() && (Math.abs(finish[0] - cord[0]) + Math.abs(finish[1] - cord[1]) >= Math.abs(finish[0] - cord[0] - a1) + Math.abs(finish[1] - cord[1] - a2))) {
                    Cletka cletka = poiskPoIndf(pole.get(cord[0] + a1).get(cord[1] + a2));
                    if (cletka.getType()==13){
                        a1*=2;
                        a2*=2;
                    }
                    if (cletka.isOtcr() && ((cletka.getType() != 14 && cletka.getType() != 15 && cletka.getType() != 16 && cletka.getType() != 17 &&
                            cletka.getType() != 20))) {
                        Boolean z = true;
                        for (int k = 0; k < shod.size(); k += 2) {
                            if (shod.get(k) == cord[0] + a1 && shod.get(k + 1) == cord[1] + a2) {
                                z = false;
                                break;
                            }
                        }
                        if (a1 == 0 && a2 == 0) {
                            z = false;
                        }
                        if (z) {
                            Integer zx = 1;
                            if (cletka.getType() == 9 || cletka.getType() == 22) {
                                zx = 2;
                            } else if (cletka.getType() == 10) {
                                zx = 3;
                            } else if (cletka.getType() == 11) {
                                zx = 4;
                            } else if (cletka.getType() == 12) {
                                zx = 5;
                            }
                            for (int k = 0; k < zx; k++) {
                                shod.add(cord[0] + a1);
                                shod.add(cord[1] + a2);
                            }
                            List<Integer> m = recurs(new Integer[]{cord[0] + a1, cord[1] + a2}, shod);
                            for (int k = 0; k < zx; k++) {
                                shod.remove(shod.size() - 1);
                                shod.remove(shod.size() - 1);
                            }
                            if (m.size() > 0 && (rez.size() == 0 || rez.size() > m.size())) {
                                rez.clear();
                                rez.addAll(m);
                            }
                        }
                    }
                }

            }
        }
        else if(c.getType()==4 && c.getType()==5){
            Integer[] q = c.getNaprav();
            for (int i = 0; i < 2; i++) {
                Integer a=q[i],a1=0,a2=0;
                switch (a){
                    case 1:
                        a1=1;
                        a2=0;
                    case 2:
                        a1=1;
                        a2=1;
                    case 3:
                        a1=0;
                        a2=1;
                    case 4:
                        a1=-1;
                        a2=1;
                    case 5:
                        a1=-1;
                        a2=0;
                    case 6:
                        a1=-1;
                        a2=-1;
                    case 7:
                        a1=0;
                        a2=-1;
                    case 18:
                        a1=1;
                        a2=-1;
                }

                if (cord[0] + a1 >= 0 && cord[0] + a1 < pole.size() && cord[1] + a2 >= 0 && cord[1] + a2 < pole.size() && (Math.abs(finish[0] - cord[0]) + Math.abs(finish[1] - cord[1]) >= Math.abs(finish[0] - cord[0] - a1) + Math.abs(finish[1] - cord[1] - a2))) {
                    Cletka cletka = poiskPoIndf(pole.get(cord[0] + a1).get(cord[1] + a2));
                    if (cletka.getType()==13){
                        a1*=2;
                        a2*=2;
                    }
                    if (cletka.isOtcr() && ((cletka.getType() != 14 && cletka.getType() != 15 && cletka.getType() != 16 && cletka.getType() != 17 &&
                            cletka.getType() != 20))) {
                        Boolean z = true;
                        for (int k = 0; k < shod.size(); k += 2) {
                            if (shod.get(k) == cord[0] + a1 && shod.get(k + 1) == cord[1] + a2) {
                                z = false;
                                break;
                            }
                        }
                        if (a1 == 0 && a2 == 0) {
                            z = false;
                        }
                        if (z) {
                            Integer zx = 1;
                            if (cletka.getType() == 9 || cletka.getType() == 22) {
                                zx = 2;
                            } else if (cletka.getType() == 10) {
                                zx = 3;
                            } else if (cletka.getType() == 11) {
                                zx = 4;
                            } else if (cletka.getType() == 12) {
                                zx = 5;
                            }
                            for (int k = 0; k < zx; k++) {
                                shod.add(cord[0] + a1);
                                shod.add(cord[1] + a2);
                            }
                            List<Integer> m = recurs(new Integer[]{cord[0] + a1, cord[1] + a2}, shod);
                            for (int k = 0; k < zx; k++) {
                                shod.remove(shod.size() - 1);
                                shod.remove(shod.size() - 1);
                            }
                            if (m.size() > 0 && (rez.size() == 0 || rez.size() > m.size())) {
                                rez.clear();
                                rez.addAll(m);
                            }
                        }
                    }
                }
            }
        }
        else if (c.getType()==6 && c.getType()==7){
            Integer[] q = c.getNaprav();
                Integer a=q[0],a1=0,a2=0;
                switch (a){
                    case 1:
                        a1=1;
                        a2=0;
                    case 2:
                        a1=1;
                        a2=1;
                    case 3:
                        a1=0;
                        a2=1;
                    case 4:
                        a1=-1;
                        a2=1;
                    case 5:
                        a1=-1;
                        a2=0;
                    case 6:
                        a1=-1;
                        a2=-1;
                    case 7:
                        a1=0;
                        a2=-1;
                    case 18:
                        a1=1;
                        a2=-1;
                }

                if (cord[0] + a1 >= 0 && cord[0] + a1 < pole.size() && cord[1] + a2 >= 0 && cord[1] + a2 < pole.size() && (Math.abs(finish[0] - cord[0]) + Math.abs(finish[1] - cord[1]) >= Math.abs(finish[0] - cord[0] - a1) + Math.abs(finish[1] - cord[1] - a2))) {
                    Cletka cletka = poiskPoIndf(pole.get(cord[0] + a1).get(cord[1] + a2));
                    if (cletka.getType()==13){
                        a1*=2;
                        a2*=2;
                    }
                    if (cletka.isOtcr() && ((cletka.getType() != 14 && cletka.getType() != 15 && cletka.getType() != 16 && cletka.getType() != 17 &&
                            cletka.getType() != 20))) {
                        Boolean z = true;
                        for (int k = 0; k < shod.size(); k += 2) {
                            if (shod.get(k) == cord[0] + a1 && shod.get(k + 1) == cord[1] + a2) {
                                z = false;
                                break;
                            }
                        }
                        if (a1 == 0 && a2 == 0) {
                            z = false;
                        }
                        if (z) {
                            Integer zx = 1;
                            if (cletka.getType() == 9 || cletka.getType() == 22) {
                                zx = 2;
                            } else if (cletka.getType() == 10) {
                                zx = 3;
                            } else if (cletka.getType() == 11) {
                                zx = 4;
                            } else if (cletka.getType() == 12) {
                                zx = 5;
                            }
                            for (int k = 0; k < zx; k++) {
                                shod.add(cord[0] + a1);
                                shod.add(cord[1] + a2);
                            }
                            List<Integer> m = recurs(new Integer[]{cord[0] + a1, cord[1] + a2}, shod);
                            for (int k = 0; k < zx; k++) {
                                shod.remove(shod.size() - 1);
                                shod.remove(shod.size() - 1);
                            }
                            if (m.size() > 0 && (rez.size() == 0 || rez.size() > m.size())) {
                                rez.clear();
                                rez.addAll(m);
                            }
                        }

                }

            }
        }
        else if (c.getType()==8){
            Integer[] q = c.getNaprav();
            for (int i = 1; i <= 8; i++) {
                Integer a=i,a1=0,a2=0;
                switch (a){
                    case 1:
                        a1=2;
                        a2=1;
                    case 2:
                        a1=2;
                        a2=-1;
                    case 3:
                        a1=1;
                        a2=2;
                    case 4:
                        a1=1;
                        a2=-2;
                    case 5:
                        a1=-1;
                        a2=2;
                    case 6:
                        a1=-1;
                        a2=-2;
                    case 7:
                        a1=-2;
                        a2=-1;
                    case 18:
                        a1=-2;
                        a2=1;
                }

                if (cord[0] + a1 >= 0 && cord[0] + a1 < pole.size() && cord[1] + a2 >= 0 && cord[1] + a2 < pole.size() && (Math.abs(finish[0] - cord[0]) + Math.abs(finish[1] - cord[1]) >= Math.abs(finish[0] - cord[0] - a1) + Math.abs(finish[1] - cord[1] - a2))) {
                    Cletka cletka = poiskPoIndf(pole.get(cord[0] + a1).get(cord[1] + a2));
                    if (cletka.getType()==13){
                        a1*=2;
                        a2*=2;
                    }
                    if (cletka.isOtcr() && ((cletka.getType() != 14 && cletka.getType() != 15 && cletka.getType() != 16 && cletka.getType() != 17 &&
                            cletka.getType() != 20))) {
                        Boolean z = true;
                        for (int k = 0; k < shod.size(); k += 2) {
                            if (shod.get(k) == cord[0] + a1 && shod.get(k + 1) == cord[1] + a2) {
                                z = false;
                                break;
                            }
                        }
                        if (a1 == 0 && a2 == 0) {
                            z = false;
                        }
                        if (z) {
                            Integer zx = 1;
                            if (cletka.getType() == 9 || cletka.getType() == 22) {
                                zx = 2;
                            } else if (cletka.getType() == 10) {
                                zx = 3;
                            } else if (cletka.getType() == 11) {
                                zx = 4;
                            } else if (cletka.getType() == 12) {
                                zx = 5;
                            }
                            for (int k = 0; k < zx; k++) {
                                shod.add(cord[0] + a1);
                                shod.add(cord[1] + a2);
                            }
                            List<Integer> m = recurs(new Integer[]{cord[0] + a1, cord[1] + a2}, shod);
                            for (int k = 0; k < zx; k++) {
                                shod.remove(shod.size() - 1);
                                shod.remove(shod.size() - 1);
                            }
                            if (m.size() > 0 && (rez.size() == 0 || rez.size() > m.size())) {
                                rez.clear();
                                rez.addAll(m);
                            }
                        }
                    }
                }

            }
        }
        else {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (cord[0] + i >= 0 && cord[0] + i < pole.size() && cord[1] + j >= 0 && cord[1] + j < pole.size() && (Math.abs(finish[0] - cord[0]) + Math.abs(finish[1] - cord[1]) >= Math.abs(finish[0] - cord[0] - i) + Math.abs(finish[1] - cord[1] - j))) {
                    Cletka cletka = poiskPoIndf(pole.get(cord[0] + i).get(cord[1] + j));
                    Integer a1=i, a2=j;
                    if (cletka.getType()==13){
                        a1*=2;
                        a2*=2;
                    }
                    if (cletka.isOtcr() && ((cletka.getType() != 14 && cletka.getType() != 15 && cletka.getType() != 16 && cletka.getType() != 17 &&
                            cletka.getType() != 20)) ) {
                        Boolean z = true;
                        for (int k = 0; k < shod.size(); k+=2) {
                            if(shod.get(k)==cord[0]+a1 && shod.get(k+1)==cord[1]+a2){
                                z=false;
                                break;
                            }
                        }
                        if(a1==0 && a2==0){
                            z=false;
                        }
                        if(z){
                            Integer zx=1;
                            if(cletka.getType()==9 || cletka.getType()==22){
                                zx=2;
                            }else if(cletka.getType()==10){
                                zx=3;
                            }else if(cletka.getType()==11){
                                zx=4;
                            }else if(cletka.getType()==12){
                                zx=5;
                            }
                            for (int k = 0; k < zx; k++) {
                                shod.add(cord[0] + a1);
                                shod.add(cord[1] + a2);
                            }
                            List<Integer> m = recurs(new Integer[]{cord[0] + a1, cord[1] + a2}, shod);
                            for (int k = 0; k < zx; k++) {
                                shod.remove(shod.size()-1);
                                shod.remove(shod.size()-1);
                            }
                            if (m.size()>0 && (rez.size() == 0 || rez.size() > m.size())) {
                                    rez.clear();
                                    rez.addAll(m);
                            }
                        }
                    }
                }
            }}
        }
        return rez;
    } //полностью коректо выбирает варинат. успех
}
