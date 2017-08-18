using System;

namespace p06_CatchTheThief
{
    class CatchTheThief
    {
        static void Main(string[] args)
        {
            string type = Console.ReadLine();
            byte n = byte.Parse(Console.ReadLine());
            long maxType = long.MaxValue;
            long maxID = long.MinValue;
            switch (type)
            {
                case "sbyte": maxType = sbyte.MaxValue; break;
                case "int": maxType = int.MaxValue; break;
                default:
                    break;
            }
            for (int i = 0; i < n; i++)
            {
                long input = long.Parse(Console.ReadLine());
                if (input <= maxType && input > maxID)
                {
                    maxID = input;
                }
            }

            Console.WriteLine(maxID);
        }
    }
}
