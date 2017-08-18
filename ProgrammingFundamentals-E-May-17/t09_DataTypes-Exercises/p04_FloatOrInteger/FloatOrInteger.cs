using System;

namespace p04_FloatOrInteger
{
    class FloatOrInteger
    {
        static void Main(string[] args)
        {
            string input = Console.ReadLine();
            int output;
            if (int.TryParse(input, out output))
            {
                Console.WriteLine(output);
            }
            else
            {
                Console.WriteLine(Math.Round(double.Parse(input), 0));
            }
        }
    }
}
