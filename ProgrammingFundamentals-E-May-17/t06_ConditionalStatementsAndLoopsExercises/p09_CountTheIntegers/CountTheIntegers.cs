using System;

namespace p09_CountTheIntegers
{
    class CountTheIntegers
    {
        static void Main(string[] args)
        {
            int count = 0;
            string input = Console.ReadLine();
            int e = 0;

            while (int.TryParse(input, out e))
            {
                count++;
                input = Console.ReadLine();
            }
            Console.WriteLine(count);
        }
    }
}
