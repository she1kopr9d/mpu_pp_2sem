import java.util.Stack;

public class Compf extends Stack<Character>{
    //Типы символов (скобки, знаки операций, иное).
    protected final static int SYM_LEFT = 0,
                               SYM_RIGHT = 1,
                               SYM_OPER = 2,
                               SYM_OTHER = 3;

    private int _last_move = SYM_LEFT;
    private StringBuilder _buffer;

    private Stack<Character> _brackets = new Stack<>();

    private static boolean checkPair(char open, char close){
        return (open == '(' && close == ')') || (open == '[' && close == ']') || (open == '{' && close == '}');
    }

    private int symType(char c){
        switch(c){
            case '(':
            case '[':
            case '{':
                return SYM_LEFT;
            case ')':
            case ']':
            case '}':
                return SYM_RIGHT;
            case '+':
            case '-':
            case '*':
            case '/':
                return SYM_OPER;
            default:
                return symOther(c);
        }
    }

    private void checkBrackets(char c){
        try{
            char open = _brackets.pop();
            if (!checkPair(open, c)){
                System.out.println("Недопустимая последовательность скобок");
                System.exit(0);
            }
        }
        catch (Exception e){
            System.out.println("Недопустимая последовательность скобок");
            System.exit(0);
        }
    }

    private boolean isUnary(char operator){
        if (operator != '+' && operator != '-'){
            return false;
        }
        if (_last_move != SYM_LEFT){
            return false;
        }
        return true;
    }

    private void processSymbol(char c){
        int move = symType(c);
        if ((move != SYM_OTHER) && (_last_move == SYM_OTHER)){
            nextOther(_buffer.toString());
        }
        switch(move){
            case SYM_LEFT:
                _brackets.push(c);
                push(c);
                break;
            case SYM_RIGHT:
                checkBrackets(c);
                processSuspendedSymbols(c);
                pop();
                break;
            case SYM_OPER:
                if (isUnary(c)){
                    nextOther('0');
                }
                processSuspendedSymbols(c);
                push(c);
                break;
            case SYM_OTHER:
                if (_last_move != SYM_OTHER){
                    _buffer = new StringBuilder();
                }
                _buffer.append(c);
                break;
        }
        _last_move = move;
    }

    private void processSuspendedSymbols(char c){
        while(precedes(peek(), c))
            nextOper(pop());
    }

    private int priority(char c){
        return c == '+' || c == '-' ? 1 : 2;
    }

    private boolean precedes(char a, char b){
        if(symType(a) == SYM_LEFT) return false;
        if(symType(b) == SYM_RIGHT) return true;

        return priority(a) >= priority(b);
    }

    protected int symOther(char c){
        if (c < 'a' || c > 'z'){
            System.out.println("Недопустимый символ: " + c);
            System.exit(0);
        }

        return SYM_OTHER;
    }

    protected void nextOper(char c){
        System.out.print("" + c + " ");
    }

    protected void nextOper(String s){
        System.out.print("" + s + " ");
    }

    protected void nextOther(char c){
        nextOper(c);
    }

    protected void nextOther(String c){
        nextOper(c);
    }

    public void compile(char[] str){
        processSymbol('(');

        for(int i = 0; i < str.length; i++)
            processSymbol(str[i]);

        processSymbol(')');

        System.out.print("\n");
    }
}
