using System;

namespace p08_TravelingAtLightSpeed
{
    class TravelingAtLightSpeed
    {
        static void Main(string[] args)
        {
            decimal lightYears = decimal.Parse(Console.ReadLine());
            uint reminder = 0;
            uint time = 0;
            reminder = (uint)((lightYears * 31500000M) % 604800M);
            time = (uint)((lightYears * 31500000M) / 604800M);
            Console.WriteLine($"{time} weeks");
            time = reminder / 86400;
            reminder = (reminder % 86400);
            Console.WriteLine($"{time} days");
            time = reminder / 3600;
            reminder = reminder % 3600;
            Console.WriteLine($"{time} hours");
            time = reminder / 60;
            Console.WriteLine($"{time} minutes");
            time = reminder % 60;
            Console.WriteLine($"{time} seconds");
        }
    }
}
