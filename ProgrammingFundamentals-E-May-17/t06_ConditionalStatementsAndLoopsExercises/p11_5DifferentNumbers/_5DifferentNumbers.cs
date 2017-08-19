using System;

namespace p11_5DifferentNumbers
{
    class _5DifferentNumbers
    {
        static void Main(string[] args)
        {
            int num1 = int.Parse(Console.ReadLine());
            int num2 = int.Parse(Console.ReadLine());
            int min = Math.Min(num1, num2);
            int max = Math.Max(num1, num2);
            if (max - min < 4)
            {
                Console.WriteLine("No");
                return;
            }
            for (int i = min; i < max - 3; i++)
            {
                for (int j = i + 1; j < max - 2; j++)
                {
                    for (int k = j + 1; k < max - 1; k++)
                    {
                        for (int l = k + 1 ; l < max ; l++)
                        {
                            for (int n = l + 1; n <= max; n++)
                            {
                                Console.WriteLine($"{i} {j} {k} {l} {n}");
                            }
                        }
                    }
                }
            }
        }
    }
}
