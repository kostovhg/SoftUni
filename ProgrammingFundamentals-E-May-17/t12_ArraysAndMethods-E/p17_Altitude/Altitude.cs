using System;

namespace p17_Altitude
{
    class Altitude
    {
        static void Main(string[] args)
        {
            string[] arr = Console.ReadLine().Split();
            double altitude = double.Parse(arr[0]);
            for (int i = 1; i < arr.Length - 1; i += 2)
            {
                if (arr[i].Equals("up"))
                {
                    altitude += double.Parse(arr[i+1]);
                }
                else
                {
                    altitude -= double.Parse(arr[i+1]);
                }
                if (altitude <= 0)
                {
                    Console.WriteLine("crashed");
                    return;
                }
            }
            Console.WriteLine($"got through safely. current altitude: {altitude}m");
        }
    }
}
