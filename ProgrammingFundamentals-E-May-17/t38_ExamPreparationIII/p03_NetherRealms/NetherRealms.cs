using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace p03_NetherRealms
{
    class NetherRealms
    {
        class Demon
        {
            public string Name { get; set; }
            public ulong Health { get; set; }
            public double Damage { get; set; }

            public Demon(string name)
            {
                this.Name = name;
                MatchCollection health = Regex.Matches(name, @"([^0-9\+\-\*\/\.]+)");
                foreach (Match m in health)
                {
                    foreach (char ch in m.Value.ToString())
                    {
                        this.Health += (ulong)ch;
                    }
                }
                MatchCollection damage = Regex.Matches(name, @"([+-]?[0-9]+\.?[0-9]+)|([+-]?[0-9])");
                foreach (Match m in damage)
                {
                    this.Damage += double.Parse(m.Value);
                }
                MatchCollection modifiers = Regex.Matches(name, @"([*\/])");
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
            List<Demon> demons = new List<Demon>
                (from string name in Console.ReadLine()
                    .Split(new char[] { ',', ' ' },
                        StringSplitOptions.RemoveEmptyEntries)
                    select new Demon(name))
                    .ToList();
            foreach (Demon demon in demons.OrderBy(x => x.Name))
            {
               Console.WriteLine("{0} - {1} health, {2:F} damage", demon.Name, demon.Health, demon.Damage);
            }
        }
    }
}
