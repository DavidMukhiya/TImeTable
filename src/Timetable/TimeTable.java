package Timetable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TimeTable {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("C:/Users/sherl/Desktop/TestInput.txt"));
        List<ConferenceTalk> morningSession = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        String[] input = null;
        ArrayList<ConferenceTalk> list = new ArrayList<>();
        ConferenceTalk lunch = new ConferenceTalk("lunch", 60);
        while (scan.hasNextLine()) {
            input = scan.nextLine().split("    ");
            if (input[1].equals("lightning")) {
                list.add(new ConferenceTalk(input[0], 5));
            } else {
                list.add(new ConferenceTalk(input[0], Integer.parseInt(input[1].substring(0, 2))));
            }
        }


//        System.out.println(list.toString());
//
//        System.out.println("-------------------------------------------------------------------------------");
//        for (ConferenceTalk s : list) {
//            System.out.println(s.getTitle() + " " + s.getDuration());
//        }
//        System.out.println("-------------------------------------------------------------------------------");
//        System.out.println("non shuffe list");
//        Collections.shuffle(list);
//        System.out.println(list);
//        System.out.println("Shuffle list");
        ArrayList<Integer> temp=new ArrayList<>();
        int sum = 0;
        int x = 0;
        talk(morningSession, list, temp, sum,x,180);
        System.out.println(morningSession);
        System.out.println(morningSession.isEmpty());
        for(ConferenceTalk s:morningSession){
            System.out.println(s.getTitle()+" "+s.getDuration());
        }
        ArrayList<ConferenceTalk> afterNoonSession = new ArrayList<>();
        list.removeAll(morningSession);
        temp.clear();
        talk(afterNoonSession, list, temp, sum,x,240);
        System.out.println(afterNoonSession);
        System.out.println(afterNoonSession.isEmpty());
        for(ConferenceTalk t:afterNoonSession){
            System.out.println(t.getTitle()+" "+t.getDuration());
        }



        //System.out.println(list);
//        while (sum1 != 180) {
//            Collections.shuffle(list, new Random(3));
//            for (ConferenceTalk conferenceTalk1 : list) {
//                sum1 = sum1 + conferenceTalk1.getDuration();
//                if (sum1 > 180) {
//                    sum1 = sum1 - conferenceTalk1.getDuration();
//                } else if (sum1 <= 180) {
//
//                    if (morningSession.contains(conferenceTalk1)) {
//                        sum1 = sum1 - conferenceTalk1.getDuration();
//                        //AfterNoonSession.add(s);
//                    } else {
//                        morningSession.add(conferenceTalk1);
//                    }
//                    if (sum1 == 180) {
//                        break;
//                    }
//                } else {
//                    morningSession.clear();
//                }
//            }
//        }
//        System.out.println("sum: " + sum1);
//        //System.out.println("------------------------------------------------------------------------------");
//        //System.out.println("Afternoon Session:" + morningSession.toString());
//        System.out.println("------------------------------------------------------");
//        morningSession.add(lunch);
//        for (ConferenceTalk morning : morningSession) {
//            System.out.println(morning.getTitle() + " " + morning.getDuration());
//        }
//
//
//        list.removeAll(morningSession);
//        ArrayList<ConferenceTalk> afterNoonSession = new ArrayList<>();
//
//
//        int sum2 = 0;
//        while (sum2 != 240) {
//            Collections.shuffle(afterNoonSession);
//            for (int i = 0; i < list.size(); i++) {
//                sum2 = sum2 + list.get(i).getDuration();
//                if (sum2 <= 240) {
//                    if (afterNoonSession.contains(list.get(i))) {
//                        sum2 = sum2 - list.get(i).getDuration();
//                    } else {
//                        afterNoonSession.add(list.get(i));
//                    }
//                    if (sum2 == 240) {
//                        break;
//                    }
//                } else if (sum2 > 240) {
//                    sum2 = sum2 - list.get(i).getDuration();
//                }
//            }
//        }
//        for (ConferenceTalk after : afterNoonSession) {
//            System.out.println(after.getTitle() + " " + after.getDuration());
//        }

    }

    private static void talk(List<ConferenceTalk> morningSession, ArrayList<ConferenceTalk> list, ArrayList<Integer> temp, int sum, int x,int total) {
        int greatCount = 0;
        if(x==0) {
            Collections.shuffle(list);
        }
        for(int i = x; i< list.size(); i++){

            sum = sum + list.get(i).getDuration();
            if(sum <=total){
                morningSession.add(list.get(i));
                if(sum ==total){
                    break;
                }
            }else if(sum >total && (i== list.size()-1)){
                if(greatCount > list.size()/3) {
                    Collections.shuffle(list);
                    morningSession.clear();
                    temp.clear();
                    talk(morningSession, list, temp, 0, 0, 180);
                }else {
                    int j = (temp.get(0));
                    temp.clear();
                    talk(morningSession, list, new ArrayList<>(), sum, j, total);
                }
            }else if(sum >total){
                temp.add(i);
                sum = sum - list.get(i).getDuration();
                greatCount++;
            }
        }
    }
}


