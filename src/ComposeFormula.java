/**
 * 组合两个算式
 */
public class ComposeFormula {
    int value;
    String formula, answer;
    CreateFormula f1;
    CreateFormula f2;
    char symbol;
    public char[] all_symbol = new char[]{'+', '-', '*', '/'};

    public ComposeFormula(int range) {
        this.f1 = new CreateFormula(range);
        this.f2 = new CreateFormula(range);
        symbol = all_symbol[(int) (Math.random() * 4)];
        if (f1.isFraction && f2.isFraction) {//两个都是分式的情况
            if (symbol == '-' && f1.value1 * f2.value2 < f2.value1 * f1.value2) {
                formula = this.f2.formula + symbol + this.f1.formula;
            } else {
                formula = this.f1.formula + symbol + this.f2.formula;
            }
            answer = f1.get_answer(f1.value1, f1.value2, f2.value1, f2.value2, symbol);
        } else if (!f1.isFraction && !f2.isFraction) {//都不是分式
            if (f2.value == 0 && symbol == '/') {//若分母为0，则重新生成随机数,直到分母不为0
                do {
                    this.f2 = new CreateFormula(range);
                } while (f2.value != 0);
            } else {
                if (symbol == '/' && f1.value % f2.value != 0) {//两个式子不能整除时
                    answer = f1.fractionOperate(f1.value, f2.value);
                    formula = this.f1.formula + symbol + this.f2.formula;
                } else {//两个式子可以整除
                    value = f1.get_value(f1.value, f2.value, symbol); //得到答案，仅仅是整数   （之后分式功能写完后要分分式的运算和整数的运算）
                    if (value < 0) {//结果为负数，答案取反，调换两个算式的位置
                        value = -value;
                        answer = "" + value;
                        formula = this.f2.formula + symbol + this.f1.formula;
                    } else {
                        answer = "" + value;
                        formula = this.f1.formula + symbol + this.f2.formula;
                    }
                }
            }
        } else if (f1.isFraction) {//f1为分式
            if (symbol == '-' && f1.value1 < f2.value * f1.value2) {
                formula = this.f2.formula + symbol + this.f1.formula;
            } else {
                formula = this.f1.formula + symbol + this.f2.formula;
            }
            answer = f1.get_answer(f1.value1, f1.value2, f2.value, 1, symbol);
        } else {//f2是分式
            if (symbol == '-' && f1.value * f2.value2 < f2.value1) {
                formula = this.f2.formula + symbol + this.f1.formula;
            } else {
                formula = this.f1.formula + symbol + this.f2.formula;
            }
            answer = f1.get_answer(f1.value, 1, f2.value1, f2.value2, symbol);
        }
    }
}
