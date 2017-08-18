using System;

namespace p07_SentenceTheThief
{
    class SentenceTheThief
    {
        static void Main(string[] args)
        {
            string type = Console.ReadLine();
            byte n = byte.Parse(Console.ReadLine());
            long maxType = long.MaxValue;
            long minType = long.MinValue;
            long maxID = long.MinValue;
            switch (type)
            {
                case "sbyte":
                    maxType = sbyte.MaxValue;
                    minType = sbyte.MinValue;
                    break;
                case "int":
                    maxType = int.MaxValue;
                    minType = int.MinValue;
                    break;
                default:
                    break;
            }
            for (int i = 0; i < n; i++)
            {
                long input = long.Parse(Console.ReadLine());
                if ((input <= maxType && input >= minType) && input > maxID)
                {
                    maxID = input;
                }
            }
            double years = Math.Ceiling((double)maxID / ( ( maxID < 0 ) ? sbyte.MinValue : sbyte.MaxValue));
            Console.WriteLine($"Prisoner with id {maxID} is sentenced to {years} year" + ((years > 1) ? "s" : ""));
        }
    }
}
