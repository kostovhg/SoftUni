using System;
using System.Collections.Generic;
using System.Linq;

namespace p04_MostValuedCustomer
{
    class MostValuedCustomer
    {
        static void Main(string[] args)
        {
            Dictionary<string, double> products = new Dictionary<string, double>();
            Dictionary<string, List<string>> clients = new Dictionary<string, List<string>>();
            string input = Console.ReadLine();
            // filling the products dictionary
            while (!"Shop is open".Equals(input))
            {
                string[] tokens = input.Split();
                string product = tokens[0];
                double price = double.Parse(tokens[1]);
                products.Add(product, price);
                input = Console.ReadLine();
            }

            input = Console.ReadLine();
            // filling the client dictionary
            while (!"Print".Equals(input))
            {
                if (!"Discount".Equals(input))
                {
                    string client = input.Split(':')[0];
                    string[] buyed = input
                        .Split(new char[] { ':', ',', ' ' }, StringSplitOptions.RemoveEmptyEntries)
                        .Skip(1)
                        .ToArray();
                    clients.Add(client, new List<string>());
                    foreach (string prod in buyed)
                    {
                        if (products.ContainsKey(prod))
                            clients[client].Add(prod);
                    }
                }
                else
                {
                    foreach(KeyValuePair<string, double> prod in products
                        .OrderByDescending(x => x.Value)
                        .Take(3).ToArray())
                    {
                        products[prod.Key] *= 0.9;
                    }
                }

                input = Console.ReadLine();
            }

            var spender = clients
                .OrderByDescending(x => x.Value.Sum(p => products[p]))
                .Take(1)
                .ToList()[0].Key;

            Console.WriteLine("Biggest spender: {0}", spender);
            Console.WriteLine("^Products bought:");
            foreach (string prod in clients[spender]
                .OrderByDescending(x => products[x])
                .Distinct())
            {
                Console.WriteLine("^^^{0}: {1:F2}", prod, products[prod]);
            }
            Console.WriteLine("Total: {0:F2}", clients[spender].Sum(p => products[p]));
        }
    }
}
