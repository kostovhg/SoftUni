using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace p04_GroceryShop
{
    class GroceryShop
    {
        static void Main(string[] args)
        {
            string input = "";
            Dictionary<string, double> products = new Dictionary<string, double>();
            Regex regex = new Regex(@"^(?'prod'[A-Z][a-z]+):(?'price'\d+.\d{2})$");
            while (!"bill".Equals(input = Console.ReadLine()))
            {
                if (!regex.IsMatch(input)) continue;
                string prod = regex.Match(input).Groups["prod"].Value;
                double price = double.Parse(regex.Match(input).Groups["price"].Value);
                if (!products.ContainsKey(prod)) products.Add(prod, 0.0);
                products[prod] = price;
            }
            foreach (var prod in products
                .OrderByDescending(p => p.Value))
            {
                Console.WriteLine("{0} costs {1:F2}", prod.Key, prod.Value);
            }
        }
    }
}
