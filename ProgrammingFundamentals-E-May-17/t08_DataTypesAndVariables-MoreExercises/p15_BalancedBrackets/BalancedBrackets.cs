using System;

namespace p15_BalancedBrackets
{
    class BalancedBrackets
    {
        static void Main(string[] args)
        {
            byte n = byte.Parse(Console.ReadLine());
            sbyte br = 0;
            for (int i = 0; i < n; i++)
            {
                char input = Console.ReadLine()[0];
                if (br >= 0 && br < 2)
                {
                    if (input.Equals('('))
                        br++;
                    else if (input.Equals(')'))
                        br--;
                }
            }
            if (br == 0 ) Console.WriteLine("BALANCED");
            else Console.WriteLine("UNBALANCED");
        }
    }
}