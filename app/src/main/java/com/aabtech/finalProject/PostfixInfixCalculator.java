package com.aabtech.finalProject;

        import java.util.*;

/**
 * A class to evaluate postfix and infix expressions
 */

public class PostfixInfixCalculator {

    /**
     * Evaluate infix expressions
     */

    /**
     * Converts an infix expression to a postfix expression
     *
     * @param infix
     *            the string that contains the infix expression
     * @return a string that contains the expression written in postfix notation
     * // @throws ExpressionFormatException
     *             if the infix expression is invalid
     */
    public static String toPostfix(String infix) {

        String postfix = "";

        try {

            boolean unary = true; // is the current operator unary?
            Stack<String> stack = new Stack<String>();
            StringTokenizer st = new StringTokenizer(infix, "()+-/%* ", true);
            while (st.hasMoreTokens()) {
                String token = st.nextToken().trim();
                if (token.equals("")) { // skip any empty token
                } else if (token.equals("(")) {
                    stack.push(token);
                } else if (token.equals(")")) {
                    String op;
                    while (!(op = stack.pop()).equals("(")) {
                        postfix += " " + op;
                    }
                } else if (token.equals("*")
                        || // an operator
                        token.equals("+") || token.equals("-")
                        || token.equals("%") || token.equals("/")) {
                    if (unary) {
                        token = "u" + token;
                        // a unary op always goes on
                        // the stack without popping any other op
                        stack.push(token);
                    } else {
                        int p = operatorPrecedence(token);
                        while (!stack.isEmpty() && !stack.peek().equals("(")
                                && operatorPrecedence(stack.peek()) >= p) {
                            String op = stack.pop();
                            postfix += " " + op;
                        }
                        stack.push(token);
                    }
                    unary = true; // if an operator is after this one, it
                    // has to be unary
                } else { // an operand
                    Integer.parseInt(token); // just to check that
                    // it is a number
                    // If not a number, an exception is
                    // thrown
                    postfix += " " + token;
                    unary = false; // any operator after an operand is binary
                }
            }
            while (!stack.isEmpty()) {
                String op = stack.pop();
                postfix += " " + op;
            }

        } catch (EmptyStackException ese) {
            //   throw new ExpressionFormatException();
        } catch (NumberFormatException nfe) {
            //  throw new ExpressionFormatException();
        }

        return postfix;
    }

    /**
     * Evaluates a postfix expression
     *
     * @param postfix
     *            the string that contains the postfix expression
     * @return the integer value of the expression
     * //@throws ExpressionFormatException
     *             if the postfix expression is invalid
     */
    public static int computePostfix(String postfix) {
        int result = -1;
        try {
            Stack<Integer> stack = new Stack<Integer>();
            StringTokenizer st = new StringTokenizer(postfix);
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                if (token.equals("*")
                        || // an operator
                        token.equals("+") || token.equals("-")
                        || token.equals("%") || token.equals("/")
                        || token.equals("u+") || token.equals("u-")) {
                    applyOperator(token, stack);
                } else { // an operand
                    stack.push(new Integer(token));
                }
            }
            result = ((Integer) stack.pop()).intValue();
            if (!stack.isEmpty()) { // the stack should be empty
                //throw new ExpressionFormatException();
            }
            return result;
        } catch (EmptyStackException ese) {
            //throw new ExpressionFormatException();
        } catch (NumberFormatException nfe) {
            //throw new ExpressionFormatException();
        }

        return result;
    }

    /**
     * Applies the given operator to the top operand or the top two operands on
     * the given stack. Possible operators are unary + and - written as "u+" and
     * "u-", and binary "+", "-", "%", and "/"
     *
     * @param operator
     *            the operator to apply
     * @param s
     *            the stack of the operands
     * @throws EmptyStackException
     *             if the stack is empty
     * @throws IllegalArgumentException
     *             if the operator is not /,*,%,+,-,u-,u+
     *
     *             post condition: the operator is applied to the top operand or
     *             to the top two operands on the stack. The operand(s) is/are
     *             popped from the stack. The result is pushed on the stack
     */
    private static void applyOperator(String operator, Stack<Integer> s) {
        int op1 = s.pop();
        if (operator.equals("u-")) {
            s.push(-op1);
        } else if (operator.equals("u+")) {
            s.push(op1);
        } else { // binary operator
            int op2 = s.pop();
            int result;
            if (operator.equals("+")) {
                result = op2 + op1;
            } else if (operator.equals("-")) {
                result = op2 - op1;
            } else if (operator.equals("/")) {
                result = op2 / op1;
            } else if (operator.equals("%")) {
                result = op2 % op1;
            } else if (operator.equals("*")) {
                result = op2 * op1;
            } else {
                throw new IllegalArgumentException();
            }
            s.push(result);
        }
    }

    /**
     * Returns an integer indicating the order of precedence of an operator
     * given as a string. Unary + and - return 2, *, / and % return 1 and binary
     * + and - return 0.
     *
     * @param operator
     *            the operator given as a string
     * //@param binary
     *            indicates if the operator is binary (true) or unary (false)
     * @return the precedence order of a given operator
     * //@throws ExpressionFormatExpression
     *             if the operator is not one of "u+", "u-", "+", "-", "/", "%",
     *             and "*"
     */
    private static int operatorPrecedence(String operator) {
        if (operator.equals("u-") || operator.equals("u+")) {
            return 2;
        } else if (operator.equals("*") || operator.equals("/")
                || operator.equals("%")) {
            return 1;
        } else if (operator.equals("-") || operator.equals("+")) {
            return 0;
        } else {
            //   throw new ExpressionFormatException();
        }
        return -1;
    }
}