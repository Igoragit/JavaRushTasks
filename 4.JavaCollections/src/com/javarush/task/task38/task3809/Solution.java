package com.javarush.task.task38.task3809;

/* 
Annotation + Reflection
*/

public class Solution {
    public static void main(String[] args) throws IllegalAccessException {
        JavaRushBankAccount account = new JavaRushBankAccount("Mr.Smith");
        System.out.println("�������� �1:");
        ReflectionAnnotationUtil.check(account);

        System.out.println("�������� �2:");
        account.setAmount(100);
        ReflectionAnnotationUtil.check(account);

        System.out.println("�������� �3:");
        ReflectionAnnotationUtil.check(new IncorrectAccount());
/* ��������� �����:

�������� �1:
���� amount � ������ JavaRushBankAccount ����� ��������� LongPositive, �� ��� �������� �� �������������.
�������� �2:
�������� �3:
���� amountString � ������ IncorrectAccount ����� ��������� LongPositive, �� ��� ��� String.

*/
    }
}
