using System;

namespace p01_HornetWings
{
    class HornetWings
    {
        static void Main(string[] args)
        {
            ulong flaps = uint.Parse(Console.ReadLine());
            double distance = double.Parse(Console.ReadLine());
            ulong endurance = uint.Parse(Console.ReadLine());
            distance *= (flaps / 1000.0);
            ulong time = (flaps / 100) + (flaps / endurance) * 5;
            Console.WriteLine("{0:F2} m.", distance);
            Console.WriteLine("{0} s.", time);
        }
    }
}
