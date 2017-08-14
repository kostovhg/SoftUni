using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace p03_NetherRealms
{
    class NetherRealms
    {
        // Declare a class that will keep demon parameters
        class Demon
        {
            public string Name { get; set; }
            public ulong Health { get; set; }
            public double Damage { get; set; }
            // A custom constructor, that manipulate the name to
            // find health and damage of the deamon
            public Demon(string name)
            {
                // name is a name
                this.Name = name;
                // the regex match any character one or more time, exept [^/*+-.]
                MatchCollection health = Regex.Matches(name, @"([^0-9\+\-\*\/\.]+)");
                // from all matched strings
                foreach (Match m in health)
                {
                    // take each character
                    foreach (char ch in m.Value)
                    {
                        // and add its ASCII code to health
                        this.Health += (ulong)ch;
                    }
                }
                // Again match the name, as filter all integers and doubles
                // importain for judge test 5 is to match first if we have double
                // ([+-][0-9]+.[0-9]+) and after that for integers
                MatchCollection damage = Regex.Matches(name, @"([+-]?[0-9]+\.?[0-9]+)|([+-]?[0-9])");
                // for all matched numbers
                foreach (Match m in damage)
                {
                    // add the value (with its sign) to demon damage
                    this.Damage += double.Parse(m.Value);
                }
                // Now it is time to match damage modifiers
                MatchCollection modifiers = Regex.Matches(name, @"([*\/])");
                // because they are applayed to already defined demon damage
                // in their order of appearance we just modify the Damage value
                // for each modifier
                foreach (Match mod in modifiers)
                {
                    if (mod.Value == "*")
                    {
                        this.Damage *= 2.0;
                    }
                    else
                    {
                        this.Damage /= 2.0;
                    }
                }
            }
        }
        static void Main(string[] args)
        {
            // Create a list of custom class Demon
            // clearing all spaces and commas
            // each demon name is provided to the custom constructor
            // of demons separately.
            List<Demon> demons = new List<Demon>
                (from string name in Console.ReadLine()
                    .Split(new char[] { ',', ' ' },
                        StringSplitOptions.RemoveEmptyEntries)
                 select new Demon(name))
                    .ToList();
            // Loop and print
            foreach (Demon demon in demons.OrderBy(x => x.Name))
            {
                Console.WriteLine("{0} - {1} health, {2:F} damage", demon.Name, demon.Health, demon.Damage);
            }
        }
    }
}
