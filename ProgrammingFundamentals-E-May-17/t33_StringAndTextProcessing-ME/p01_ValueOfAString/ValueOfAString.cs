using System;
using System.Linq;

namespace p01_ValueOfAString
{
    class ValueOfAString
    {
        static void Main(string[] args)
        {
            string input = Console.ReadLine();
            bool upper = ("UPPERCASE").Equals(Console.ReadLine());
            int sum = CalcValOfString(input, upper);
            
            Console.WriteLine("The total sum is: {0}", sum);
        }

        private static int CalcValOfString(string input, bool upper)
        {
            char lowerBound = '`';
            char upperBound = '{';
            if (upper)
            {
                lowerBound = '@';
                upperBound = '[';
            }
            return input.Where(x => x > lowerBound && x < upperBound).Select(c => (int)c).Sum();
        }
    }
}
