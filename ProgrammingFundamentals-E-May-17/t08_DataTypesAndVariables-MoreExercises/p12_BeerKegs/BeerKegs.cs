using System;

namespace p12_BeerKegs
{
    class BeerKegs
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            double maxVolume = 0.0;
            string winerKeg = "";

            for (int i = 0; i < n; i++)
            {
                string name = Console.ReadLine();
                double radius = double.Parse(Console.ReadLine());
                int height = int.Parse(Console.ReadLine());
                double vol = Math.PI * radius * radius * height;
                if (maxVolume < vol)
                {
                    maxVolume = vol;
                    winerKeg = name;
                }
            }
            Console.WriteLine(winerKeg);
        }
    }
}
