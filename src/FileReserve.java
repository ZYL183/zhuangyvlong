import java.io.*;

/**
 * 存放练习题和答案文件
 */
public class FileReserve {
    String[] Exercises = new String[10000];//存放练习题
    String[] Answers = new String[10000];//存放答案
    int i = 0;
    int j = 0;

    public FileReserve() {
        //将题目文件存入数组
        File exercisesFile = new File("Exercises");
        String[] list = exercisesFile.list();
        if (list != null) {
            for (String string : list) {
                if (string.endsWith("txt")) {
                    Exercises[i] = string;//将题目文件名字放入字符串数组
                    i++;
                }
            }
        }

        //将答案文件存入数组
        File answersFile = new File("Answers");
        String[] list2 = answersFile.list();
        if (list2 != null) {
            for (String string : list2) {
                if (string.endsWith("txt")) {
                    Answers[j] = string;//将答案文件名字放入字符串数组
                    j++;
                }
            }
        }
    }

    public void get_ExercisesName() {
        i = 0;
        File file = new File("Exercises");
        String[] list = file.list();
        if (list != null) {
            for (String string : list) {
                Exercises[i] = string;//将题目文件名字放入字符串数组
                i++;
            }
        }
        for (int i = 0; Exercises[i] != null; i++)
            System.out.println(Exercises[i]);
    }
}
