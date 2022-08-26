package Timetable;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class TimeTable {
    int sum = 0;
    int x = 0;
    static int tracknum=0;
    TimeTable(){
        tracknum++;
    }

    public static ArrayList<ConferenceTalk> eventList(){
        Scanner scan = null;
        try {
            scan = new Scanner(new File("src/Timetable/TestInput.txt"));
        } catch (FileNotFoundException e) {
           e.getMessage();
        }
        ArrayList<ConferenceTalk> list = new ArrayList<>();
        while (scan.hasNextLine()) {
            String[] input = scan.nextLine().split("    ");
            if (input[1].equals("lightning")) {
                list.add(new ConferenceTalk(input[0], 5));
            } else {
                list.add(new ConferenceTalk(input[0], Integer.parseInt(input[1].substring(0, 2))));
            }
        }
        return list;
    };

    public static Boolean addEvent(ArrayList<ConferenceTalk> session, ArrayList<ConferenceTalk> mainList, int sum, int targetValue) {
        int greatCount = 0;
        Boolean result = false;
        Collections.shuffle(mainList);
        for(int i = 0; i< mainList.size(); i++){
            sum = sum + mainList.get(i).getDuration();
            if(sum <=targetValue){
                session.add(mainList.get(i));
                if(sum ==targetValue){
                    result = true;
                    break;
                }
                //if it came to end just shuffle
            }else if(i== mainList.size()-1 && result==false){
                    session.clear();
                    addEvent(session, mainList,0,180);
            }else if(sum >targetValue){
                sum = sum - mainList.get(i).getDuration();
            }
        }
        return result;
    }
    public ArrayList<ConferenceTalk> getMorningSession(){
        ArrayList<ConferenceTalk> morningSession = new ArrayList<>();
        addEvent(morningSession, eventList(),0,180 );
        morningSession.add(new ConferenceTalk("lunch", 60));
        return morningSession;
    }

    public ArrayList<ConferenceTalk> getAfternoonSession(){
        eventList().removeAll(getMorningSession());
        ArrayList<ConferenceTalk> afterNoonSession = new ArrayList<>();
        addEvent(afterNoonSession,eventList(),0, 240 );
        afterNoonSession.add(new ConferenceTalk("NetWorking event", 60));
        return afterNoonSession;
    }

    public static void printList(ArrayList<ConferenceTalk> anyList){
        for(ConferenceTalk s: anyList){
            System.out.println(s.getTitle()+" "+s.getDuration());
        }
    }

    public static void generateTrack(TimeTable track){
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("Track "+tracknum);
        System.out.println();
        ArrayList list = eventList();
        ArrayList<ConferenceTalk> morningSessionList = track.getMorningSession();
        ArrayList<ConferenceTalk> afternoonSessionList = track.getAfternoonSession();
        printList(morningSessionList);
        printList(afternoonSessionList);
    }


    public static void main(String[] args) {
        generateTrack(new TimeTable());
        generateTrack(new TimeTable());

    }

}


