package cn.wh.study.chapter_01;

public class Calculator {
  public int evaluate(String expression) {
    int sum = 0;
    for (String summand: expression.split("\\+"))
      sum += Integer.parseInt(summand);
    return sum;
  }

  public double add(double one,double two){
    return one+two;
  }
}