using System;

namespace p01_SoftUniCoffeOrders
{
    class SoftUniCoffeOrders
    {
        static void Main(string[] args)
        {
            int ordersNumber = int.Parse(Console.ReadLine());
            uint capsulesCount;
            DateTime orderDate;
            decimal captulePrice;
            decimal total = 0;
            for (int i = 0; i < ordersNumber; i++)
            {
                captulePrice = decimal.Parse(Console.ReadLine());
                orderDate = DateTime.ParseExact(Console.ReadLine(), "d/M/yyyy", 
                    System.Globalization.CultureInfo.InvariantCulture);
                capsulesCount = uint.Parse(Console.ReadLine());
                decimal price = ((DateTime.DaysInMonth(orderDate.Year, orderDate.Month) * capsulesCount) *
                                 captulePrice);
                Console.WriteLine("The price for the coffee is: ${0:F2}", price);
                total += price;
            }
            Console.WriteLine("Total: ${0:F2}", total);
        }
    }
}
