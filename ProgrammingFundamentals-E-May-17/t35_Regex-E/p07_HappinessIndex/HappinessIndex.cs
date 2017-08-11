using System;
using System.Text.RegularExpressions;
using System.Linq;

namespace p07_HappinessIndex
{
    class HappinessIndex
    {
        static void Main(string[] args)
        {
            string pattern = @"((?'smile'[:;][\)\]\}D\*]|[(\*c\[][;:])|(?'sad'[:;][\[\(\{c]|[D\]\)][:;]))+";
            string input = Console.ReadLine();
            int happyCount = 0;
            int sadCount = 0;
            foreach (Match match in Regex.Matches(input, pattern))
            {
                if (match.Groups["smile"].Success) happyCount++;
                else sadCount++;
            }
            double index = happyCount / (double)sadCount;
            Console.WriteLine("Happiness index: {0:F2} :{1}", index,
                (index > 1.0) ?
                (index >= 2.0 ? "D" : ")") :
                (index < 1.0 ? "(" : "|"));
            Console.WriteLine("[Happy count: {0}, Sad count: {1}]",
                happyCount, sadCount);
        }
    }
}