using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p04_NSA
{
    class NSA
    {
        static void Main(string[] args)
        {
            Dictionary<string, Dictionary<string, int>> countries = new Dictionary<string, Dictionary<string, int>>();
            string input;
            while (!"quit".Equals(input = Console.ReadLine()))
            {
                string[] tokens = input.Split(new string[] { " -> " }, StringSplitOptions.RemoveEmptyEntries);
                string country = tokens[0];
                string spy = tokens[1];
                int days = int.Parse(tokens[2]);

                if (!countries.ContainsKey(country)) countries.Add(country, new Dictionary<string, int>());
                if (!countries[country].ContainsKey(spy)) countries[country].Add(spy, 0);
                countries[country][spy] = days;
            }
            foreach (var country in countries.OrderByDescending(x => x.Value.Count))
            {
                Console.WriteLine($"Country: {country.Key}");
                foreach (var spy in country.Value.OrderByDescending(x => x.Value))
                {
                    Console.WriteLine($"**{spy.Key} : {spy.Value}");
                }
            }
        }
    }
}
