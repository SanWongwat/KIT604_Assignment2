package assignment2.kit607.timemanagement;

/**
 * Created by PeitaoYe on 2017/5/20.
 */

public class Question {
    private String[] shortQ={"1. Do you make a list of the things you have to do each day?," ,
            "2. Do you plan your day before you start it?," ,
            "3. Do you make a schedule of the activities you have to do on work days?",
            "4. Do you write a set of goals for yourself for each day?",
            "5. Do you spend time each day planning?",
            "6. Do you have a clear idea of what you want to accomplish during the next week?",
            "7. Do you set and honor priorities?"};
    private String[] attitude={"1. Do you often find yourself doing things which interfere with your schoolwork simply because you hate to say \"No\" to people? *\n",
   "2. Do you feel you are in charge of your own time, by and large?\n",
    "3. On an average class day do you spend more time with personal grooming than doing schoolwork?*\n",
    "4. Do you believe that there is room for improvement in the way you manage your time? *\n",
    "5. Do you make constructive use of your time?\n",
    "6. Do you continue unprofitable routines or activities?*\n"};
    private String[] longQ={"1. Do you usually keep your desk clear of everything other than what you are currently working on?\n",
    "2. Do you have a set of goals for the entire quarter?\n",
    "3. The night before a major assignment is due, are you usually still working on it? *\n",
    "4. When you have several things to do, do you think it is best to do a little bit of work on each one?\n",
    "5. Do you regularly review your class notes, even when a test is not imminent?\n"};

    public String[] getShortQ() {
        return shortQ;
    }
    public String[] getAttitude(){
        return attitude;
    }

    public String[] getLongQ() {
        return longQ;
    }
}
