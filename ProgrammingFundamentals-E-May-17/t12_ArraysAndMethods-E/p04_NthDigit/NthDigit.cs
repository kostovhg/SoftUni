using System;

namespace p04_NthDigit
{
    class NthDigit
    {
        static int FindNthDigit(long number, int index)
        {
            int digits = 1;
            while (number > 0)
            {
                if (digits != index)
                    number /= 10;
                else
                    break;
                digits++;
            }
            return (int)(number % 10);
        }
        static void Main(string[] args)
        {
            long number = long.Parse(Console.ReadLine());
            int index = int.Parse(Console.ReadLine());
            Console.WriteLine(FindNthDigit(number, index));
        }
    }
}
