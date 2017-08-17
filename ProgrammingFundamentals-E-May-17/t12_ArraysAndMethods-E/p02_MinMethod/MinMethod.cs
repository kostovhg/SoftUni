using System;

namespace p02_MinMethod
{
    class MinMethod
    {
        static int GetMin (int a, int b)
        {
            return (a > b) ? b : a;
        }
        static void Main(string[] args)
        {
            Console.WriteLine(GetMin(GetMin(int.Parse(Console.ReadLine()), int.Parse(Console.ReadLine())), int.Parse(Console.ReadLine())));
        }
    }
}
