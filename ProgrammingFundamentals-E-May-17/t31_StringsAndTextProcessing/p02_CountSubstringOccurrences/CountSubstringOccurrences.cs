using System;

namespace p02_CountSubstringOccurrences
{
    class CountSubstringOccurrences
    {
        static void Main(string[] args)
        {
            string input = Console.ReadLine().ToLower();
            string substring = Console.ReadLine().ToLower();
            int count = 0;
            int index = input.IndexOf(substring);
            while (index != -1)
            {
                count++;
                index = input.IndexOf(substring, index + 1);
            }
            Console.WriteLine(count);
        }
    }
}
