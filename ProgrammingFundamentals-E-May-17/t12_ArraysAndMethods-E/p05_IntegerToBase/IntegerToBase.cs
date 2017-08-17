using System;

namespace p05_IntegerToBase
{
    class _IntegerToBase
    {
        static string IntegerToBase(long number, int toBase)
        {
            string result = string.Empty;
            while (number > 0)
            {
                result = (number % toBase).ToString() + result;
                number /= toBase;
            }

            return result;
        }
        static void Main(string[] args)
        {
            long num = long.Parse(Console.ReadLine());
            int toBase = int.Parse(Console.ReadLine());
            Console.WriteLine(IntegerToBase(num, toBase));
        }
    }
}
