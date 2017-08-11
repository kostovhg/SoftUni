using System;
using System.Collections.Generic;
using System.Linq;

namespace p03_ShoppingSpree
{
    class ShoppingSpree
    {
        static void Main(string[] args)
        {
            Dictionary<string, double> products = new Dictionary<string, double>();
            double budget = double.Parse(Console.ReadLine());
            string input = Console.ReadLine();
            while (!"end".Equals(input))
            {
                string[] tokens = input.Split();
                string product = tokens[0];
                double price = double.Parse(tokens[1]);
                if (!products.ContainsKey(tokens[0]))
                    products.Add(product, price);
                else if (products[product] >= price)
                    products[product] = price;
                input = Console.ReadLine();
            }

            if (products.Sum(x => x.Value) > budget)
            {
                Console.WriteLine("Need more money... Just buy banichka");
            }
            else
            {
                foreach (KeyValuePair<string, double> product in products
                    .OrderByDescending(x => x.Value)
                    .ThenBy(x => x.Key.Length)
                    )
                {
                    Console.WriteLine("{0} costs {1:F2}", product.Key, product.Value);
                }
            }
        }
    }
}
