using System;

namespace p01_PokeMon
{
    class PokeMon
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            double half = n * 0.5;
            int m = int.Parse(Console.ReadLine());
            int y = int.Parse(Console.ReadLine());
            int count = 0;
            while (n >= m)
            {
                n -= m;
                if (half == n && y > 0)
                {
                    n /= y;
                }
                count++;
            }
            Console.WriteLine(n);
            Console.WriteLine(count);
        }
    }
}
