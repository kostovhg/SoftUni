using System;

namespace p03_WaterOverflow
{
    class WaterOverflow
    {
        static void Main(string[] args)
        {
            byte n = byte.Parse(Console.ReadLine());
            byte tank = 0;
            ushort add = 0;
            for (int i = 0; i < n; i++)
            {
                add = ushort.Parse(Console.ReadLine());
                if (add + tank > byte.MaxValue)
                {
                    Console.WriteLine("Insufficient capacity!");
                }
                else
                {
                    tank += (byte)add;
                }
            }
            Console.WriteLine(tank);
        }
    }
}
