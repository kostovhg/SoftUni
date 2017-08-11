using System;
using System.Linq;
using System.Text.RegularExpressions;

namespace p03_BoomingCannon
{
    class BoomingCannon
    {
        static void Main(string[] args)

        {
            string input = Console.ReadLine();
            int distance = int.Parse(input.Split()[0]);
            int destruction = int.Parse(input.Split()[1]);
            string pattern = @"(?<=\\<{3}.{" + distance + @"})\w{1," + destruction + @"}";
            input = Console.ReadLine();
            MatchCollection words = Regex.Matches(input, pattern);
            Console.WriteLine(string.Join("/\\", 
                words.Cast<Match>()
                .Select(m => m.Value).ToArray()));

        }
    }
}
