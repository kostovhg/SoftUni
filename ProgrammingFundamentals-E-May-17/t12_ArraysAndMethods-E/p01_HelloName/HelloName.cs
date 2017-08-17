using System;

namespace p01_HelloName
{
    class HelloName
    {
        static void Main(string[] args)
        {
            printGreetings(Console.ReadLine());
        }

        private static void printGreetings(string v)
        {
            Console.WriteLine("Hello, {0}!", v); ;
        }
    }
}
