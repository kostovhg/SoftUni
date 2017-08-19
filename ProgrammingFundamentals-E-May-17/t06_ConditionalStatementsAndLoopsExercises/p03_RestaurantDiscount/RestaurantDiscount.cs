using System;

namespace p03_RestaurantDiscount
{
    class RestaurantDiscount
    {
        static void Main(string[] args)
        {
            int iGroupSize = int.Parse(Console.ReadLine());
            string sPackageType = Console.ReadLine();
            double price = 0.0;
            string sHallName = "";

            if (iGroupSize <= 50)
            {
                price = 2500.0;
                sHallName = "Small Hall";
            }
            else if (iGroupSize <= 100)
            {
                price = 5000.0;
                sHallName = "Terrace";
            }
            else if (iGroupSize <= 120)
            {
                price = 7500.0;
                sHallName = "Great Hall";
            }

            switch (sPackageType)
            {
                case "Normal": price = (price + 500) * 0.95; break;
                case "Gold": price = (price + 750) * 0.9; break;
                case "Platinum": price = (price + 1000) * 0.85; break;
                default:
                    break;
            }

            if (!sHallName.Equals(""))
            {
                Console.WriteLine($"We can offer you the {sHallName}");
                Console.WriteLine($"The price per person is {price / iGroupSize:F2}$");
            }
            else
            {
                Console.WriteLine("We do not have an appropriate hall.");
            }
            
        }
    }
}
