/**
 * 生成算式
 */
public class CreateFormula {
    int value;  //数的和，单个数的时候是本身，两个数的时候是两数的运算结果
    int value1; //两个数的时候第一个数
    int value2; //两个数的时候第二个数

    String fraction;//存放分数值
    String formula;//存放算式

    boolean isFu = false;//负数标志
    boolean isSwap = false;//负数调换标志
    boolean isFraction = false;//分数标志

    char symbol;
    public char[] all_symbol = new char[]{'+', '-', '*', '/'};

    public CreateFormula(int range) {//range为传入的数值范围
        if ((Math.random() * 2) <= 1) {            //只有一数的情况
            value = (int) (Math.random() * (range - 1) + 1);
            formula = String.valueOf(value);
            symbol = '\0';
        } else {//若生成两个数
            value1 = (int) (Math.random() * range + 1);
            value2 = (int) (Math.random() * range + 1);
            symbol = all_symbol[(int) (Math.random() * 4)];//随机生成+、-、*、/的其中一种符号
            if (symbol == '/' && value1 % value2 != 0) {//若为除号，且不能整除，生成分式
                isFraction = true;
                fraction = fractionOperate(value1, value2);
            } else {//若为除号，且可以整除，或者是其他符号
                value = get_value(value1, value2, symbol);
            }
            if (isSwap) {//如果出现负数
                value = -value;
                formula = "(" + value2 + symbol + value1 + ")";
            } else
                formula = "(" + value1 + symbol + value2 + ")";
        }
    }

    public int get_value(int a, int b, char symbol) {
        switch (symbol) {
            case '+':
                return a + b;
            case '-':
                if (a < b)
                    isSwap = true;
                return a - b;
            case '/':
                return a / b;
            case '*':
                return a * b;
        }
        return 0;
    }

    public String get_answer(int a, int b, int c, int d, char symbol) {
        int numerator, denominator;//分子和分母
        switch (symbol) {
            case '+':
                numerator = a * d + b * c;
                denominator = b * d;
                return fractionOperate(numerator, denominator);
            case '-':
                numerator = a * d - b * c;
                denominator = b * d;
                if (numerator < 0) {
                    isFu = true;
                    return fractionOperate(-numerator, denominator);
                }
                return fractionOperate(numerator, denominator);
            case '*':
                numerator = a * c;
                denominator = b * d;
                return fractionOperate(numerator, denominator);
            case '/':
                numerator = a * d;
                denominator = b * c;
                return fractionOperate(numerator, denominator);
        }
        return null;
    }

    //处理分数
    public String fractionOperate(int a, int b) {
        int zhengShu = a / b;//整数
        int numerator = a - zhengShu * b;//分子
        int i;
        int maxGongYinShu = 1;
        for (i = 1; i <= numerator; i++) {//找最大公因数,化简分数
            if (numerator % i == 0 && b % i == 0)
                maxGongYinShu = i;
        }
        String fraction;
        if (zhengShu == 0) {//分子小于分母
            if (numerator == 0)
                fraction = String.valueOf(0);
            else
                fraction = String.valueOf(numerator / maxGongYinShu) + '/' + (b / maxGongYinShu);
        } else {//分子大于分母
            if (numerator == 0)
                fraction = String.valueOf(zhengShu);
            else
                fraction = String.valueOf(zhengShu) + '’' + numerator / maxGongYinShu + '/' + b / maxGongYinShu;
        }
        return fraction;
    }
}

