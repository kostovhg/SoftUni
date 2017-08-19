using System;

namespace p13_GameOfNumbers
{
    class GameOfNumbers
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            int m = int.Parse(Console.ReadLine());
            int magic = int.Parse(Console.ReadLine());
            int count = 0;
            int found1 = 0;
            int found2 = 0;

            for (int i = n; i <= m; i++)
            {
                for (int j = n; j <= m; j++)
                {
                    count++;
                    if (i + j == magic)
                    {
                        found1 = i;
                        found2 = j;
                    }
                }
            }
            if (found2 != 0)
            {
                Console.WriteLine($"Number found! {found1} + {found2} = {magic}");
            }
            else
            {
                Console.WriteLine($"{count} combinations - neither equals {magic}");
            }
        }
    }
}
