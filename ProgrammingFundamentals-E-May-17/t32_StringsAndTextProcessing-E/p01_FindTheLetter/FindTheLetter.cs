using System;
using System.Linq;

namespace p01_FindTheLetter
{
    static class FindTheLetter 
    {
        static void Main(string[] args)
        {
            string input = Console.ReadLine();
            string[] tokens = Console.ReadLine()
                .Split();
            char toFind = tokens[0].ToCharArray()[0];
            int occurence = ushort.Parse(tokens[1]);
            // take each index untill occurence is greater than 0
            // as the same time lowering occurence with 1 every
            // thime when we have character equal to "toFind"
            // taken from https://stackoverflow.com/a/6450725
            // by https://stackoverflow.com/users/664533/wayne-maurer
            int res = input
                .TakeWhile(x => 
                (occurence -= (x == toFind ? 1 : 0)) > 0)
                .Count();
            // count resulted characters. If all of them are taken
            // by .TakeWale, that means the "occurence"
            Console.WriteLine(res != input.Length ? res.ToString() : "Find the letter yourself!");
            
        }
    }
}
