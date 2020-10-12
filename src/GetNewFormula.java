import java.io.File;
import java.io.IOException;

/**
 * 查重生成的算式，查重后将算式的练习题和答案还有文件名传出
 */
public class GetNewFormula {
    GetNewFormula(int range, int number) throws IOException {
        ComposeFormula formula;
        int i;
        String[] Exercises = new String[10000];//用于存放练习题名字
        String[] Answers = new String[10000];//用于存放答案名字
        for (i = 0; i < number; i++) {
            formula = new ComposeFormula(range);
            Exercises[i] = formula.formula;
            Answers[i] = formula.answer;
            //查重规则：首先判断答案是否相同，若相同则检查两个式子长度是否一样，若长度一样则认为两个算式模糊相同
            for (int j = 0; j < i; j++) {
                if (Answers[i].equals(Answers[j])) {
                    if (Exercises[i].length() == Exercises[j].length())
                        do {
                            formula = new ComposeFormula(range);
                            Exercises[i] = formula.formula;
                            Answers[i] = formula.answer;
                        } while (Exercises[i].length() != Exercises[j].length());
                }
            }
        }
        //查重完毕后将最终的算式和应生成的文件名组合到一个方法中
        FileReserve name = new FileReserve();

        File exercises = new File("./Exercises/Exercises" + (name.i + 1) + ".txt");
        exercises.createNewFile();
        name.Exercises[name.i] = "Exercises" + (name.i + 1) + ".txt";

        File answers = new File("./Answers/Answers" + (name.j + 1) + ".txt");
        answers.createNewFile();
        name.Answers[name.j] = "Answers" + (name.j + 1) + ".txt";

        new FileOperate(Exercises, Answers, "Exercises" + (name.i + 1) + ".txt", "Answers" + (name.j + 1) + ".txt", i);
    }
}
