using System;

namespace p12_VariableInHexadecimalFormat
{
    class VariableInHexadecimalFormat
    {
        static void Main(string[] args)
        {
            string input = Console.ReadLine();
            Console.WriteLine(Convert.ToInt32(input, 16));
        }
    }
}
