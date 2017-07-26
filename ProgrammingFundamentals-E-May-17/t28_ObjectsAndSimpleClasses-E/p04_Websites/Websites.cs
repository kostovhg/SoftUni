using System;
using System.Collections.Generic;
using System.Linq;

namespace p04_Websites
{
    class Website
    {
        public string Host { get; set; }
        public string Domain { get; set; }
        public List<string> Queries { get; set; }

        public void Print()
        {
            Console.Write("https://www.{0}.{1}", this.Host, this.Domain);
            if (this.Queries.Count > 0)
            {
                Console.WriteLine("/query?={0}",
                    string.Join("&", this.Queries));
            }
            else
            {
                Console.WriteLine();
            }
        }
        public Website(string[] input)
        {
            this.Host = input[0];
            this.Domain = input[1];
            this.Queries = input
                .Skip(2)
                .Select(x => "[" + x + "]")
                .ToList();
        }
    }
    class Websites
    {
        static void Main(string[] args)
        {
            List<Website> sites = new List<Website>();
            string input;
            while (!"end".Equals(input = Console.ReadLine()))
            {
                sites.Add(new Website(input
                    .Split(new string[] { " | ", "," },
                    StringSplitOptions.RemoveEmptyEntries)));
            }
            foreach (Website site in sites)
            {
                site.Print();
            }
        }
    }
}
