using System;

namespace p08_HouseBuilder
{
    class HouseBuilder
    {
        static void Main(string[] args)
        {
            string num1 = Console.ReadLine();
            string num2 = Console.ReadLine();
            sbyte sbPrice = 0;
            int iPrice = 0;

            if (sbyte.TryParse(num1, out sbPrice))
            {
                iPrice = int.Parse(num2);
            }
            else
            {
                iPrice = int.Parse(num1);
                sbPrice = sbyte.Parse(num2);
            }
            long price = (long)iPrice * 10 + sbPrice * 4;
            Console.WriteLine($"{price}");
        }
    }
}
