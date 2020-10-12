import java.io.*;

/**
 * 1、写入练习题和答案到文件
 * 2、比对练习题的作答和答案的正确性
 */
public class FileOperate {
    //输出练习题跟答案到文件
    FileOperate(String[] formula, String[] answer, String exercisesName, String answersName, int order) throws IOException {
        File exercises = new File("./Exercises/" + exercisesName);
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(exercises));
        File answers = new File("./Answers/" + answersName);
        BufferedWriter bw2 = new BufferedWriter(new FileWriter(answers));
        //写入从1到order的算式
        for (int i = 1; i <= order; i++) {
            bw1.write(i + "、" + formula[i - 1] + "=" + "\n");
            bw1.flush();
            bw2.write(i + "、" + formula[i - 1] + "=" + answer[i - 1] + "\n");
            bw2.flush();
        }
        bw1.close();
        bw2.close();
    }

    //输入练习题跟答案进行比对
    FileOperate(String exercisesName, String answersName, String gradeName) throws IOException {
        int i = 0;
        int t = 0, f = 0;//t是正确的题数，f是错误的题数
        String exercises;//练习题
        String answers;//答案
        String[] correct = new String[10000];
        String[] wrong = new String[10000];
        File writeExercises = new File("./Exercises/" + exercisesName);//创建练习题文件
        BufferedReader br1 = new BufferedReader(new FileReader(writeExercises));//读入练习题文件中内容
        File writeAnswers = new File("./Answers/" + answersName);//创建答案文件
        BufferedReader br2 = new BufferedReader(new FileReader(writeAnswers));//读入答案文件中内容

        //将对和错的题目序号写入到相应的数组
        while ((exercises = br1.readLine()) != null && (answers = br2.readLine()) != null) {
            i++;//记录每道题的序号
            if (exercises.equals(answers) && !exercises.equals("\n")) {//对的题目
                t++;
                correct[t - 1] = String.valueOf(i);
            } else {//错的题目
                f++;
                wrong[f - 1] = String.valueOf(i);
            }
        }

        //创建grade文件并将对和错地序号输入到文件中
        File writeGrade = new File("./Grade/" + gradeName);//创建比对文件
        BufferedWriter br3 = new BufferedWriter(new FileWriter(writeGrade));//创建字节输出流
        br3.write("Correct:" + t + "(");
        br3.flush();
        for (int x = 0; x < t; x++) {//写入对的题目序号
            if (x != t - 1)
                br3.write(correct[x] + ",");
            else
                br3.write(correct[x]);
            br3.flush();
        }
        br3.write(")" + "\n" + "Wrong:" + f + "(");
        br3.flush();
        for (int x = 0; x < f; x++) {//写入错的题目序号
            if (x != f - 1)
                br3.write(wrong[x] + ",");
            else
                br3.write(wrong[x]);
            br3.flush();
        }
        br3.write(")" + "\n");
        br3.flush();
        br1.close();
        br2.close();
        br3.close();
    }
}
