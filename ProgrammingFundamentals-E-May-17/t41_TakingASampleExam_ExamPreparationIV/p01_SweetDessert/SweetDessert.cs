using System;

namespace p01_SweetDessert
{
    class SweetDessert
    {
        static void Main(string[] args)
        {
            decimal cash = decimal.Parse(Console.ReadLine());
            uint guests = uint.Parse(Console.ReadLine());
            decimal bananaPrice = decimal.Parse(Console.ReadLine());
            decimal eggsPrice = decimal.Parse(Console.ReadLine());
            decimal berriesPrice = decimal.Parse(Console.ReadLine());
            uint portions = guests / 6;
            if (guests % 6 > 0) portions++;
            decimal price = portions * (2 * bananaPrice + 4 * eggsPrice + 0.2m * berriesPrice);
            if (cash >= price)
            {
                Console.WriteLine($"Ivancho has enough money - it would cost {price:F}lv.");
            }
            else
            {
                Console.WriteLine($"Ivancho will have to withdraw money - he will need {price - cash:F}lv more.");
            }
        }
    }
}
