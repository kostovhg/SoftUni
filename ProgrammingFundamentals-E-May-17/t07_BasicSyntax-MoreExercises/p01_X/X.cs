using System;

namespace p01_X
{
    class X
    {
        static void Main(string[] args)
        {
            int num = int.Parse(Console.ReadLine());
            for (int i = 1; i <= num; i++)
            {
                for (int j = 1; j <= num; j++)
                {
                    Console.Write((i == j || j == num - i + 1) ? "x" : " ");
                }
                Console.WriteLine();
            }
        }
    }
}
