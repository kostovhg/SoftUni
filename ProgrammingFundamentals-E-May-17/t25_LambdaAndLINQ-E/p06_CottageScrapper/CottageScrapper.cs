using System;
using System.Collections.Generic;
using System.Linq;

namespace p06_CottageScrapper
{
    class CottageScrapper
    {
        static void Main(string[] args)
        {
            List<KeyValuePair<string, int>> logs = new List<KeyValuePair<string, int>>();
            string input = Console.ReadLine();

            while (!"chop chop".Equals(input))
            {
                string[] tokens = input.Split(new string[] { " -> " }, StringSplitOptions.None );
                string type = tokens[0];
                int length = int.Parse(tokens[1]);
                logs.Add(new KeyValuePair<string, int> (type, length));

                input = Console.ReadLine();
            }

            string validType = Console.ReadLine();
            int validLength = int.Parse(Console.ReadLine());

            double price = Math.Round(logs.Average(d => d.Value), 2);

            double usedLogs = logs.
                Where(d => validType.Equals(d.Key) && d.Value >= validLength).Sum(d => d.Value);
            
            double unUsedLogs = logs.Where(d => !validType.Equals(d.Key) || d.Value < validLength).Sum(d => d.Value);

            usedLogs = Math.Round(usedLogs * price, 2);
            unUsedLogs = Math.Round(unUsedLogs * price * 0.25, 2);
            double total = Math.Round(usedLogs + unUsedLogs, 2);

            Console.WriteLine("Price per meter: ${0:F2}", price);
            Console.WriteLine("Used logs price: ${0:F2}" , usedLogs);
            Console.WriteLine("Unused logs price: ${0:F2}", unUsedLogs);
            Console.WriteLine("CottageScraper subtotal: ${0:F2}", total);


        }
    }
}
