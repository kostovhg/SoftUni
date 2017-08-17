using System;

namespace p01_Wormtest
{
    class Wormtest
    {
        static void Main(string[] args)
        {
            uint length = uint.Parse(Console.ReadLine()) * 100;
            double width = double.Parse(Console.ReadLine());
            if (width == 0)
            {
                Console.WriteLine("0.00");
            }
            else if(length % width == 0)
            {
                Console.WriteLine("{0:F}", length * width);
            }
            else
            {
                Console.WriteLine("{0:F}%", (length / width) * 100);
            }
           
        }
    }
}
