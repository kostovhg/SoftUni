using System;

namespace p07_FromTerabytesToBits
{
    class FromTerabytesToBits
    {
        static void Main(string[] args)
        {
            double inputTerabytes = double.Parse(Console.ReadLine());
            Console.WriteLine(8796093022208 * inputTerabytes);
        }
    }
}
