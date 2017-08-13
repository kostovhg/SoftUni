using System;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace p03_RageQuit
{
    class RageQuit
    {
        static void Main(string[] args)
        {
            string pattern = @"(?'chars'[^0-9]+)(?'count'[0-9]{1,2})";
            StringBuilder output = new StringBuilder();
            foreach (Match m in Regex.Matches(Console.ReadLine(), pattern))
            {
                output.Append(string.Join("",
                    Enumerable.Repeat(m.Groups["chars"].Value.ToUpper(),
                    int.Parse(m.Groups["count"].Value)))
                    );
            }
            Console.WriteLine("Unique symbols used: {0}", output.ToString().Distinct().Count());
            Console.WriteLine(output);
        }
    }
}
