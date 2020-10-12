
import java.util.Arrays;
import java.util.Scanner;


public class ControllerMain {
    public static void main(String[] args) throws Exception {
        int range = 0, number = 0;
        FileReserve fileReserve = new FileReserve();
        System.out.println("******欢迎使用四则运算自动生成器******");
        System.out.println("以下是已有题目文件（可进行作答）：");
        fileReserve.get_ExercisesName();
        while (true) {
            System.out.println("________________________________");
            System.out.println("************指令选择*************");
            System.out.println("1.做题结果与答案进行校验。");
            System.out.println("2.生成新的题目和答案文件。");
            System.out.println("3.退出。");
            System.out.println("________________________________");
            System.out.print("请选择指令：");

            Scanner input = new Scanner(System.in);
            String choose = input.next();

            switch (choose) {
                case "1":
                    System.out.println("指令输入规范：-e <文件名>.txt -a <文件名>.txt");
                    Scanner scanner = new Scanner(System.in); // 创建Scanner对象
                    System.out.print("请输入指令："); // 打印提示
                    String arr = scanner.nextLine(); // 读取一行输入并获取字符串
                    String[] instruction = arr.split("\\s+");//空格分割字符串
                    if (instruction[0].equals("-e")) {
                        new FileOperate(instruction[1], instruction[3], "Grade.txt"); //instruction[1]是题目文件名，instruction[3]是答案文件名
                        System.out.println("作答校验完毕,请在Grade.txt中查看结果。");
                        break;
                    } else System.out.println("输入格式错误请重新操作。");
                    continue;
                case "2":
                    System.out.println("请输入指定生成题目的个数，示例：-n 10。");
                    System.out.println("请输入指定题目数值的范围，示例：-r 10。");
                    while (true) {
                        System.out.print("请输入指定生成题目的个数：");
                        scanner = new Scanner(System.in);
                        arr = scanner.nextLine();
                        instruction = arr.split("\\s+");
                        if (instruction[0].equals("-n")) {
                            number = Integer.parseInt(instruction[1]);
                        } else {
                            System.out.println("输入有误，请重新操作");
                            break;
                        }
                        System.out.print("请输入指定题目数值的范围：");
                        scanner = new Scanner(System.in);
                        arr = scanner.nextLine();
                        instruction = arr.split("\\s+");
                        if (instruction[0].equals("-r")) {
                            range = Integer.parseInt(instruction[1]);
                        } else {
                            System.out.println("输入有误，请重新操作");
                        }
                        break;
                    }
                    if (range < 0 || number < 0) {
                        System.out.println("输入参数有误请重新输入。");
                    } else if (number == 0) {
                        System.out.println("未输入题目数量");
                    } else if (range == 0) {
                        System.out.println("未输入数值范围");
                    } else {
                        System.out.println("数值的范围为：" + range);
                        System.out.println("题目的数量为：" + number);
                        System.out.println("正在生成题目.......");
                        new GetNewFormula(range, number);//题目生成
                        System.out.println("生成完毕，题目文件和答案文件分别在Exercises文件夹和在Answers文件夹中。");
                        System.out.println("温馨提示：若要在编译器中查看文件请刷新。");
                        range = 0;               //初始化数组范围和题目数量范围
                        number = 0;
                        System.out.println("以下是现有题目文件（可进行作答）：");
                        fileReserve.get_ExercisesName();
                        System.out.print("按回车键继续.......");
                        new Scanner(System.in).nextLine();
                        continue;
                    }
                case "3":
                    break;
                default:
                    System.out.println("输入有误，请重新输入。");
                    //continue;
            }
            if (choose.equals("3")) {
                System.out.println("成功退出，欢迎下次使用！");
                break;
            }
        }
    }
}
