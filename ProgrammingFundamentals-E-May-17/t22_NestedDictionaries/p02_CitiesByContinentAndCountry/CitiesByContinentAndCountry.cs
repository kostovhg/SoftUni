using System;
using System.Collections.Generic;

namespace p02_CitiesByContinentAndCountry
{
    class CitiesByContinentAndCountry
    {
        static void Main(string[] args)
        {
            var continentsData = new Dictionary<string, Dictionary<string, List<string>>>();
            int inp = int.Parse(Console.ReadLine());
            for (int i = 0; i < inp; i++)
            {
                string[] input = Console.ReadLine()
                    .Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries);
                string continent = input[0];
                string country = input[1];
                string city = input[2];

                if (!continentsData.ContainsKey(continent))
                {
                    continentsData.Add(continent, new Dictionary<string, List<string>>());
                }
                if (!continentsData[continent].ContainsKey(country))
                {
                    continentsData[continent].Add(country, new List<string>());
                }
                continentsData[continent][country].Add(city);
            }

            foreach (string cont in continentsData.Keys)
            {
                Console.WriteLine("{0}:", cont);
                foreach (string state in continentsData[cont].Keys)
                {
                    Console.WriteLine("  {0} -> {1}",
                        state,
                        string.Join(", ", continentsData[cont][state]));
                }
            }
        }
    }
}
