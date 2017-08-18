using System;

namespace p05_WeatherForecast
{
    class WeatherForecast
    {
        static void Main(string[] args)
        {
            string sNum = Console.ReadLine();
            long lNum = 0;
            if (long.TryParse(sNum, out lNum))
            {
                if (lNum > int.MaxValue || lNum < int.MinValue)
                {
                    Console.WriteLine("Windy");
                }
                else if (lNum > sbyte.MaxValue || lNum < sbyte.MinValue)
                {
                    Console.WriteLine("Cloudy");
                }
                else
                {
                    Console.WriteLine("Sunny");
                }
            }
            else
            {
                Console.WriteLine("Rainy");
            }
        }
    }
}
