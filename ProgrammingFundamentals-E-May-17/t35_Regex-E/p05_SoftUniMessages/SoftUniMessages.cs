using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace p05_SoftUniMessages
{
    class SoftUniMessages
    {
        static void Main(string[] args)
        {
            Regex regex = new Regex(@"^(\d+)(?'code'[a-zA-Z]+)([^a-zA-Z]+)$");
            string input;
            int count;
            while (!"Decrypt!".Equals(input = Console.ReadLine()))
            {
                string coded = regex.Match(input).Groups["code"].Value;
                if ((count = int.Parse(Console.ReadLine()))
                    != coded.Length) continue;
                Console.Write("{0} = ", coded);
                foreach (int index in new Regex(@"\d")
                    .Matches(input).Cast<Match>()
                    .Select(v => int.Parse(v.Value))
                    .Where(x => x < coded.Length).ToArray())
                {
                    Console.Write(coded[index]);
                }
                Console.WriteLine();
            }
        }
    }
}
