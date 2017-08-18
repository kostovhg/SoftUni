using System;

namespace p03_Megapixels
{
    class Megapixels
    {
        static void Main(string[] args)
        {
            int width = int.Parse(Console.ReadLine());
            int height = int.Parse(Console.ReadLine());
            Console.WriteLine($"{width}x{height} => {Math.Round((width * height)/1000000d, 1)}MP");
        }
    }
}
