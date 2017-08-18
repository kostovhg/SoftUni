using System;

namespace p06_IncrementVariable
{
    class IncrementVariable
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            byte overflows = 0;
            byte num = 0;
            for (int i = 0; i < n; i++)
            {
                num++;
                if (num == 0) overflows++;
            }
            Console.WriteLine(num);
            Console.WriteLine($"Overflowed {overflows} times");
        }
    }
}
