using System;

namespace p01_CharityMarathon
{
    class CharityMarathon
    {
        static void Main(string[] args)
        {
            ulong days = ulong.Parse(Console.ReadLine());
            ulong count = ulong.Parse(Console.ReadLine());
            ulong laps = ulong.Parse(Console.ReadLine());
            ulong len = ulong.Parse(Console.ReadLine());
            ulong cap = ulong.Parse(Console.ReadLine()) * days;
            decimal money = decimal.Parse(Console.ReadLine());

            decimal totalKm = (((count > cap) ? cap : count) * laps * len) / 1000.0M;
            Console.WriteLine("Money raised: {0:F2}", money * totalKm);

        }
    }
}
