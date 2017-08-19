using System;

namespace p12_TestNumbers
{
    class TestNumbers
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            int m = int.Parse(Console.ReadLine());
            int max = int.Parse(Console.ReadLine());
            int sum = 0;
            int count = 0;
            bool end = false;

            for (int i = n; i >= 1 && !end; i--)
            {
                for (int j = 1; j <= m && !end; j++)
                {
                    sum += 3 * (i * j);
                    count++;
                    if (sum >= max) end = true;
                }
                if (end) break;
            }

            Console.WriteLine($"{count} combinations");
            Console.WriteLine($"Sum: {sum}" + (end ? $" >= {max}" : ""));
        }
    }
}
