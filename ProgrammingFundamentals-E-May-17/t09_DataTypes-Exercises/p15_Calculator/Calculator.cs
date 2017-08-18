using System;

namespace p15_Calculator
{
    class Calculator
    {
        static void Main(string[] args)
        {
            int num1 = int.Parse(Console.ReadLine());
            char oper = Console.ReadLine()[0];
            int num2 = int.Parse(Console.ReadLine());
            int result = 0;
            switch (oper)
            {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': result = num1 / num2; break;
                default:
                    break;
            }
            Console.WriteLine($"{num1} {oper} {num2} = {result}");

        }
    }
}
