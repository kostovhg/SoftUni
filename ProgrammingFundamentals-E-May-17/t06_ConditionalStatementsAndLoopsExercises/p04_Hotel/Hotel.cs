using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p04_Hotel
{
    class Hotel
    {
        static void Main(string[] args)
        {
            string sMonth = Console.ReadLine();
            int nights = int.Parse(Console.ReadLine());
            double[] prices = new double[3];
            int freeNight = 0;

            switch (sMonth)
            {
                case "May":
                case "October":
                    prices[0] = nights > 7 ? 50.0 * 0.95 : 50.0; prices[1] = 65.0; prices[2] = 75.0;
                    break;
                case "June":
                case "September":
                    prices[0] = 60.0; prices[1] = nights > 14 ? 72.0 * 0.9 : 72.0; prices[2] = 82.0;
                    break;
                case "July":
                case "August":
                case "December":
                    prices[0] = 68.0; prices[1] = 77.0; prices[2] = nights > 14 ? 89.0 * 0.85 : 89.0;
                    break;
                default:
                    break;
            }

            if (nights > 7 && (sMonth.Equals("September") || sMonth.Equals("October"))) freeNight = 1;

            Console.WriteLine("Studio: {0:F2} lv.", prices[0] * (nights - freeNight));
            Console.WriteLine($"Double: {prices[1] * nights:F2} lv.");
            Console.WriteLine($"Suite: {prices[2] * nights:F2} lv.");
        }
    }
}
